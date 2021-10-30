package com.example.dictionaryapplication;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class DictionaryApplication extends Application {

    public static final String USERNAME_lONG = "longcule";
    public static final String USERNAME_TUNG= "v.thnh.tng";
    public static final String USERNAME_KHOIMOM = "khoimom";
    public static final String PASSWORD = "1234";


    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Login.fxml"));
        Parent root = loader.load();
        primaryStage.setTitle("Dictionary App");
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();

    }

    public static void main(String[] args) throws IOException{
        launch(args);

    }
}