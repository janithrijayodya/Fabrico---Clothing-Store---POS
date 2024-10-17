package edu.icet.controller.EmployeeReport;

import edu.icet.entity.EmployeeEntity;
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

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class employeeReportFormController implements Initializable {

    @FXML
    private Button btnEmpPrint;

    @FXML
    private TableColumn<?, ?> colEmpCompany;

    @FXML
    private TableColumn<?, ?> colEmpEmail;

    @FXML
    private TableColumn<?, ?> colEmpID;

    @FXML
    private TableColumn<?, ?> colEmpName;

    @FXML
    private Label lblDate;

    @FXML
    private TableView<EmployeeEntity> tblEmployee;

    EmployeeService employeeService = ServiceFactory.getInstance().getServiceType(ServiceType.EMPLOYEE);


    @FXML
    void btnEmpPrintOnClick(ActionEvent event) {

    }

    public void loadTable(){
        ObservableList<EmployeeEntity> load = employeeService.getAll();
        tblEmployee.setItems(load);
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        lblDate.setText(String.valueOf(LocalDate.now()));

        colEmpID.setCellValueFactory(new PropertyValueFactory<>("EmpId"));
        colEmpName.setCellValueFactory(new PropertyValueFactory<>("EmpName"));
        colEmpCompany.setCellValueFactory(new PropertyValueFactory<>("EmpCompany"));
        colEmpEmail.setCellValueFactory(new PropertyValueFactory<>("EmpEmail"));

        loadTable();
    }
}
