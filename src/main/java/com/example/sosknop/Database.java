package com.example.sosknop;
import java.nio.charset.StandardCharsets;
import java.sql.*;

import com.fazecast.jSerialComm.*;
import java.io.InputStream;


public class Database {

     public static void main(String[] args) {
          pushedBtn();
     }
     public static void showContact(int x) {
          String url = "jdbc:mysql://localhost:3306/challenge";
          String username = "user";
          String pwd = "ic-piP3412";

          try (Connection con = DriverManager.getConnection(url, username, pwd)) {
               String sql =  "select voornaam, achternaam, tussenvoegsels ,telefoonnummer from contactpersoon left join registreer on registreer.contactpersoon_id = contactpersoon.contactpersoon_id where klant_id = ? ";

               // Executes the query
               try (PreparedStatement ps = con.prepareStatement(sql)) {
                    ps.setInt(1, x);

                    try (ResultSet rs = ps.executeQuery()) {

                         while (rs.next()) {

                         String voornaam =  rs.getString("voornaam");
                         String ts =   rs.getString("tussenvoegsels");
                         String achternaam =   rs.getString("achternaam");
                         int telefoonnummer = rs.getInt("telefoonnummer");

                         System.out.printf("%s %s %s %d", voornaam, ts, achternaam, telefoonnummer );
                         System.out.println();
                         }
                    }
               }
          } catch (SQLException e) {
               throw new RuntimeException(e);
          }
     }

     public static boolean newUser(String voornaam, String tv, String achtermaam, String tel, String email, String ww) {
          String url = "jdbc:mysql://localhost:3306/challenge";
          String username = "user";
          String pwd = "ic-piP3412";



          try (Connection con = DriverManager.getConnection(url, username, pwd)) {
               String sql = "INSERT INTO klant( voornaam, tussenvoegsels, achternaam, telefoonnummer) VALUES (?,?,?,?); " ;
               String sql1 = "INSERT INTO user( email, wachtwoord, klant) VALUES (?,?,LAST_INSERT_ID()); ";

               try ( PreparedStatement ps = con.prepareStatement(sql)) {
                    ps.setString(1, voornaam);
                    ps.setString(2, tv);
                    ps.setString(3, achtermaam);
                    ps.setString(4, tel);
                   ps.executeUpdate();
               }
               try (PreparedStatement ps = con.prepareStatement(sql1)) {
                    ps.setString(1, email);
                    ps.setString(2, ww);
                    ps.executeUpdate();
               }

               return true;

          } catch (SQLException e) {
               throw new RuntimeException(e);

          }
     }

     public static void updatePassword(String oldpassword, String NewPassword) {

     }

     public static boolean Login(String x, String y) {
          String url = "jdbc:mysql://localhost:3306/challenge";
          String username = "user";
          String pwd = "ic-piP3412";

          try (Connection con = DriverManager.getConnection(url, username, pwd)) {
               String sql =  "select * from user left join klant on klant.klant_id = user.klant where email = ? and wachtwoord = ? ";

               // Executes the query 
                try (PreparedStatement ps = con.prepareStatement(sql)) {
                     ps.setString(1, x);
                     ps.setString(2, y);

                     try (ResultSet rs = ps.executeQuery()) {
                          return rs.next();
                     }
                }
          } catch (SQLException e) {
               throw new RuntimeException(e);
          }
     }

     public static void pushedBtn() {
          SerialPort serialPort = SerialPort.getCommPort("COM3"); // e.g., "COM3" or "/dev/ttyUSB0"
          serialPort.setComPortTimeouts(SerialPort.TIMEOUT_READ_SEMI_BLOCKING, 10000, 0);
          if (serialPort.openPort()) {

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
                                   //Json data = new Gson().fromJson(completeJson, Json.class);

                                   // Process the parsed data as needed
                                   //aveJsonDataToDatabase(data);
                                   endIndex = buffer.indexOf("}");
                              }
                         }
                    }
               } catch (Exception e) {
                    e.printStackTrace();
               } finally {
                    serialPort.closePort();
               }
          } else {
               System.err.println("Error opening serial port.");
          }
     }

     public static int saveJsonDataToDatabase(Json data) {
          //System.out.println(data.getLocation());
          return 1 + 2;
     }



}
