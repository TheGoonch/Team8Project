package testpackage;

import com.example.employeereimburseapp.CentralDatabase;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.sql.*;
import java.sql.SQLException;

 class CentralDatabaseTest{

     @Test
    public void testEmployeeSelect(){
         try(Connection con = CentralDatabase.getConnection()){
             ResultSet rs;
             PreparedStatement ps;
             ps = con.prepareStatement("SELECT 1 FROM \"UNBEmployee\" WHERE \"emp_id\" = ?");
             ps.setInt(1,1);
             rs = ps.executeQuery();
             assertTrue(rs.next());
         }catch(SQLException e){
             System.out.println("UnitTest: Error in getting database connection \n" + e.getMessage());
         }

     }

     @Test
     public void testUserSelect(){
         try(Connection con = CentralDatabase.getConnection()){
             ResultSet rs;
             PreparedStatement ps;
             ps = con.prepareStatement("SELECT 1 FROM \"User\" WHERE \"user_id\" = ?");
             ps.setInt(1,1);
             rs = ps.executeQuery();
             assertTrue(rs.next());
         }catch(SQLException e){
             System.out.println("UnitTest: Error in getting database connection \n" + e.getMessage());
         }

     }
     @Test
     public void testRequest(){
         try(Connection con = CentralDatabase.getConnection()){
             ResultSet rs;
             PreparedStatement ps;
             ps = con.prepareStatement("SELECT 1 FROM \"Request\" WHERE \"user_id\" = ?");
             ps.setInt(1,1);
             rs = ps.executeQuery();
             assertTrue(rs.next());
         }catch(SQLException e){
             System.out.println("UnitTest: Error in getting database connection \n" + e.getMessage());
         }

     }

     @Test
     public void testConnection(){
        try(Connection con = CentralDatabase.getConnection()){
            assertNotNull(con);
            System.out.println("Connected to database successfully");
        }catch(SQLException e){
            System.out.println("UnitTest: Error in getting database connection \n" + e.getMessage());
        }
     }

 }