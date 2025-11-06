package testpackage;

import com.example.employeereimburseapp.UserSession;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class UserSessionTest{

    @Test
    public void testGetNotCreatedUser(){
        assertNull(UserSession.getUser());
    }

    @Test
    public void testCreateUser(){
        UserSession.createUser(1,"Hazem","hazem@gmail.com","employee");
        UserSession user1 = UserSession.getUser();
        assert user1 != null;


        assertEquals(1,user1.getId());
        assertEquals("Hazem",user1.getName());
        assertEquals("hazem@gmail.com",user1.getEmail());
        assertEquals("employee",user1.getRole());
    }




}