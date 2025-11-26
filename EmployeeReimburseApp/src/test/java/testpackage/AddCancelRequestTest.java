package testpackage;


import com.example.employeereimburseapp.CardEmployee;
import com.example.employeereimburseapp.EmployeeDashController;
import com.example.employeereimburseapp.UserSession;
import javafx.application.Platform;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.sql.SQLException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class AddCancelRequestTest {

    private EmployeeDashController controller;

    @BeforeAll
    public static void initJavaFx() throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(1);
        try{
            Platform.startup(() -> {

                UserSession.createUser(1,"Gian Karl Cera", "g4zac@unb.ca", "employee");
                latch.countDown();
            });
            latch.await(5, TimeUnit.SECONDS);
        } catch (IllegalStateException e){
            UserSession.createUser(1,"Gian Karl Cera", "g4zac@unb.ca", "employee");
        }


    }

    @BeforeEach
    public void setUp() throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(1);
        Platform.runLater(() ->{
            controller = new EmployeeDashController();
            controller.setCostField(new TextField());
            controller.setExpenseField(new TextField());
            controller.setReasonField(new TextField());
            controller.setRecieptField(new TextField());
            controller.setLocField(new TextField());
            controller.setReqListVB(new VBox());
            latch.countDown();
        });
        latch.await(5, TimeUnit.SECONDS);
    }

    @Test
    public void addCancelRequestTest() throws SQLException, InterruptedException {
        AtomicBoolean exists = new AtomicBoolean(false);
        AtomicBoolean deleted = new AtomicBoolean(false);
        CountDownLatch latch = new CountDownLatch(1);
        Platform.runLater(() ->{
            controller.getLocField().setText("Test Location");
            controller.getCostField().setText("100");
            controller.getReasonField().setText("For Testing");
            controller.getRecieptField().setText("Test Reciept");
            controller.getExpenseField().setText("Testing type");
            controller.requestCreate();
            VBox reqListVB = controller.getReqListVB();

            for(Node node : reqListVB.getChildren()){
                if(node instanceof Parent parent){
                    Parent cardRoot = (Parent) node;
                    Label locLbl = (Label) cardRoot.lookup("#locLbl");
                    if(locLbl != null&& locLbl.getText().equals("Test Location")){
                        exists.set(true);
                    }
                }
            }

            for(Node node : reqListVB.getChildren()){
                if(node instanceof Parent parent){
                    Label locLbl = (Label) parent.lookup("#locLbl");
                    if(locLbl != null && locLbl.getText().equals("Test Location")){
                        CardEmployee controller = (CardEmployee) parent.getProperties().get("controller");
                        controller.cancelRequest();
                        break;
                    }
                }
            }

            for(Node node : reqListVB.getChildren()){
                if(node instanceof Parent parent){
                    Parent cardRoot = (Parent) node;
                    Label locLbl = (Label) cardRoot.lookup("#locLbl");
                    if(locLbl != null&& locLbl.getText().equals("Test Location")){
                        deleted.set(true);
                    }
                }
            }


            latch.countDown();
        });
        latch.await(5, TimeUnit.SECONDS);
        assertTrue(exists.get(), "Couldn't Create cards");
        assertFalse(deleted.get(), "Couldn't Delete cards");
    }
    @Test
    public void InvalidAddTest() throws SQLException, InterruptedException {
        AtomicBoolean created = new AtomicBoolean(false);
        CountDownLatch latch = new CountDownLatch(1);
        Platform.runLater(() ->{
            controller.getLocField().setText("Test Location");
            controller.getCostField().setText("This is not a number");
            controller.getReasonField().setText("For Testing");
            controller.getRecieptField().setText("Test Reciept");
            controller.getExpenseField().setText("Testing type");
            controller.requestCreate();
            VBox reqListVB = controller.getReqListVB();
            for(Node node : reqListVB.getChildren()){
                created.set(true);
                break;
            }

            latch.countDown();
        });
        latch.await(5, TimeUnit.SECONDS);
        assertFalse(created.get(), "Cost was invalid so no card should have been created");
    }

}
