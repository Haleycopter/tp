package seedu.address.ui;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.testfx.util.WaitForAsyncUtils.waitForFxEvents;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.concurrent.TimeoutException;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.testfx.api.FxRobot;
import org.testfx.api.FxToolkit;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import seedu.address.commons.core.GuiSettings;
import seedu.address.logic.Logic;
import seedu.address.model.person.Person;

// Code written with help from ChatGPT
@ExtendWith({ApplicationExtension.class, MockitoExtension.class})
class MainWindowTest {
    private static final double DEFAULT_HEIGHT = 600;
    private static final double DEFAULT_WIDTH = 740;
    private static final Path DEFAULT_PATH = Paths.get("data" , "addressbook.json");
    private static final ObservableList<Person> EMPTY_LIST = FXCollections.observableArrayList();

    private MainWindow mainWindow;

    @Start
    void start(Stage stage) {
        Logic logic = mock(Logic.class);
        when(logic.getGuiSettings()).thenReturn(new GuiSettings());
        when(logic.getFilteredPersonList()).thenReturn(EMPTY_LIST);
        when(logic.getAddressBookFilePath()).thenReturn(DEFAULT_PATH);

        mainWindow = new MainWindow(stage, logic);
        stage.show();
    }

    @Test
    void constructMainWindow_defaultDimensions_success(FxRobot robot) {
        waitForFxEvents();
        Stage stage = (Stage) robot.window(0);
        assertEquals(DEFAULT_WIDTH, stage.getWidth());
        assertEquals(DEFAULT_HEIGHT, stage.getHeight());
    }

    @Test
    void execute_fillInnerParts_success(FxRobot robot) throws TimeoutException {
        FxToolkit.setupFixture(() -> {
            mainWindow.fillInnerParts();
        });
        waitForFxEvents();

        StackPane personListPanelPlaceholder =
            robot.lookup("#personListPanelPlaceholder").queryAs(StackPane.class);
        assertEquals(1, personListPanelPlaceholder.getChildren().size());

        StackPane statusbarPlaceholder =
            robot.lookup("#statusbarPlaceholder").queryAs(StackPane.class);
        assertEquals(1, statusbarPlaceholder.getChildren().size());

        StackPane commandBoxPlaceholder =
            robot.lookup("#commandBoxPlaceholder").queryAs(StackPane.class);
        assertEquals(1, commandBoxPlaceholder.getChildren().size());
    }
}
