package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import javax.swing.*;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AddPartController implements Initializable {
    public Label addMachineOrCompanyLbl;
    Stage stage;
    Parent scene;


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

    public void saveBtnClick(ActionEvent actionEvent) {
    }
}
