import org.junit.jupiter.api.Test;
import org.server.persistance.Database;

import static org.junit.jupiter.api.Assertions.*;

class DatabaseTest
{
    @Test
    void testNewUser()
    {
        Database instance = new Database();
        boolean result = instance.registerNewUser("test@gmail.com", "test", "pass543", "img.jpg", "2000-01-01");
        assertTrue(result);
    }
    @Test
    void ifEmailExists()
    {
        Database instance = new Database();
        boolean result = instance.emailExists("test@gmail.com");
        assertTrue(result);
    }

    @Test
    void ifCanGetPassword(){
        Database instance = new Database();
        String password = instance.getPassword(543);
        assertNotNull(password);
    }

    @Test
    void ifCanGetImage(){
        Database instance = new Database();
        String result = instance.getImage(543);
        assertNotNull(result);
    }

    @Test
    void ifCanGetUUID(){
        Database instance = new Database();
        long id = instance.getUUID("test@gmail.com");
        assertEquals(-1,id);
    }

    @Test
    void ifCanGetUserName()
    {
        Database instance = new Database();
        String result = instance.getUsername(543);
        assertNotNull(result);
    }
}