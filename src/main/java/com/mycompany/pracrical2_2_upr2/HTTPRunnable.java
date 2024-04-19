/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.pracrical2_2_upr2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.HashMap;
import javax.net.ssl.HttpsURLConnection;

/**
 *
 * @author KonGo
 */
public class HTTPRunnable implements Runnable {
    
    private String address;
    private HashMap<String,String> requestBody;
    private String responseBody;

    public HTTPRunnable(String address, HashMap<String, String> requestBody) {
        this.address = address;
        this.requestBody = requestBody;
    }

    public String getResponseBody() {
        return responseBody;
    }
    //это к первому упражнению
//    @Override 
//    public void run(){
//        if(this.address != null && !this.address.isEmpty()){
//            try{
//               URL url = new URL(this.address);
//               URLConnection connection = url.openConnection();
//               HttpURLConnection httpConnection = (HttpsURLConnection)connection;
//               httpConnection.setRequestMethod("GET");
//               InputStreamReader isr = new InputStreamReader(httpConnection.getInputStream());
//               BufferedReader br = new BufferedReader(isr);
//               String currentLine;
//               StringBuilder sbResponse = new StringBuilder();
//               while((currentLine = br.readLine()) != null){
//                   sbResponse.append(currentLine);
//               }
//               responseBody = sbResponse.toString();
//               
//            }catch(IOException ex){
//                System.out.println("Error: " + ex.getMessage());
//            }
//        }
//    }
    
    // это второе упражнение
    @Override 
    public void run(){
        if(this.address != null && !this.address.isEmpty()){
            try{
               URL url = new URL(this.address);
               URLConnection connection = url.openConnection();
               HttpURLConnection httpConnection = (HttpsURLConnection)connection;
               httpConnection.setRequestMethod("POST");
               httpConnection.setDoOutput(true);
                OutputStreamWriter osw = new OutputStreamWriter(httpConnection.getOutputStream());
                osw.write(generateStringBody());
                osw.flush();
                int responseCode = httpConnection.getResponseCode();
                System.out.println("Response Code: " + responseCode);
                if(responseCode == 200){
                    InputStreamReader isr = new InputStreamReader(httpConnection.getInputStream());
                    BufferedReader br = new BufferedReader(isr);
                    String currentLine;
                    StringBuilder sbResponse = new StringBuilder();
                    while((currentLine = br.readLine()) != null){
                        sbResponse.append(currentLine);
               }
               responseBody = sbResponse.toString();
               
            }else{
                    System.out.println("Error! Bad response code!");
                }   
            }catch(IOException ex){
                System.out.println("Error: " + ex.getMessage());
                }
               
        }
}
    private String generateStringBody(){
    StringBuilder sbParams = new StringBuilder();
    if(this.requestBody != null && !requestBody.isEmpty()){
        int i = 0;
        for (String key : this.requestBody.keySet()){
            try { 
                if (i!=0){
                    sbParams.append("&");
                }
                sbParams.append(key).append("=")
                        .append(URLEncoder.encode(this.requestBody.get(key),"UTF-8"));
            }catch (UnsupportedEncodingException e){
                e.printStackTrace();
            }
            i++;
        }
    }
    return sbParams.toString();
}

}
