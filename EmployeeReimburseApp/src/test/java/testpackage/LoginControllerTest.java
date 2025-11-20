package testpackage;

import com.example.employeereimburseapp.LoginPageController;
import com.example.employeereimburseapp.UserSession;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import javafx.event.ActionEvent;
import java.io.IOException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;

public class LoginControllerTest {

    private LoginPageController controller;

    @BeforeAll
    static void initJavaFx() throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(1);
        Platform.startup(latch::countDown);
        latch.await(5, TimeUnit.SECONDS);
    }

    @BeforeEach
    void setUp() throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(1);
        Platform.runLater(() ->{
            controller = new LoginPageController();
            controller.setEmpIDField(new TextField());
            controller.setPasswordField(new TextField());
            controller.setEmailField(new TextField());
            UserSession.destroyUser();
            latch.countDown();
        });
        latch.await(5, TimeUnit.SECONDS);

    }

    @Test
    void employeeLoginTest() throws IOException, InterruptedException {
        CountDownLatch latch = new CountDownLatch(1);
        Platform.runLater(() ->{
            controller.getEmpIDField().setText("1");
            controller.getEmailField().setText("g4zac@unb.ca");
            controller.getPasswordField().setText("test123");
            Button fakeButton = new Button("Login");
            StackPane root = new StackPane(fakeButton);
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);

            ActionEvent event = new ActionEvent(fakeButton, null);
            try {
                controller.testPress(event);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            UserSession user = UserSession.getUser();
            assertNotNull(user);
            assertEquals("Employee",user.getRole());
            latch.countDown();
        });

        latch.await(5, TimeUnit.SECONDS);

    }

    @Test
    void managerLoginTest() throws IOException, InterruptedException {
        CountDownLatch latch = new CountDownLatch(1);
        Platform.runLater(() ->{
            controller.getEmpIDField().setText("3");
            controller.getEmailField().setText("gingr@unb.ca");
            controller.getPasswordField().setText("ginger");
            Button fakeButton = new Button("Login");
            StackPane root = new StackPane(fakeButton);
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);

            ActionEvent event = new ActionEvent(fakeButton, null);
            try {
                controller.testPress(event);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            UserSession user = UserSession.getUser();
            assertNotNull(user);
            assertEquals("Manager",user.getRole());
            latch.countDown();
        });

        latch.await(5, TimeUnit.SECONDS);

    }

    @Test
    void failLoginTest() throws IOException, InterruptedException {
        CountDownLatch latch = new CountDownLatch(1);
        Platform.runLater(() ->{
            controller.getEmpIDField().setText("-1");
            controller.getEmailField().setText("fail@unb.ca");
            controller.getPasswordField().setText("fail");
            Button fakeButton = new Button("Login");
            StackPane root = new StackPane(fakeButton);
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);

            ActionEvent event = new ActionEvent(fakeButton, null);
            try {
                controller.testPress(event);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            UserSession user = UserSession.getUser();
            assertNull(user);
            latch.countDown();
        });

        latch.await(5, TimeUnit.SECONDS);

    }


}
