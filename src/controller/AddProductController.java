package controller;

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
import model.*;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class AddProductController implements Initializable {
    Stage stage;
    Parent scene;
    public static Part partSelected;
    private ObservableList<Part> partsAssociated = FXCollections.observableArrayList();

    @FXML
    private TableView<Part> partsTableView;

    @FXML
    private TableColumn<Part, Integer> partsIdCol;

    @FXML
    private TableColumn<Part, String> partsNameCol;

    @FXML
    private TableColumn<Part, Integer> partsInvCol;

    @FXML
    private TableColumn<Part, Double> partsPriceCol;

    @FXML
    private TableView<Part> associatedPartsTableView;

    @FXML
    private TableColumn<Part, Integer> associatedPartsIdCol;

    @FXML
    private TableColumn<Part, String> associatedPartsNameCol;

    @FXML
    private TableColumn<Part, Integer> associatedPartsInvCol;

    @FXML
    private TableColumn<Part, Double> associatedPartsPriceCol;

    @FXML
    private TextField partSearch;

    @FXML
    private TextField prodName;

    @FXML
    private TextField prodInv;

    @FXML
    private TextField prodPrice;

    @FXML
    private TextField prodMax;

    @FXML
    private TextField prodMin;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        partsTableView.setItems(Inventory.getAllParts());
        partsIdCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        partsNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        partsInvCol.setCellValueFactory(new PropertyValueFactory<>("stock"));
        partsPriceCol.setCellValueFactory(new PropertyValueFactory<>("price"));

        associatedPartsIdCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        associatedPartsNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        associatedPartsInvCol.setCellValueFactory(new PropertyValueFactory<>("stock"));
        associatedPartsPriceCol.setCellValueFactory(new PropertyValueFactory<>("price"));

    }

    @FXML
    void cancelBtnClick(ActionEvent event) throws IOException {
        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/MainMenu.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    public void saveBtnClick(ActionEvent actionEvent) {
        try {
            int id = 0;
            String name = prodName.getText();
            Double price = Double.parseDouble(prodPrice.getText());
            int stock = Integer.parseInt(prodInv.getText());
            int min = Integer.parseInt(prodMin.getText());
            int max = Integer.parseInt(prodMax.getText());
            boolean productAdded = false;

            if(name.isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setContentText("Please enter a valid value for each text field.");
                alert.showAndWait();
            } else {
                if(min < max && stock <= max && stock >= min) {
                    try {
                        Product product = new Product(Inventory.createProductId(), name, price, stock, min, max);
                        for (Part part : partsAssociated) {
                            product.addAssociatedPart(part);
                        }
                        Inventory.addProduct(product);
                        productAdded = true;
                    } catch (Exception e) {
                        Alert alert2 = new Alert(Alert.AlertType.ERROR);
                        alert2.setTitle("Error");
                        alert2.setContentText("Please enter a valid value for each field.");
                        alert2.showAndWait();
                    }

                    if(productAdded) {
                        stage = (Stage)((Button)actionEvent.getSource()).getScene().getWindow();
                        scene = FXMLLoader.load(getClass().getResource("/view/MainMenu.fxml"));
                        stage.setScene(new Scene(scene));
                        stage.show();
                    }
                } else {
                    Alert alert2 = new Alert(Alert.AlertType.ERROR);
                    alert2.setTitle("Error");
                    alert2.setContentText("Inventory, max, and min most be compatible.");
                    alert2.showAndWait();
                }
            }
        } catch(Exception e) {
            Alert alert2 = new Alert(Alert.AlertType.ERROR);
            alert2.setTitle("Error");
            alert2.setContentText("Please enter a valid value for each field.");
            alert2.showAndWait();
        }
    }

    public void removeBtnClick(ActionEvent actionEvent) {
        partSelected = associatedPartsTableView.getSelectionModel().getSelectedItem();
        if (partSelected == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText("A part must be selected.");
            alert.showAndWait();
        } else {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Warning");
            alert.setContentText("Are you sure you want to remove the part: " + partSelected.getName() + "?");
            Optional<ButtonType> result = alert.showAndWait();

            if (result.isPresent() && result.get() == ButtonType.OK) {
                partsAssociated.remove(partSelected);
                associatedPartsTableView.setItems(partsAssociated);
            }
        }
    }

    public void addBtnClick(ActionEvent actionEvent) {
        partSelected = partsTableView.getSelectionModel().getSelectedItem();
        if (partSelected == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText("A part must be selected.");
            alert.showAndWait();
        } else {
            partsAssociated.add(partSelected);
            associatedPartsTableView.setItems(partsAssociated);
        }
    }

    @FXML
    public void onActionSearchPart(ActionEvent actionEvent) {
        ObservableList<Part> unfilteredList = Inventory.getAllParts();
        ObservableList<Part> filteredList = FXCollections.observableArrayList();
        String searchBar = partSearch.getText();

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
}
