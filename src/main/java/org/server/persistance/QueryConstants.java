package org.server.persistance;

public final class QueryConstants {

    /**
     * Adds a new user to the users table
     */
    public static String ADD_NEW_USER_TO_DATABASE = "INSERT INTO USERS (username, pass, email, profileImage, dateOfBirth) VALUES (?,?,?,?,?)";

    /**
     * Checks if email exists
     */
    public static String CHECK_EMAIL_EXISTS = "SELECT email FROM USERS WHERE email = ?";


    public static String GET_USER_NAME = "SELECT username FROM chatapp.users WHERE id =";
    public static String GET_UUID = "SELECT id FROM chatapp.users WHERE email =";
    public static String GET_IMAGE = "SELECT profileImage FROM chatapp.users WHERE id =";

    //public static String GET_NEWEST_USER_ID = "SELECT last_insert_id() FROM `chatapp`.`users`";
    //public static String GET_ID_ALL_USERS = "SELECT id FROM `chatapp`.`users`";
    //public static String TEST_QUERY = "SELECT * FROM TABLE_NAME";
    // public static String GET_ID_ALL_USERS = "SELECT id FROM `chatapp`.`users`";

    //private constructor to avoid instantiation
    private QueryConstants() {

    }
}
