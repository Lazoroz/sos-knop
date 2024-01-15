package com.example.sosknop;
import java.sql.*;
import com.google.gson.Gson;
import com.fazecast.jSerialComm.*;
import java.io.InputStream;


public class Database {

     public static void main(String[] args) {
          //showContact(1);
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

          if (serialPort.openPort()) {
               System.out.println("Serial port opened successfully!");

               try (InputStream inputStream = serialPort.getInputStream()) {
                    byte[] buffer = new byte[1024];
                    int bytesRead;

                    while (true) {
                         bytesRead = inputStream.read(buffer);

                         if (bytesRead > 0) {
                              String receivedData = new String(buffer, 0, bytesRead);
                              System.out.println("Received data: " + receivedData);

                              // Parse the received data using Gson
                              Json data = new Gson().fromJson(receivedData, Json.class);

                              // Process the parsed data as needed (e.g., save to the database)
                              //saveDataToDatabase(data);
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

}
