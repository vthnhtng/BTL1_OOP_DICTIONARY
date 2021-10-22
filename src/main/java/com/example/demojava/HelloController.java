package com.example.demojava;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.net.URL;
import java.util.PrimitiveIterator;
import java.util.ResourceBundle;

public class HelloController implements Initializable {
    @FXML
    private Button button1;

    @FXML
    private Button button2;
    @FXML
      public void handleButtonAction(ActionEvent event) throws Exception {
        Stage stage;
        Parent root;

        if(event.getSource()== button1){
            stage = (Stage) button1.getScene().getWindow();
            root = FXMLLoader.load(getClass().getResource("Translate.fxml"));
        }
        else{
            stage = (Stage) button2.getScene().getWindow();
            root = FXMLLoader.load(getClass().getResource("Bookmark.fxml"));
        }
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
}