package sophia;
import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.VBox;


/**
 * Create a reminder
 */
public class ReminderBox extends TitledPane {
    @FXML
    private VBox userTaskContainer = new VBox();
    private ReminderBox() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("/view/ReminderBox.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static ReminderBox getReminderBox() {
        return new ReminderBox();
    }
    public void addUserTask(String task) {
        userTaskContainer.getChildren().add(new Label(task));
    }
}
