/*
 * Copyright (c) 2012 Oracle and/or its affiliates.
 * All rights reserved. Use is subject to license terms.
 *
 * This file is available and licensed under the following license:
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 *  - Redistributions of source code must retain the above copyright
 *    notice, this list of conditions and the following disclaimer.
 *  - Redistributions in binary form must reproduce the above copyright
 *    notice, this list of conditions and the following disclaimer in
 *    the documentation and/or other materials provided with the distribution.
 *  - Neither the name of Oracle Corporation nor the names of its
 *    contributors may be used to endorse or promote products derived
 *    from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT
 * LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR
 * A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT
 * OWNER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,
 * SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT
 * LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE,
 * DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY
 * THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
 * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
package webviewbrowser;

import java.io.BufferedInputStream;
import java.net.URL;
import java.util.Date;
import java.util.List;
import webviewbrowser.HttpDownloadUtility;
import webviewbrowser.HttpDownloader;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.VPos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebEvent;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

 
/**
 * Demonstrates a WebView object accessing a web page.
 *
 * @see javafx.scene.web.WebView
 * @see javafx.scene.web.WebEngine
 */
public class WebViewBrowser extends Application {
 
    @Override public void start(Stage primaryStage) throws Exception {
        Pane root = new WebViewPane();
        primaryStage.setScene(new Scene(root, 1024, 650));
        primaryStage.show();
    }


    /**
     * The main() method is ignored in correctly deployed JavaFX 
     * application. main() serves only as fallback in case the 
     * application can not be launched through deployment artifacts,
     * e.g., in IDEs with limited FX support. NetBeans ignores main().
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        pesan();
        launch(args);
       // HttpDownloader donlot = new HttpDownloader();
    }

    
      public void exit() {
            Platform.exit();
         }
         //public void print(Date date) {
         //    System.out.println("Parm:"+date);
         // }
         public void print(long date) {
             System.out.println("Parm:"+new Date(date));
          }
         public Date getValue() {
             return new Date();
          }

         public static void pesan(){
             JOptionPane.showMessageDialog(null, "Selamat Datang Di Aplikasi SeMUTSoft.");
         }
         
         
    /**
     * Create a  WebView pane
     */
    public class WebViewPane extends Pane {
           
        
      
         
        public WebViewPane() {
            VBox.setVgrow(this, Priority.ALWAYS);
            setMaxWidth(Double.MAX_VALUE);
            setMaxHeight(Double.MAX_VALUE);

            WebView view = new WebView();
            
                     
            
            view.setMinSize(500, 400);
            view.setPrefSize(500, 400);
            final WebEngine eng = view.getEngine();
            eng.load("https://semutsoft-trucking.id/user/?671925C438813F0366D8745D4D329EBB");
            final TextField locationField = new TextField("https://semutsoft-trucking.id/user/?671925C43881773F0366D8745D4D329EBB");
            locationField.setMaxHeight(Double.MAX_VALUE);
            locationField.setVisible(false);
            Button goButton = new Button("Go");
            goButton.setDefaultButton(true);
            goButton.setVisible(false);
            EventHandler<ActionEvent> goAction = new EventHandler<ActionEvent>() {
                @Override public void handle(ActionEvent event) {
                    eng.load(locationField.getText().startsWith("http://") ? locationField.getText() :
                            "https://" + locationField.getText());
                }
            };
            locationField.setVisible(false);
            goButton.setOnAction(goAction);
            locationField.setOnAction(goAction);
            eng.locationProperty().addListener(new ChangeListener<String>() {
                @Override public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                    locationField.setText(newValue);
                }
            });
            GridPane grid = new GridPane();
            grid.setVgap(5);
            grid.setHgap(5);
            //GridPane.setConstraints( 0, 0, 1, 1, HPos.CENTER, VPos.CENTER, Priority.ALWAYS, Priority.SOMETIMES);
            //GridPane.setConstraints(1,0);
           //GridPane.setConstraints(locationField, 0, 0, 1, 1, HPos.CENTER, VPos.CENTER, Priority.ALWAYS, Priority.SOMETIMES);
           // GridPane.setConstraints(goButton,1,0);
            GridPane.setConstraints(view, 0, 1, 2, 1, HPos.CENTER, VPos.CENTER, Priority.ALWAYS, Priority.ALWAYS);
            grid.getColumnConstraints().addAll(
                    new ColumnConstraints(100, 100, Double.MAX_VALUE, Priority.ALWAYS, HPos.CENTER, true),
                    new ColumnConstraints(40, 40, 40, Priority.NEVER, HPos.CENTER, true)
            );
            //grid.getChildren().addAll(locationField, goButton, view);
            grid.getChildren().addAll(view);
            getChildren().add(grid);
        }

        @Override protected void layoutChildren() {
            List<Node> managed = getManagedChildren();
            double width = getWidth();
            double height = getHeight();
            double top = getInsets().getTop();
            double right = getInsets().getRight();
            double left = getInsets().getLeft();
            double bottom = getInsets().getBottom();
            for (int i = 0; i < managed.size(); i++) {
                Node child = managed.get(i);
                layoutInArea(child, left, top,
                               width - left - right, height - top - bottom,
                               0, Insets.EMPTY, true, true, HPos.CENTER, VPos.CENTER);
            }
        }
        
        //tes function
        
       
    }
}


 

 

