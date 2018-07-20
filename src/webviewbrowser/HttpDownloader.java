/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package webviewbrowser;
import java.io.IOException;
 
public class HttpDownloader {
 
    public static void main(String[] args) {
        String fileURL = "http://jdbc.postgresql.org/download/postgresql-9.2-1002.jdbc4.jar";
        String saveDir = "E:/Download";
        try {
            HttpDownloadUtility.downloadFile(fileURL, saveDir);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}