package com.vrms.presentation.gui.fx;

import com.vrms.common.RentalException;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.time.LocalDate;

/**
 * JavaFX dialog for calculating rental cost, late fee, and total cost.
 */
public class CostDialogFx {

    private final ServiceBundle bundle;
    private final Stage stage;

    private TextField rentIdField;
    private DatePicker returnDatePicker;
    private Label resultLabel;
    private VBox resultBox;

    public CostDialogFx(ServiceBundle bundle) {
        this.bundle = bundle;
        this.stage = new Stage();
    }

    public void showAndWait() {
        Label title = new Label("Calculate Rental Cost");
        title.getStyleClass().add("dialog-title");

        Label idLabel = new Label("Rental ID");
        idLabel.getStyleClass().add("field-label");
        rentIdField = new TextField();
        rentIdField.setPromptText("e.g. R-101");
        rentIdField.setMaxWidth(Double.MAX_VALUE);

        Label dateLabel = new Label("Return Date");
        dateLabel.getStyleClass().add("field-label");
        returnDatePicker = new DatePicker();
        returnDatePicker.setMaxWidth(Double.MAX_VALUE);

        GridPane form = new GridPane();
        form.setHgap(12);
        form.setVgap(10);
        form.add(idLabel, 0, 0);
        form.add(rentIdField, 1, 0);
        form.add(dateLabel, 0, 1);
        form.add(returnDatePicker, 1, 1);

        resultLabel = new Label("Results will appear here.");
        resultLabel.setStyle("-fx-text-fill: #9ca3af; -fx-font-size: 13px;");
        resultLabel.setWrapText(true);

        resultBox = new VBox(resultLabel);
        resultBox.getStyleClass().add("result-box");

        Button calculateButton = new Button("Calculate");
        calculateButton.getStyleClass().add("btn-primary");
        calculateButton.setOnAction(e -> calculate());

        Button closeButton = new Button("Close");
        closeButton.getStyleClass().add("btn-secondary");
        closeButton.setOnAction(e -> stage.close());

        HBox buttons = new HBox(10, closeButton, calculateButton);

        VBox root = new VBox(16, title, form, resultBox, buttons);
        root.setPadding(new Insets(24));
        root.getStyleClass().add("dialog-pane");

        Scene scene = new Scene(root, 450, 420);
        scene.getStylesheets().add(getClass().getResource("/styles.css").toExternalForm());

        stage.setTitle("Calculate Rental Cost");
        stage.setScene(scene);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.showAndWait();
    }

    private void calculate() {
        String rentId = rentIdField.getText().trim();
        LocalDate returnDate = returnDatePicker.getValue();

        if (returnDate == null) {
            resultLabel.setStyle("-fx-text-fill: #f87171; -fx-font-size: 13px;");
            resultLabel.setText("Please select a return date.");
            return;
        }

        try {
            double rentalCost = bundle.getRentalService().costrental(rentId);
            double lateFee = bundle.getRentalService().costlate(rentId, returnDate);
            double total = bundle.getRentalService().totalcost(rentId, returnDate);

            resultLabel.setStyle("-fx-text-fill: #e5e7eb; -fx-font-size: 14px;");
            resultLabel.setText(String.format(
                    "Rental cost:  %.2f%nLate fee:       %.2f%nTotal:            %.2f",
                    rentalCost, lateFee, total
            ));
        } catch (RentalException ex) {
            resultLabel.setStyle("-fx-text-fill: #f87171; -fx-font-size: 13px;");
            resultLabel.setText(ex.getMessage());
        }
    }
}