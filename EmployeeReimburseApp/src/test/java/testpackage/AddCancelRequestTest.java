package testpackage;

import com.example.employeereimburseapp.CentralDatabase;
import com.example.employeereimburseapp.EmployeeDashController;
import com.example.employeereimburseapp.LoginPageController;
import com.example.employeereimburseapp.UserSession;
import javafx.application.Platform;
import javafx.scene.control.TextField;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class AddCancelRequestTest {

    private EmployeeDashController controller;
    private static LoginPageController sessionCreator;

    @BeforeAll
    static void initJavaFx() throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(1);
        Platform.startup(() -> {
            Connection con = CentralDatabase.getConnection();
            try {
                sessionCreator.createSession(con);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            latch.countDown();
        });
        latch.await(5, TimeUnit.SECONDS);
    }


}
