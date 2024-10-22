package edu.icet.controller.Product;

import edu.icet.controller.MakeOrder.makeOrderFormController;
import edu.icet.entity.ProductEntity;
import edu.icet.util.ServiceType;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import service.ServiceFactory;
import service.custom.ProductService;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class manageProductFormController implements Initializable {

    @FXML
    public Button btnProductAdd;
    public TextField txtSupplierID;
    @FXML
    public ComboBox comboSupplierID;
    @FXML
    private Button btnAddImage;

    @FXML
    private ImageView imageView;

    @FXML
    private Label lblImage;

    @FXML
    private TextField txtProductId;

    @FXML
    private TextField txtProductName;

    @FXML
    private TextField txtProductPrice;

    @FXML
    private TextField txtProductQty;

    @FXML
    private TextField txtProductSize;

    @FXML
    private TextField txtProductType;

    @FXML
    private byte[] imageBytes;


    ProductService productService = ServiceFactory.getInstance().getServiceType(ServiceType.PRODUCT);

    @FXML
    void btnAddImageOnClick(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Image Files", "*.png","*.jpg" ,"*.jpeg"));

        File selectedFile = fileChooser.showOpenDialog(btnAddImage.getScene().getWindow());

        if(selectedFile != null){
            imageView.setImage(new Image(selectedFile.toURI().toString()));
            lblImage.setText(selectedFile.getAbsolutePath());

            try {
               imageBytes = Files.readAllBytes(selectedFile.toPath());
                System.out.println(imageBytes);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }else{
            lblImage.setText("No image selected");
        }
    }


    public void btnProductAddOnClick(ActionEvent actionEvent) {

        ProductEntity product = new ProductEntity(
                txtProductId.getText(),
                txtProductName.getText(),
                txtProductSize.getText(),
                Double.parseDouble(txtProductPrice.getText()),
                Integer.parseInt(txtProductQty.getText()),
                (String) comboSupplierID.getValue(),
                txtProductType.getText()
//                imageBytes
        );

       if(productService.addProduct(product)){
           clearForm();
           getGeneratedID();
           new Alert(Alert.AlertType.INFORMATION,"Product has been added !!").show();
       }else {
           clearForm();
           getGeneratedID();
           new Alert(Alert.AlertType.INFORMATION,"NOT added !!").show();
       }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        List<String> SupplierIDs = productService.getSuppliers();
        comboSupplierID.getItems().addAll(SupplierIDs);

        getGeneratedID();

    }

    public void getGeneratedID(){
        String setProductID = productService.generateProductID();
        txtProductId.setText(setProductID);
    }

    public void clearForm(){
        txtProductType.clear();
        txtProductId.clear();
        txtProductSize.clear();
        txtProductPrice.clear();
        txtProductQty.clear();
        txtProductName.clear();
        comboSupplierID.setValue(null);
    }
}

