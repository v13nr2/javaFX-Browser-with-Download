/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package webviewbrowser;

import java.awt.Toolkit;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.io.BufferedReader;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.BorderPane;
import javafx.scene.web.WebView;
import javafx.stage.Stage;


import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.CookieManager;
import java.net.HttpCookie;
import java.net.URI;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.TextInputDialog;
import javafx.scene.input.Clipboard;
import javafx.scene.web.WebHistory;
import javax.swing.JOptionPane;
import static webviewbrowser.WebViewBrowser.pesan;
import javafx.scene.image.Image;

/**
 *
 * @author SeMUTSoft
 */
public class TST  extends Application {

    private final String START_URL = 
            "https://semutsoft-trucking.id/user";

    
   TextField locationField = new TextField(START_URL);
    
    WebView webView = new WebView();
        
    @Override
    public void start(Stage primaryStage) {
        webView.getEngine().load(locationField.getText().toString());
        locationField.setEditable(false);
        webView.setContextMenuEnabled(false);
        createContextMenu(webView);
        
        primaryStage.getIcons().add(new Image("https://semutsoft-trucking.id/java.jpg"));
        primaryStage.setTitle("Aplikasi SeMUTSoft Trucking");

        locationField.setOnAction(e -> {
            webView.getEngine().load(getUrl(locationField.getText()));
        });
        
        
        
        webView.getEngine().locationProperty().addListener(new ChangeListener<String>() {
                @Override public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                    locationField.setText(newValue);
                }
            });
        
        BorderPane root = new BorderPane(webView, locationField, null, null, null);
        primaryStage.setScene(new Scene(root, 800, 600));
        primaryStage.show();

    }

    private void createContextMenu(WebView webView) {
        ContextMenu contextMenu = new ContextMenu();
        MenuItem reload = new MenuItem("Reload");
        reload.setOnAction(e -> webView.getEngine().reload());
        MenuItem kembali = new MenuItem("Kembali");
        kembali.setOnAction(e -> goBack());
        MenuItem savePage = new MenuItem("Download");
        savePage.setOnAction(e -> donlot(locationField.getText()));
        MenuItem tempel = new MenuItem("Paste");
        tempel.setOnAction(e -> onPaste());
        MenuItem register = new MenuItem("Register");
        register.setOnAction(e -> register_key());
        contextMenu.getItems().addAll(reload, kembali, savePage, register);

        webView.setOnMousePressed(e -> {
            if (e.getButton() == MouseButton.SECONDARY) {
                contextMenu.show(webView, e.getScreenX(), e.getScreenY());
            } else {
                contextMenu.hide();
            }
        });
    }
    public void goBack(){
        WebHistory history = webView.getEngine().getHistory();
        history.go(-1);
         
      
    }
    
    public void register_key(){
        String url = "https://semutsoft-trucking.id/user";
        //String  postData = URLEncoder.encode("key1", "UTF-8") + "=" + URLEncoder.encode("value1", "UTF-8"); postData += "&" + URLEncoder.encode("key2", "UTF-8") + "=" + URLEncoder.encode("value2", "UTF-8"); 
        String  postData; 
        postData = URLEncoder.encode("nama key") + "=" + URLEncoder.encode("masukkan key anda#");
            System.out.println("Status: " + doPost(url, postData));
            
            //if(doPost(url, postData)== "SUKSES"){
               // webView.getEngine().load(getUrl("https://semutsoft-trucking.id/user/load_login_page"));
            
                //webView.getEngine().locationProperty().addListener((obs, oldLocation, newLocation) -> {
                    //if (newLocation != null) {
                        webView.getEngine().load("https://semutsoft-trucking.id/user/load_login_page");
                    //}
                //});
                
                
           // }
            
            
    }
    private void onPaste(){
        java.awt.datatransfer.Clipboard c = Toolkit.getDefaultToolkit().getSystemClipboard();
        Transferable t = c.getContents(this);
        if (t == null)
            return;
        try {
            locationField.setText((String) t.getTransferData(DataFlavor.stringFlavor));
        } catch (Exception e){
            e.printStackTrace();
        }//try
    }//onPaste
    
    
    private String getUrl(String text) {
        if (text.indexOf("://")==-1) {
            return "http://" + text ;
        } else {
            return text ;
        }
    }

    public static void main(String[] args) {
        
        
        pesan();
        launch(args);
    }
    
     private void donlot(String lokasi){
         
         
         TextInputDialog dialog = new TextInputDialog("");
 
         String saveDir = "C:/Laporan";
        try {
            
            dialog.setTitle("SeMUTSoft Download");
            dialog.setHeaderText("Look, a download");
            dialog.setContentText("Silakahkan masukkan nama file :");

         
            // Traditional way to get the response value.
            Optional<String> result = dialog.showAndWait();
            String fileURL = "https://semutsoft-trucking.id/dev/laporan_trucking/jurnal/"+result.get();
            System.out.println("Nama File: " + result.get());
         
            HttpDownloadUtility.downloadFile(fileURL, saveDir);
            //JOptionPane.showMessageDialog(null, "Laporan Berhasil di Download SeMUTSoft.");
        } catch (IOException ex) {
            //JOptionPane.showMessageDialog(null, "Laporan Gagal di Download SeMUTSoft.");
            ex.printStackTrace();
        }
    }
     
     
         public static void pesan(){
             JOptionPane.showMessageDialog(null, "Selamat Datang Di Aplikasi SeMUTSoft.");
         }
         
         
         public static String doPost(String url, String postData) {
            PrintWriter out = null;
            BufferedReader in = null;
            String result = "";
            try {
                URL realUrl = new URL(url);
                // build connection
                URLConnection conn = realUrl.openConnection();
                // set request properties
                conn.setRequestProperty("accept", "*/*");
                conn.setRequestProperty("connection", "Keep-Alive");
                conn.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1; SV1)");
                // enable output and input
                conn.setDoOutput(true);
                conn.setDoInput(true);
                out = new PrintWriter(conn.getOutputStream());
                // send POST DATA
                out.print(postData);
                out.flush();
                in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                String line;
                while ((line = in.readLine()) != null) {
                    //result += "/n" + line;
                    result = line;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            finally {
                try {
                    if (out != null) {
                        out.close();
                    }
                    if (in != null) {
                        in.close();
                    }
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
            return result;
        } 
        
}