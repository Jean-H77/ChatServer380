package org.server.persistance;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;

public class Database {
    public boolean RegisterNewUser (String email, String username, String password, String dateOfBirth)
    {
        String addUserStr = QueryConstants.ADD_NEW_USER_TO_DATABASE + "VALUES + ('" + username + "', '" + password + "', '" + email + " ', '" + dateOfBirth + "')";

        try (Connection conn = Datasource.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(addUserStr))
        {
            //stmt.executeUpdate();
            conn.commit();
            return true;
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            return false;
        }
    }
    public boolean EmailExists (String email)
    {
        String emailCheckStr = QueryConstants.CHECK_EMAIL_EXISTS + email + "')";

        try (Connection conn = Datasource.getConnection();
             Statement stmt  = conn.createStatement();
             ResultSet rs =  stmt.executeQuery(emailCheckStr))
        {
            if (rs.next())
            {
                if (Objects.equals(email, rs.getString("email")))
                    return true;
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        return false;
    }

    public String GetImage (long id)
    {
        String imageGetStr = QueryConstants.GET_IMAGE + id + "')";

        try (Connection conn = Datasource.getConnection();
             Statement stmt  = conn.createStatement();
             ResultSet rs = stmt.executeQuery(imageGetStr))
        {
            if (rs.next())
            {
                return rs.getString("profileImage");
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        return "";
    }

    public long GetUUID (String email)
    {
        String emailGetStr = QueryConstants.GET_UUID + email + "')";

        try (Connection conn = Datasource.getConnection();
             Statement stmt  = conn.createStatement();
             ResultSet rs = stmt.executeQuery(emailGetStr))
        {
            if (rs.next())
            {
                return Long.parseLong(rs.getString("id"));
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        return -1; //no ID found
    }
    public String GetUsername (long id)
    {
        String getUsrNameStr = QueryConstants.GET_USER_NAME + id + "')";

        try (Connection conn = Datasource.getConnection();
             Statement stmt  = conn.createStatement();
             ResultSet rs = stmt.executeQuery(getUsrNameStr))
        {
            if (rs.next())
            {
                return rs.getString("username");
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        return ""; //is this unsafe to do so?
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
