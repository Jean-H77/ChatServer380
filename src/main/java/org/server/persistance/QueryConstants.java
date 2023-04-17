package org.server.persistance;

public final class QueryConstants {

    /**
     * Adds a new user to the users table
     */
    public static String ADD_NEW_USER_TO_DATABASE = "INSERT INTO `chatapp`.`users`(uuid, username, pass, email, profileImage, dateOfBirth) VALUES (?,?,?,?,?,?)";

    /**
     * Checks if email exists
     */
    public static String CHECK_EMAIL_EXISTS = "SELECT email FROM chatapp.users WHERE email = ?";

    /**
     * Retrieves passwords from database by comparing UUID
     */
    public static String GET_PASS = "SELECT pass FROM chatapp.users WHERE id = ?";

    /**
     * Decrypyt the incoming SHA1 pass
     */
    public static String DECRYPT_PASS = "SELECT SHA1(UNHEX(SHA1(?)))";
    /**
     * Retrieves username by comparing UUID
     */
    public static String GET_USER_NAME = "SELECT username FROM chatapp.users WHERE id =";

    /**
     * Retrieves UUID by comparing email of the input
     */
    public static String GET_UUID = "SELECT id FROM chatapp.users WHERE email =";

    /**
     * Retrieves the users imgur link of their profile image
     */
    public static String GET_IMAGE = "SELECT profileImage FROM chatapp.users WHERE id =";


    //public static String GET_NEWEST_USER_ID = "SELECT last_insert_id() FROM `chatapp`.`users`";
    //public static String GET_ID_ALL_USERS = "SELECT id FROM `chatapp`.`users`";
    //public static String TEST_QUERY = "SELECT * FROM TABLE_NAME";

    //private constructor to avoid instantiation
    private QueryConstants() {

    }
}
