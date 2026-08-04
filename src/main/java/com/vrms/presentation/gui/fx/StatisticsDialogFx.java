package com.vrms.presentation.gui.fx;

import com.vrms.application.VehicleStatistics;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.util.Map;

/**
 * JavaFX dialog showing simple fleet statistics: available/rented
 * counts and count by vehicle type.
 */
public class StatisticsDialogFx {

    private final ServiceBundle bundle;
    private final Stage stage;

    public StatisticsDialogFx(ServiceBundle bundle) {
        this.bundle = bundle;
        this.stage = new Stage();
    }

    public void showAndWait() {
        VehicleStatistics stats = new VehicleStatistics(bundle.getVehicleRepository());

        Label title = new Label("Fleet Statistics");
        title.getStyleClass().add("dialog-title");

        VBox summaryBox = new VBox(10);
        summaryBox.getStyleClass().add("result-box");
        summaryBox.getChildren().addAll(
                statRow("Total Vehicles", String.valueOf(stats.getTotalCount())),
                statRow("Available", String.valueOf(stats.getAvailableCount())),
                statRow("Rented", String.valueOf(stats.getRentedCount()))
        );

        Label byTypeTitle = new Label("By Type");
        byTypeTitle.getStyleClass().add("field-label");

        VBox typeBox = new VBox(8);
        typeBox.getStyleClass().add("result-box");
        Map<String, Integer> byType = stats.getCountByType();
        if (byType.isEmpty()) {
            typeBox.getChildren().add(new Label("No vehicles in the fleet."));
        } else {
            for (Map.Entry<String, Integer> entry : byType.entrySet()) {
                typeBox.getChildren().add(statRow(entry.getKey(), String.valueOf(entry.getValue())));
            }
        }

        Button closeButton = new Button("Close");
        closeButton.getStyleClass().add("btn-secondary");
        closeButton.setOnAction(e -> stage.close());

        HBox buttons = new HBox(closeButton);

        VBox root = new VBox(16, title, summaryBox, byTypeTitle, typeBox, buttons);
        root.setPadding(new Insets(24));
        root.getStyleClass().add("dialog-pane");

        Scene scene = new Scene(root, 380, 480);
        scene.getStylesheets().add(getClass().getResource("/styles.css").toExternalForm());

        stage.setTitle("Fleet Statistics");
        stage.setScene(scene);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.showAndWait();
    }

    private HBox statRow(String label, String value) {
        Label labelNode = new Label(label);
        labelNode.setStyle("-fx-text-fill: #9ca3af; -fx-font-size: 13px;");

        Label valueNode = new Label(value);
        valueNode.setStyle("-fx-text-fill: #ffffff; -fx-font-size: 15px; -fx-font-weight: bold;");

        javafx.scene.layout.Region spacer = new javafx.scene.layout.Region();
        HBox.setHgrow(spacer, javafx.scene.layout.Priority.ALWAYS);

        HBox row = new HBox(labelNode, spacer, valueNode);
        row.setAlignment(javafx.geometry.Pos.CENTER_LEFT);
        return row;
    }
}