package edu.icet.controller.EmployeeReport;

import edu.icet.model.Employee;
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
    private TableView<Employee> tblEmployee;

    employeeReportService service = employeeReportController.getInstance();

    @FXML
    void btnEmpPrintOnClick(ActionEvent event) {

    }

    public void loadTable(){
        ObservableList<Employee> load = service.getAll();
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
