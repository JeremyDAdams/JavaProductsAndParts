package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.InHouse;
import model.OutSourced;
import model.Part;

import javax.swing.*;
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

    public void saveBtnClick(ActionEvent actionEvent) {
    }
}
