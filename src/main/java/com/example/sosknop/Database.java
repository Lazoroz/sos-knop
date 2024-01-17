package com.example.sosknop;

import com.fazecast.jSerialComm.SerialPort;
import com.google.gson.Gson;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.sql.*;


public class Database {

     public static void main(String[] args) {
//          Login("imperdiet.ullamcorper@google.couk", "account");
//          System.out.println(sessionManager.getInstance().getLoggedInUserId());
     }

     public static void showContact(int x) {
          Connection con = dbConnection.getConnection();
               String sql =  "select voornaam, achternaam, tussenvoegsels ,telefoonnummer from contactpersoon left join registreer on registreer.contactpersoon_id = contactpersoon.contactpersoon_id where klant_id = ?";

               // Executes the query
               try (PreparedStatement ps = con.prepareStatement(sql)) {
                    ps.setInt(1, x);

                    try (ResultSet rs = ps.executeQuery()) {

                         while (rs.next()) {

                         String voornaam =  rs.getString("voornaam");
                         String ts =   rs.getString("tussenvoegsels");
                         String achternaam =   rs.getString("achternaam");
                         int telefoonnummer = rs.getInt("telefoonnummer");

                         String fullName = voornaam + " " + ts + " " + achternaam;

                         System.out.printf("%s ",fullName );
                         System.out.println(telefoonnummer);
                         }
                    }
               } catch (SQLException e) {
               throw new RuntimeException(e);
          }
     }

     public static boolean newContact(String voornaam, String ts , String achternaam, String tel) {
        Connection con = dbConnection.getConnection();

        try {
             String sql = "INSERT INTO contactpersoon( voornaam, tussenvoegsels, achternaam, telefoonnummer) VALUES(?,?,?,?)";
             String sql2 = "INSERT INTO `registreer`(`klant_id`, `contactpersoon_id`) VALUES (?, LAST_INSERT_ID())";

             try (PreparedStatement ps = con.prepareStatement(sql)) {
                  ps.setString(1, voornaam);
                  ps.setString(2, ts);
                  ps.setString(3, achternaam);
                  ps.setString(4, tel);
                  ps.executeUpdate();

             }
             try (PreparedStatement ps = con.prepareStatement(sql2)) {
                  ps.setInt(1, sessionManager.getInstance().getLoggedInUserId());
                  ps.executeUpdate();
             }

             return true;
        }catch (SQLException e) {
             throw new RuntimeException(e);
          }

     }



     public static boolean newUser(String voornaam, String tv, String achtermaam, String tel, String email, String ww) {
          Connection con = dbConnection.getConnection();

          try {
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

     public static boolean updatePassword(String oldPassword, String newPassword) {
          Connection con = dbConnection.getConnection();

          try  {
               String sql =  "UPDATE user SET wachtwoord = ? WHERE wachtwoord = ? ";

               // Executes the query
               try (PreparedStatement ps = con.prepareStatement(sql)) {
                    ps.setString(1, newPassword);
                    ps.setString(2, oldPassword);
                    ps.executeUpdate();

                    return true;
               }
          } catch (SQLException e) {
               throw new RuntimeException(e);
          }
     }

     public static boolean Login(String x, String y) {
          Connection con = dbConnection.getConnection();

          try {
               String sql =  "select user.klant from user left join klant on klant.klant_id = user.klant where email = ? and wachtwoord = ? ";

               // Executes the query 
                try (PreparedStatement ps = con.prepareStatement(sql)) {
                     ps.setString(1, x);
                     ps.setString(2, y);

                     try (ResultSet rs = ps.executeQuery()) {
                          if (rs.next()) {
                               int userId = rs.getInt("klant");

                               sessionManager.getInstance().createSession(userId);
                               System.out.println(userId);
                               return true;
                          }
                          return false;
                     }
                }
          } catch (SQLException e) {
               throw new RuntimeException(e);
          }
     }
}
