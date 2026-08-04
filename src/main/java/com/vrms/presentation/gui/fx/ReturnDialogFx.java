package com.vrms.presentation.gui.fx;

import com.vrms.common.RentalException;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * JavaFX dialog for returning a rented vehicle.
 */
public class ReturnDialogFx {

    private final ServiceBundle bundle;
    private final Runnable onSuccess;
    private final Stage stage;

    private TextField rentIdField;
    private Label errorLabel;

    public ReturnDialogFx(ServiceBundle bundle, Runnable onSuccess) {
        this.bundle = bundle;
        this.onSuccess = onSuccess;
        this.stage = new Stage();
    }

    public void showAndWait() {
        Label title = new Label("Return a Vehicle");
        title.getStyleClass().add("dialog-title");

        Label label = new Label("Rental ID");
        label.getStyleClass().add("field-label");

        rentIdField = new TextField();
        rentIdField.setPromptText("e.g. R-101");
        rentIdField.setMaxWidth(Double.MAX_VALUE);

        errorLabel = new Label();
        errorLabel.setStyle("-fx-text-fill: #f87171; -fx-font-size: 12px;");
        errorLabel.setWrapText(true);

        GridPane form = new GridPane();
        form.setHgap(12);
        form.setVgap(10);
        form.add(label, 0, 0);
        form.add(rentIdField, 1, 0);

        Button submitButton = new Button("Confirm Return");
        submitButton.getStyleClass().add("btn-primary");
        submitButton.setOnAction(e -> submit());

        Button cancelButton = new Button("Cancel");
        cancelButton.getStyleClass().add("btn-secondary");
        cancelButton.setOnAction(e -> stage.close());

        HBox buttons = new HBox(10, cancelButton, submitButton);

        VBox root = new VBox(16, title, form, errorLabel, buttons);
        root.setPadding(new Insets(24));
        root.getStyleClass().add("dialog-pane");

        Scene scene = new Scene(root, 380, 220);
        scene.getStylesheets().add(getClass().getResource("/styles.css").toExternalForm());

        stage.setTitle("Return a Vehicle");
        stage.setScene(scene);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.showAndWait();
    }

    private void submit() {
        try {
            bundle.getRentalService().returnV(rentIdField.getText().trim());
            onSuccess.run();
            stage.close();
        } catch (RentalException ex) {
            errorLabel.setText(ex.getMessage());
        }
    }
}