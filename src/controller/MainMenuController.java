package controller;

import com.sun.javafx.charts.Legend;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.Inventory;
import model.Part;
import model.Product;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainMenuController implements Initializable {

    //public Label TheLabel;

    Stage stage;
    Parent scene;
    public static Part partSelected;
    public static Product productSelected;

    //Parts table
    @FXML
    //I tried to use <Inventory> at first.
    private TableView<Part> partsTableView;

    @FXML
    private TableColumn<Part, Integer> partsIdCol;

    @FXML
    private TableColumn<Part, String> partsNameCol;

    @FXML
    private TableColumn<Part, Integer> partsInvCol;

    @FXML
    private TableColumn<Part, Double> partsPriceCol;

    //Products table

    @FXML
    private TableView<Product> productsTableView;

    @FXML
    private TableColumn<Product, Integer> prodIdCol;

    @FXML
    private TableColumn<Product, String> prodNameCol;

    @FXML
    private TableColumn<Product, Integer> prodInvCol;

    @FXML
    private TableColumn<Product, Double> prodPriceCol;

    @FXML
    private TextField partSearch;

    @FXML
    private TextField productSearch;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        partsTableView.setItems(Inventory.getAllParts());
        partsIdCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        partsNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        partsInvCol.setCellValueFactory(new PropertyValueFactory<>("stock"));
        partsPriceCol.setCellValueFactory(new PropertyValueFactory<>("price"));

        productsTableView.setItems(Inventory.getAllProducts());
        prodIdCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        prodNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        prodInvCol.setCellValueFactory(new PropertyValueFactory<>("stock"));
        prodPriceCol.setCellValueFactory(new PropertyValueFactory<>("price"));
    }

    @FXML
    void onActionAddPart(ActionEvent event) throws IOException {
        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/AddPart.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    @FXML
    void onActionModifyPart(ActionEvent event) throws IOException {
        partSelected = partsTableView.getSelectionModel().getSelectedItem();
        if(partSelected == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText("A part must be selected.");
            alert.showAndWait();
        } else {
            stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
            scene = FXMLLoader.load(getClass().getResource("/view/ModifyPart.fxml"));
            stage.setScene(new Scene(scene));
            stage.show();
        }
    }

    @FXML
    void onActionAddProduct(ActionEvent event) throws IOException {
        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/AddProduct.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    @FXML
    void onActionModifyProduct(ActionEvent event) throws IOException {
        productSelected = productsTableView.getSelectionModel().getSelectedItem();
        if(productSelected == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText("A part must be selected.");
            alert.showAndWait();
        } else {
            stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
            scene = FXMLLoader.load(getClass().getResource("/view/ModifyProduct.fxml"));
            stage.setScene(new Scene(scene));
            stage.show();
        }
    }

    @FXML
    void onActionExit(ActionEvent event) {
        System.exit(0);
    }

    @FXML
    public void onActionSearchPart(ActionEvent actionEvent) {
        ObservableList<Part> unfilteredList = Inventory.getAllParts();
        ObservableList<Part> filteredList = FXCollections.observableArrayList();
        String searchBar = partSearch.getText();

        //I couldn't get this to work so I just wrote logic to execute the search here in the controller.
        //filteredList.add((Part) Inventory.lookupPart(searchBar));

        for (Part part: unfilteredList) {
            if (String.valueOf(part.getId()).contains(searchBar)) {
                filteredList.add(part);
            }
            if (String.valueOf(part.getName()).contains(searchBar)) {
                filteredList.add(part);
            }
        }

        partsTableView.setItems(filteredList);

        if (searchBar.isEmpty()) {
            partsTableView.setItems(unfilteredList);
        }

        if (filteredList.size() == 0) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText("No matching parts found.");
            alert.showAndWait();
        }
    }

    @FXML
    public void onActionSearchProduct(ActionEvent actionEvent) {
        ObservableList<Product> unfilteredList = Inventory.getAllProducts();
        ObservableList<Product> filteredList = FXCollections.observableArrayList();
        String searchBar = productSearch.getText();

        for (Product product: unfilteredList) {
            if (String.valueOf(product.getId()).contains(searchBar)) {
                filteredList.add(product);
            }
            if (String.valueOf(product.getName()).contains(searchBar)) {
                filteredList.add(product);
            }
        }
        productsTableView.setItems(filteredList);

        if (searchBar.isEmpty()) {
            productsTableView.setItems(unfilteredList);
        }

        if (filteredList.size() == 0) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText("No matching products found.");
            alert.showAndWait();
        }
    }


    @FXML
    public void onActionDeletePart(ActionEvent actionEvent) {
        partSelected = partsTableView.getSelectionModel().getSelectedItem();
        Inventory.deletePart(partSelected);
        if (partSelected == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText("You must select a part to be deleted.");
            alert.showAndWait();
        }
    }

    @FXML
    public void onActionDeleteProduct(ActionEvent actionEvent) {
        productSelected = productsTableView.getSelectionModel().getSelectedItem();
        Inventory.deleteProduct(productSelected);
        if (productSelected == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText("You must select a product to be deleted.");
            alert.showAndWait();
        }
    }
}
