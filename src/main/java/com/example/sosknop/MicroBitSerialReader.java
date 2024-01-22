package com.example.sosknop;
import com.fazecast.jSerialComm.SerialPort;
import com.google.gson.Gson;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class MicroBitSerialReader {
    public static void main() {

        SerialPort serialPort = SerialPort.getCommPort("COM3"); // e.g., "COM3" or "/dev/ttyUSB0"
        serialPort.setComPortTimeouts(SerialPort.TIMEOUT_READ_SEMI_BLOCKING, 5000, 0);
        if (serialPort.openPort()) {
            System.out.println("het is open");
            serialPort.setBaudRate(115200);  // Set the baud rate to match the Microbit
            serialPort.setNumDataBits(8);  // Set the number of data bits
            serialPort.setNumStopBits(1);  // Set the number of stop bits
            serialPort.setParity(SerialPort.NO_PARITY);

            try (InputStream inputStream = serialPort.getInputStream()) {
                byte[] bufferBytes = new byte[1024];
                StringBuilder buffer = new StringBuilder();
                int bytesRead;

                while (true) {
                    bytesRead = inputStream.read(bufferBytes);

                    if (bytesRead > 0) {
                        buffer.append(new String(bufferBytes, 0, bytesRead, StandardCharsets.UTF_8));

                        // Check if the buffer contains a complete JSON object
                        int endIndex = buffer.indexOf("}");
                        while (endIndex != -1) {
                            String completeJson = buffer.substring(0, endIndex + 1);
                            buffer.delete(0, endIndex + 1);

                            // Parse the complete JSON object
                            System.out.println("Received data: " + completeJson);
                            Json data = new Gson().fromJson(completeJson, Json.class);

                            // Process the parsed data as needed
                            saveJsonDataToDatabase(data);
                            endIndex = buffer.indexOf("}");
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                serialPort.closePort();
             }
        }
    }

    public static void saveJsonDataToDatabase(Json data) {
        Connection con = dbConnection.getConnection();
        double x = Math.random() * 4;

        try {
            String sql = "insert into pushedbtn(locatie,`sos-knop`) values (?,?) " ;

            try ( PreparedStatement ps = con.prepareStatement(sql)) {
                ps.setString(1, data.getLocation());
                ps.setInt(2, (int)x);
                ps.executeUpdate();

                System.out.println("opgelagen!");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);

        }
    }
}