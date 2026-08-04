package com.vrms.presentation.gui.fx;

import com.vrms.common.RentalException;
import com.vrms.domain.Customer;
import com.vrms.domain.Vehicle;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.time.LocalDate;
import java.util.List;

/**
 * JavaFX dialog for renting a vehicle.
 */
public class RentDialogFx {

    private final ServiceBundle bundle;
    private final Runnable onSuccess;
    private final Stage stage;

    private ComboBox<Vehicle> vehicleCombo;
    private TextField rentIdField;
    private TextField customerIdField;
    private TextField customerNameField;
    private TextField ageField;
    private CheckBox specialLicenseCheck;
    private DatePicker startDatePicker;
    private DatePicker endDatePicker;
    private Label errorLabel;

    public RentDialogFx(ServiceBundle bundle, Runnable onSuccess) {
        this.bundle = bundle;
        this.onSuccess = onSuccess;
        this.stage = new Stage();
    }

    public void showAndWait() {
        Label title = new Label("Rent a Vehicle");
        title.getStyleClass().add("dialog-title");

        vehicleCombo = new ComboBox<>();
        List<Vehicle> available = bundle.getVehicleService().getAvailableVehicles();
        vehicleCombo.getItems().addAll(available);
        vehicleCombo.setMaxWidth(Double.MAX_VALUE);
        if (!available.isEmpty()) {
            vehicleCombo.getSelectionModel().selectFirst();
        }

        rentIdField = new TextField();
        rentIdField.setPromptText("e.g. R-101");

        customerIdField = new TextField();
        customerIdField.setPromptText("e.g. C-01");

        customerNameField = new TextField();
        customerNameField.setPromptText("Full name");

        ageField = new TextField();
        ageField.setPromptText("e.g. 25");

        specialLicenseCheck = new CheckBox("Customer has special license");

        startDatePicker = new DatePicker();
        endDatePicker = new DatePicker();

        errorLabel = new Label();
        errorLabel.setStyle("-fx-text-fill: #f87171; -fx-font-size: 12px;");
        errorLabel.setWrapText(true);

        GridPane form = new GridPane();
        form.setHgap(14);
        form.setVgap(14);
        int row = 0;
        row = addRow(form, row, "Available Vehicle", vehicleCombo);
        row = addRow(form, row, "Rental ID", rentIdField);
        row = addRow(form, row, "Customer ID", customerIdField);
        row = addRow(form, row, "Customer Name", customerNameField);
        row = addRow(form, row, "Customer Age", ageField);
        form.add(specialLicenseCheck, 1, row++);
        row = addRow(form, row, "Start Date", startDatePicker);
        row = addRow(form, row, "End Date", endDatePicker);

        Button submitButton = new Button("Confirm Rental");
        submitButton.getStyleClass().add("btn-primary");
        submitButton.setOnAction(e -> submit());

        Button cancelButton = new Button("Cancel");
        cancelButton.getStyleClass().add("btn-secondary");
        cancelButton.setOnAction(e -> stage.close());

        HBox buttons = new HBox(10, cancelButton, submitButton);

        VBox root = new VBox(18, title, form, errorLabel, buttons);
        root.setPadding(new Insets(28));
        root.getStyleClass().add("dialog-pane");

        Scene scene = new Scene(root, 520, 620);
        scene.getStylesheets().add(getClass().getResource("/styles.css").toExternalForm());

        stage.setTitle("Rent a Vehicle");
        stage.setScene(scene);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.showAndWait();
    }

    private int addRow(GridPane form, int row, String labelText, javafx.scene.Node field) {
        Label label = new Label(labelText);
        label.getStyleClass().add("field-label");
        form.add(label, 0, row);
        form.add(field, 1, row);
        if (field instanceof javafx.scene.control.Control) {
            ((javafx.scene.control.Control) field).setMaxWidth(Double.MAX_VALUE);
        }
        return row + 1;
    }

    private void submit() {
        errorLabel.setText("");

        Vehicle selectedVehicle = vehicleCombo.getSelectionModel().getSelectedItem();
        if (selectedVehicle == null) {
            errorLabel.setText("No available vehicle to rent.");
            return;
        }

        int age;
        try {
            age = Integer.parseInt(ageField.getText().trim());
        } catch (NumberFormatException ex) {
            errorLabel.setText("Customer age must be a number.");
            return;
        }

        LocalDate startDate = startDatePicker.getValue();
        LocalDate endDate = endDatePicker.getValue();

        if (startDate == null || endDate == null) {
            errorLabel.setText("Please select both start and end dates.");
            return;
        }

        Customer customer = new Customer(
                customerIdField.getText().trim(),
                customerNameField.getText().trim(),
                age,
                specialLicenseCheck.isSelected()
        );

        try {
            bundle.getRentalService().rentVehicle(
                    rentIdField.getText().trim(), selectedVehicle, startDate, endDate, customer
            );
            onSuccess.run();
            stage.close();
        } catch (RentalException ex) {
            errorLabel.setText(ex.getMessage());
        }
    }
}