package edu.icet.controller.SupplierReport;

import edu.icet.model.Supplier;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class supplierReportFormController implements Initializable {

    public Label lblDate;
    @FXML
    private Button btnSupPrint;

    @FXML
    private Button btnSupReload;

    @FXML
    private TableColumn<?, ?> colSupplierCompany;

    @FXML
    private TableColumn<?, ?> colSupplierEmail;

    @FXML
    private TableColumn<?, ?> colSupplierID;

    @FXML
    private TableColumn<?, ?> colSupplierName;

    @FXML
    private TableView<Supplier> tblSupplier;

    suppplierReportService service = supplierReportController.getInstance();

    @FXML
    void btnSupPrintOnClick(ActionEvent event) {

    }


    public void loadTable(){
        ObservableList<Supplier> load = service.getAll();
        tblSupplier.setItems(load);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        lblDate.setText(String.valueOf(LocalDate.now()));

        colSupplierID.setCellValueFactory(new PropertyValueFactory<>("SupplierID"));
        colSupplierName.setCellValueFactory(new PropertyValueFactory<>("SupplierName"));
        colSupplierCompany.setCellValueFactory(new PropertyValueFactory<>("SupplierCompany"));
        colSupplierEmail.setCellValueFactory(new PropertyValueFactory<>("SupplierEmail"));
        loadTable();
    }
}
