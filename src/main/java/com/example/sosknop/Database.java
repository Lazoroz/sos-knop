package com.example.sosknop;

import com.fazecast.jSerialComm.SerialPort;
import com.google.gson.Gson;
import javafx.beans.Observable;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Label;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.sql.*;


public class Database {

     public static void main(String[] args) {
     }

     public static void showName(int x, Label label) {
          Connection con = dbConnection.getConnection();
          String sql = "select voornaam, tussenvoegsels, achternaam from klant where klant_id = ?";

          try (PreparedStatement ps = con.prepareStatement(sql)) {
               ps.setInt(1, x);

               try (ResultSet rs = ps.executeQuery()) {

                    while (rs.next()) {
                         String vooranaam = rs.getString("voornaam");
                         String ts = rs.getString("tussenvoegsels");
                         String achternaam = rs.getString("achternaam");
                         String full = vooranaam + " " + ts + " " + achternaam;
                         label.setText(full);
                    }
               }
               con.close();
          } catch (SQLException e) {
               throw new RuntimeException(e);
          }
     }


     public static ObservableList<Contacten> showContact() {
          ObservableList<Contacten> contactList = FXCollections.observableArrayList();
          Connection con = dbConnection.getConnection();
          String sql =  "select voornaam, achternaam, tussenvoegsels ,telefoonnummer from contactpersoon left join registreer on registreer.contactpersoon_id = contactpersoon.contactpersoon_id where klant_id = ?";

          int loggedInUserId = sessionManager.getInstance().getLoggedInUserId();
          // Executes the query
          try (PreparedStatement ps = con.prepareStatement(sql)) {
               ps.setInt(1, loggedInUserId);

               try (ResultSet rs = ps.executeQuery()) {

                    while (rs.next()) {

                         String voornaam =  rs.getString("voornaam");
                         String ts =   rs.getString("tussenvoegsels");
                         String achternaam =   rs.getString("achternaam");
                         int telefoonnummer = rs.getInt("telefoonnummer");
                         String naam = voornaam + " " + ts + " " + achternaam;

                         Contacten contacten = new Contacten(naam, telefoonnummer);
                         contactList.add(contacten);
                    }
               }
               con.close();
          } catch (SQLException e) {
               throw new RuntimeException(e);
          }
          return contactList;
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

                  con.close();
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
                    con.close();
               }

               return true;

          } catch (SQLException e) {
               throw new RuntimeException(e);

          }
     }

     public static boolean updateUser(String voornaam, String tv, String achtermaam, String tel, String email, int id) {
          Connection con = dbConnection.getConnection();

          try {
               String sql =  "UPDATE `klant` SET `voornaam`= ?,`tussenvoegsels`= ?,`achternaam`= ?, `telefoonnummer`= ? WHERE klant_id = ?";
               String sql1 = "UPDATE `user` SET `email`= ? WHERE klant = ?";

               try ( PreparedStatement ps = con.prepareStatement(sql)) {
                    ps.setString(1, voornaam);
                    ps.setString(2, tv);
                    ps.setString(3, achtermaam);
                    ps.setString(4, tel);
                    ps.setInt(5, id);
                    ps.executeUpdate();
               }
               try (PreparedStatement ps = con.prepareStatement(sql1)) {
                    ps.setString(1, email);
                    ps.setInt(2, id);
                    ps.executeUpdate();
                    con.close();
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
                               con.close();
                               return true;
                          }
                          return false;
                     }

                }
          } catch (SQLException e) {
               throw new RuntimeException(e);
          }
     }

    public static void deleteContact(Contacten contact) {
         Connection con = dbConnection.getConnection();
         String sql = "DELETE FROM contactpersoon WHERE voornaam = ? AND telefoonnummer = ?";

         try (PreparedStatement ps = con.prepareStatement(sql)) {
              // Assuming that voornaam is the unique identifier for your contact
              ps.setString(1, contact.getNaam());
              ps.setInt(2, contact.getTelefoonnummer());

              int rowsAffected = ps.executeUpdate();

              if (rowsAffected > 0) {
                   System.out.println("Contact deleted successfully!");
              } else {
                   System.out.println("Contact not found or couldn't be deleted.");
              }
         } catch (SQLException e) {
              throw new RuntimeException(e);
         } finally {
              try {
                   con.close();
              } catch (SQLException e) {
                   e.printStackTrace();
              }
         }
    }
}
