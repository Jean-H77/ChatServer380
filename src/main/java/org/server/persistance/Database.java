package org.server.persistance;

import at.favre.lib.crypto.bcrypt.BCrypt;
import java.sql.*;
import java.util.UUID;

import static org.server.persistance.QueryConstants.ADD_NEW_USER_TO_DATABASE;
import static org.server.persistance.QueryConstants.CHECK_EMAIL_EXISTS;
import static org.server.persistance.QueryConstants.GET_PASS;

public class Database {

    public boolean registerNewUser(String email, String username, String password, String profileImage, String dateOfBirth) {
        try (Connection conn = Datasource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(ADD_NEW_USER_TO_DATABASE)) {
            conn.setAutoCommit(false);

            long uuid = Math.abs(UUID.randomUUID().getLeastSignificantBits());
            stmt.setString(1, String.valueOf(uuid));
            stmt.setString(2, username);
            stmt.setString(3, BCrypt.withDefaults().hashToString(8, password.toCharArray()));
            stmt.setString(4, email);
            stmt.setString(5, profileImage);
            stmt.setString(6, dateOfBirth);

            stmt.executeUpdate();
            conn.commit();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean emailExists(String email) {
        try (Connection conn = Datasource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(CHECK_EMAIL_EXISTS)) {
            stmt.setString(1, email);
            return stmt.executeQuery().isBeforeFirst();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public String getPassword(long id){
        try (Connection conn = Datasource.getConnection();
             PreparedStatement stmt  = conn.prepareStatement(GET_PASS)) {
            stmt.setString(1, String.valueOf(id));
            return String.valueOf(stmt.executeQuery().isBeforeFirst());
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    public String getImage(long id) {
        String imageGetStr = QueryConstants.GET_IMAGE + id + "')";
        try (Connection conn = Datasource.getConnection();
             Statement stmt  = conn.createStatement();
             ResultSet rs = stmt.executeQuery(imageGetStr)) {
            if (rs.next()) {
                return rs.getString("profileImage");
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public long getUUID(String email) {
        String emailGetStr = QueryConstants.GET_UUID + email + "')";
        try (Connection conn = Datasource.getConnection();
             Statement stmt  = conn.createStatement();
             ResultSet rs = stmt.executeQuery(emailGetStr)) {
            if (rs.next()) {
                return Long.parseLong(rs.getString("uuid"));
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return -1; //no ID found
    }

    public String getUsername(long id) {
        String getUsrNameStr = QueryConstants.GET_USER_NAME + id + "')";
        try (Connection conn = Datasource.getConnection();
             Statement stmt  = conn.createStatement();
             ResultSet rs = stmt.executeQuery(getUsrNameStr)) {
            if (rs.next()) {
                return rs.getString("username");
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
