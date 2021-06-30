package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import javax.swing.*;
import java.io.IOException;
import java.net.URL;
import java.util.EventObject;
import java.util.ResourceBundle;

public class AddPartController implements Initializable {
    public Label addMachineOrCompanyLbl;
    Stage stage;
    Parent scene;

    @FXML
    private TextField partsIdTxt;

    @FXML
    private TextField partsNameTxt;

    @FXML
    private TextField partsInvTxt;

    @FXML
    private TextField partsPriceTxt;

    @FXML
    private TextField partsMaxTxt;

    @FXML
    private TextField partsMinTxt;

    @FXML
    private TextField partsHouseTxt;

    //had an error here accidently using just "Button"
    @FXML
    private RadioButton addHouseBtn;

    @FXML
    private RadioButton addOutsourced;


/*


    @FXML
    private Button addHouseBtn;
*/
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        /*if(addHouseBtn.isSelected())
            addMachineOrCompanyLbl.setText("Machine ID");
        else
            addMachineOrCompanyLbl.setText("Company Name");
         */
    }


    @FXML
    void cancelBtnClick(ActionEvent event) throws IOException {
        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/MainMenu.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    //@FXML
    //private Label addMachineOrCompanyLbl;

    @FXML
    void houseBtnClick(ActionEvent actionEvent) {
        addMachineOrCompanyLbl.setText("Machine ID");
    }

    @FXML
    void outSourcedBtnClick(ActionEvent actionEvent) {
        addMachineOrCompanyLbl.setText("Company Name");
    }

    public void saveBtnClick(ActionEvent event) throws IOException{
/*
        try {
            int id = 0;
            String name = partsNameTxt.getText();
            Double price = Double.parseDouble(partsPriceTxt.getText());
            int stock = Integer.parseInt(partsInvTxt.getText());
            int min = Integer.parseInt(partsMinTxt.getText());
            int max = Integer.parseInt(partsMaxTxt.getText());

            if(name.isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setContentText("Please enter a valid value for each text field.");
                alert.showAndWait();
            } else {
                if(min < max && stock <= max && stock >= min) {
                    if(addHouseBtn.isSelected()) {
                        try {
                            int machineId = Integer.parseInt(partsHouseTxt.getText());

                        }
                    }
                }
            }
        }
*/

        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/MainMenu.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }
}
