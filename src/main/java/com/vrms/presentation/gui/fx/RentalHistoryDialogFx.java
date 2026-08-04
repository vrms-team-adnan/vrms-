package com.vrms.presentation.gui.fx;

import com.vrms.domain.Rental;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.util.List;

/**
 * JavaFX dialog showing the rental history for a given customer id.
 */
public class RentalHistoryDialogFx {

    private final ServiceBundle bundle;
    private final Stage stage;

    private TextField customerIdField;
    private TableView<HistoryRow> table;
    private Label infoLabel;

    public RentalHistoryDialogFx(ServiceBundle bundle) {
        this.bundle = bundle;
        this.stage = new Stage();
    }

    public void showAndWait() {
        Label title = new Label("Rental History");
        title.getStyleClass().add("dialog-title");

        Label idLabel = new Label("Customer ID");
        idLabel.getStyleClass().add("field-label");

        customerIdField = new TextField();
        customerIdField.setPromptText("e.g. c1");
        customerIdField.setMaxWidth(Double.MAX_VALUE);

        Button searchButton = new Button("Search");
        searchButton.getStyleClass().add("btn-primary");
        searchButton.setOnAction(e -> search());

        HBox searchRow = new HBox(10, customerIdField, searchButton);
        searchRow.setStyle("-fx-alignment: CENTER_LEFT;");

        infoLabel = new Label("Enter a customer id and press Search.");
        infoLabel.setStyle("-fx-text-fill: #9ca3af; -fx-font-size: 12px;");

        table = new TableView<>();
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        table.setPrefHeight(220);

        TableColumn<HistoryRow, String> rentIdCol = new TableColumn<>("Rental ID");
        rentIdCol.setCellValueFactory(new javafx.scene.control.cell.PropertyValueFactory<>("rentId"));

        TableColumn<HistoryRow, String> vehicleCol = new TableColumn<>("Vehicle");
        vehicleCol.setCellValueFactory(new javafx.scene.control.cell.PropertyValueFactory<>("vehicleName"));

        TableColumn<HistoryRow, String> datesCol = new TableColumn<>("Dates");
        datesCol.setCellValueFactory(new javafx.scene.control.cell.PropertyValueFactory<>("dates"));

        TableColumn<HistoryRow, String> statusCol = new TableColumn<>("Status");
        statusCol.setCellValueFactory(new javafx.scene.control.cell.PropertyValueFactory<>("status"));

        table.getColumns().addAll(rentIdCol, vehicleCol, datesCol, statusCol);

        Button closeButton = new Button("Close");
        closeButton.getStyleClass().add("btn-secondary");
        closeButton.setOnAction(e -> stage.close());

        HBox buttons = new HBox(closeButton);

        VBox root = new VBox(14, title, searchRow, infoLabel, table, buttons);
        root.setPadding(new Insets(24));
        root.getStyleClass().add("dialog-pane");

        Scene scene = new Scene(root, 520, 460);
        scene.getStylesheets().add(getClass().getResource("/styles.css").toExternalForm());

        stage.setTitle("Rental History");
        stage.setScene(scene);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.showAndWait();
    }

    private void search() {
        String customerId = customerIdField.getText().trim();
        List<Rental> rentals = bundle.getRentalRepository().findByCustomerId(customerId);

        if (rentals.isEmpty()) {
            infoLabel.setText("No rentals found for customer \"" + customerId + "\".");
            table.setItems(FXCollections.observableArrayList());
            return;
        }

        infoLabel.setText(rentals.size() + " rental(s) found.");

        var rows = FXCollections.<HistoryRow>observableArrayList();
        for (Rental rental : rentals) {
            rows.add(new HistoryRow(
                    rental.getId(),
                    rental.getVehicle().getName(),
                    rental.getStartD() + " to " + rental.getEndD(),
                    rental.getStatus().toString()
            ));
        }
        table.setItems(rows);
    }

    /** Simple row model for the rental history table. */
    public static class HistoryRow {
        private final String rentId;
        private final String vehicleName;
        private final String dates;
        private final String status;

        public HistoryRow(String rentId, String vehicleName, String dates, String status) {
            this.rentId = rentId;
            this.vehicleName = vehicleName;
            this.dates = dates;
            this.status = status;
        }

        public String getRentId() { return rentId; }
        public String getVehicleName() { return vehicleName; }
        public String getDates() { return dates; }
        public String getStatus() { return status; }
    }
}