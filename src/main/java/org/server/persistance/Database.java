package org.server.persistance;

import java.sql.*;

import static org.server.persistance.QueryConstants.ADD_NEW_USER_TO_DATABASE;
import static org.server.persistance.QueryConstants.CHECK_EMAIL_EXISTS;

public class Database {

    public boolean registerNewUser(String email, String username, String password, String profileImage, String dateOfBirth) {
        try (Connection conn = Datasource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(ADD_NEW_USER_TO_DATABASE)) {
            stmt.setString(1, username);
            stmt.setString(2, password);
            stmt.setString(3, email);
            stmt.setString(4, profileImage);
            stmt.setString(5, dateOfBirth);
            return stmt.executeUpdate() > 0;
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
                return Long.parseLong(rs.getString("id"));
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return -1; //no ID found
    }

    public String GetUsername(long id) {
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

    /*
    public static boolean printAllUserIDs() //for debug
    {
        try (Connection conn = Datasource.getConnection();
             Statement stmt  = conn.createStatement();
             ResultSet rs = stmt.executeQuery(QueryConstants.GET_ID_ALL_USERS))
        {
            while (rs.next())
            {
                System.out.println(rs.getString("id"));
            }
            System.out.print("test\n");
            System.out.println (stmt);
            return true;
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        return false;
    }
    public int GetNewestUserID() //for debug
    {
        try (Connection conn = Datasource.getConnection();
             Statement stmt  = conn.createStatement();
             ResultSet rs = stmt.executeQuery(QueryConstants.GET_NEWEST_USER_ID))
        {
            if (rs.next())
            {
                return rs.getInt(1);
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        return -1;
    }
     */
}
