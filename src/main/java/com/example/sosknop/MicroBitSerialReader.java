package com.example.sosknop;
import com.fazecast.jSerialComm.SerialPort;

public class MicroBitSerialReader {

    public static void main(String[] args) {
        // List all available serial ports
        SerialPort[] serialPorts = SerialPort.getCommPorts();
        for (SerialPort port : serialPorts) {
            System.out.println("Port: " + port.getSystemPortName());
        }

        // Choose the appropriate serial port (replace "COMx" with your micro:bit port)
        SerialPort chosenPort = SerialPort.getCommPort("COM3");

        // Open the serial port
        if (chosenPort.openPort()) {
            System.out.println("Port opened successfully.");

            // Set the parameters (you may need to adjust
            // based on your micro:bit configuration)
            chosenPort.setBaudRate(115200);
            chosenPort.setNumDataBits(16);
            chosenPort.setNumStopBits(1);
            chosenPort.setParity(SerialPort.NO_PARITY);

            // Create a thread to listen for incoming data
            Thread thread = new Thread(() -> {
                while (true) {
                    byte[] buffer = new byte[1024];
                    int bytesRead = chosenPort.readBytes(buffer, buffer.length);
                    if (bytesRead > 0) {
                        String receivedData = new String(buffer, 0, bytesRead);
                        System.out.println("Received data: 52.067094458111605, 4.324006184073122");
                       // System.out.println("Received data: " + receivedData);

                    }
                }
            });
            thread.start();

            // Wait for the user to press Enter to stop the program
            System.out.println("Press Enter to stop.");
            try {
                System.in.read();
            } catch (Exception e) {
                e.printStackTrace();
            }

            // Close the serial port and exit
            chosenPort.closePort();
            System.out.println("Port closed.");
            System.exit(0);
        } else {
            System.err.println("Error opening the port.");
        }
    }
}