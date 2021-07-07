package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import model.InHouse;
import model.Inventory;
import model.OutSourced;
import model.Part;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ModifyPartController implements Initializable {
    //public Label modMachineOrCompanyLbl;
    Stage stage;
    Parent scene;

    @FXML
    private TextField modPartId;

    @FXML
    private TextField modPartName;

    @FXML
    private TextField modPartInv;

    @FXML
    private TextField modPartPrice;

    @FXML
    private TextField modPartMax;

    @FXML
    private TextField modPartMachine;

    @FXML
    private TextField modPartMin;

    @FXML
    private RadioButton modHouseBtn;

    @FXML
    private RadioButton modOutsourcedBtn;

    @FXML
    private Label modMachineOrCompanyLbl;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Part partSelected = MainMenuController.partSelected;

        if (partSelected instanceof InHouse) {
            modHouseBtn.setSelected(true);
            modMachineOrCompanyLbl.setText("Machine ID");
            modPartMachine.setText(String.valueOf(((InHouse) partSelected).getMachineId()));
        } else {
            modOutsourcedBtn.setSelected(true);
            modMachineOrCompanyLbl.setText("Company Name");
            modPartMachine.setText(String.valueOf(((OutSourced) partSelected).getCompanyName()));
        }

        modPartId.setText(String.valueOf(partSelected.getId()));
        modPartName.setText(partSelected.getName());
        modPartInv.setText(String.valueOf(partSelected.getStock()));
        modPartPrice.setText(String.valueOf(partSelected.getPrice()));
        modPartMax.setText(String.valueOf(partSelected.getMax()));
        modPartMin.setText(String.valueOf(partSelected.getMin()));
    }

    @FXML
    void cancelBtnClick(ActionEvent event) throws IOException {
        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/MainMenu.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    @FXML
    void houseBtnClick(ActionEvent actionEvent) {
        modMachineOrCompanyLbl.setText("Machine ID");
    }

    @FXML
    void outSourcedBtnClick(ActionEvent actionEvent) {
        modMachineOrCompanyLbl.setText("Company Name");
    }

    public void saveBtnClick(ActionEvent event) {
        Part partSelected = MainMenuController.partSelected;
        try {
            int id = partSelected.getId();
            String name = modPartName.getText();
            Double price = Double.parseDouble(modPartPrice.getText());
            int stock = Integer.parseInt(modPartInv.getText());
            int min = Integer.parseInt(modPartMin.getText());
            int max = Integer.parseInt(modPartMax.getText());
            int machineId;
            String companyName;
            boolean partModded = false;

            if(name.isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setContentText("Please enter a valid value for each text field.");
                alert.showAndWait();
            } else {
                if(min < max && stock <= max && stock >= min) {
                    if(modHouseBtn.isSelected()) {
                        try {
                            machineId = Integer.parseInt(modPartMachine.getText());
                            InHouse inHousePart = new InHouse(id, name, price, stock, min, max, machineId);
                            Inventory.addPart(inHousePart);

                            partModded = true;
                        } catch (Exception e) {
                            Alert alert2 = new Alert(Alert.AlertType.ERROR);
                            alert2.setTitle("Error");
                            alert2.setContentText("Please enter a valid value for each field.");
                            alert2.showAndWait();
                        }
                    }
                    if(modOutsourcedBtn.isSelected()) {
                        companyName = modPartMachine.getText();
                        OutSourced outSourcedPart = new OutSourced(id, name, price, stock, min, max, companyName);
                        Inventory.addPart(outSourcedPart);
                        partModded = true;
                    }
                    if(partModded) {
                        Inventory.deletePart(partSelected);
                        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
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
}
