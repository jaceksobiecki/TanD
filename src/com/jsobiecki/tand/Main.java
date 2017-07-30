package com.jsobiecki.tand;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("tand");
        primaryStage.setScene(new Scene(root, 500, 600));
        primaryStage.show();
        primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent t) {
                Platform.exit();
                try{
                    FileOutputStream fos= new FileOutputStream("dateArray");
                    ObjectOutputStream oos= new ObjectOutputStream(fos);
                    oos.writeObject(Controller.getDateArray());
                    oos.close();
                    fos.close();
                }catch(IOException ioe){
                    ioe.printStackTrace();
                }
                System.exit(0);
            }
        });
    }


    public static void main(String[] args) {
        launch(args);
    }
}
