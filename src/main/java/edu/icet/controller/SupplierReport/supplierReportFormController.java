package edu.icet.controller.SupplierReport;

import edu.icet.entity.SupplierEntity;
import edu.icet.util.ServiceType;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import service.ServiceFactory;
import service.custom.EmployeeService;
import service.custom.SupplierService;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class supplierReportFormController implements Initializable {
    @FXML
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
    private TableView<SupplierEntity> tblSupplier;

    SupplierService supplierService = ServiceFactory.getInstance().getServiceType(ServiceType.SUPPLIER);

    public void loadTable(){
        ObservableList<SupplierEntity> load = supplierService.getAll();
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

    @FXML
    void btnSupPrintOnClick(ActionEvent event) {

    }
}
