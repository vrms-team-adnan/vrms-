package com.vrms.presentation.gui.fx;

import com.vrms.domain.Vehicle;
import com.vrms.domain.VehicleStatus;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.List;

/**
 * Main JavaFX screen shown after login. Displays all vehicles with their
 * status and provides actions for renting, returning, and calculating cost.
 */
public class MainScreen {

    private final ServiceBundle bundle;
    private final Stage stage;
    private TableView<VehicleRow> table;
    private ObservableList<VehicleRow> tableData;

    public MainScreen(ServiceBundle bundle, Stage stage) {
        this.bundle = bundle;
        this.stage = stage;
    }

    public void show() {
        BorderPane root = new BorderPane();
        root.setLeft(buildSidebar());
        root.setCenter(buildContent());
        root.getStyleClass().add("root");

        Scene scene = new Scene(root, 900, 620);
        scene.getStylesheets().add(getClass().getResource("/styles.css").toExternalForm());

        stage.setTitle("VRMS - Dashboard");
        stage.setScene(scene);
        stage.setResizable(true);
        stage.show();

        refreshTable();
    }

    private VBox buildSidebar() {
        Label title = new Label("VRMS");
        title.getStyleClass().add("sidebar-title");

        Label subtitle = new Label("Fleet Management");
        subtitle.getStyleClass().add("sidebar-subtitle");

        Button rentButton = new Button("＋  Rent Vehicle");
        Button returnButton = new Button("↩  Return Vehicle");
        Button costButton = new Button("＄  Calculate Cost");
        Button statsButton = new Button("📊  Statistics");
        Button historyButton = new Button("🕑  Rental History");
        Button refreshButton = new Button("⟳  Refresh");
        Button logoutButton = new Button("⏻  Logout");

        for (Button b : List.of(rentButton, returnButton, costButton, statsButton, historyButton, refreshButton)) {
            b.getStyleClass().add("btn-secondary");
            b.setMaxWidth(Double.MAX_VALUE);
        }
        logoutButton.getStyleClass().add("btn-danger");
        logoutButton.setMaxWidth(Double.MAX_VALUE);

        rentButton.setOnAction(e -> new RentDialogFx(bundle, this::refreshTable).showAndWait());
        returnButton.setOnAction(e -> new ReturnDialogFx(bundle, this::refreshTable).showAndWait());
        costButton.setOnAction(e -> new CostDialogFx(bundle).showAndWait());
        statsButton.setOnAction(e -> new StatisticsDialogFx(bundle).showAndWait());
        historyButton.setOnAction(e -> new RentalHistoryDialogFx(bundle).showAndWait());
        refreshButton.setOnAction(e -> refreshTable());
        logoutButton.setOnAction(e -> {
            bundle.getAuthService().logout();
            new LoginScreen(bundle, stage).show();
        });

        VBox sidebar = new VBox(10);
        sidebar.getStyleClass().add("sidebar");
        sidebar.setPrefWidth(220);
        sidebar.getChildren().addAll(
                title, subtitle, spacer(20),
                rentButton, returnButton, costButton, statsButton, historyButton, refreshButton,
                spacer(0), grow(), logoutButton
        );
        return sidebar;
    }

    private VBox buildContent() {
        Label header = new Label("Vehicle Fleet");
        header.getStyleClass().add("page-header");

        table = new TableView<>();
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        TableColumn<VehicleRow, String> idCol = new TableColumn<>("ID");
        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));

        TableColumn<VehicleRow, String> nameCol = new TableColumn<>("Name");
        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));

        TableColumn<VehicleRow, String> typeCol = new TableColumn<>("Type");
        typeCol.setCellValueFactory(new PropertyValueFactory<>("type"));

        TableColumn<VehicleRow, String> statusCol = new TableColumn<>("Status");
        statusCol.setCellValueFactory(new PropertyValueFactory<>("status"));
        statusCol.setCellFactory(col -> new javafx.scene.control.TableCell<>() {
            @Override
            protected void updateItem(String status, boolean empty) {
                super.updateItem(status, empty);
                if (empty || status == null) {
                    setText(null);
                    getStyleClass().removeAll("status-available", "status-rented");
                } else {
                    setText(status);
                    getStyleClass().removeAll("status-available", "status-rented");
                    getStyleClass().add("AVAILABLE".equals(status) ? "status-available" : "status-rented");
                }
            }
        });

        table.getColumns().addAll(idCol, nameCol, typeCol, statusCol);
        VBox.setVgrow(table, Priority.ALWAYS);

        VBox content = new VBox(18, header, table);
        content.getStyleClass().add("content-area");
        return content;
    }

    private void refreshTable() {
        List<Vehicle> vehicles = bundle.getVehicleRepository().findAll();
        tableData = FXCollections.observableArrayList();
        for (Vehicle v : vehicles) {
            tableData.add(new VehicleRow(
                    v.getId(),
                    v.getName(),
                    v.getClass().getSimpleName(),
                    v.getStatus() == VehicleStatus.AVAILABLE ? "AVAILABLE" : "RENTED"
            ));
        }
        table.setItems(tableData);
    }

    private VBox spacer(double height) {
        VBox box = new VBox();
        box.setPrefHeight(height);
        return box;
    }

    private VBox grow() {
        VBox box = new VBox();
        VBox.setVgrow(box, Priority.ALWAYS);
        return box;
    }

    /** Simple row model for the table (JavaFX TableView needs getters matching property names). */
    public static class VehicleRow {
        private final String id;
        private final String name;
        private final String type;
        private final String status;

        public VehicleRow(String id, String name, String type, String status) {
            this.id = id;
            this.name = name;
            this.type = type;
            this.status = status;
        }

        public String getId() { return id; }
        public String getName() { return name; }
        public String getType() { return type; }
        public String getStatus() { return status; }
    }
}