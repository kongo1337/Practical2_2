/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */

package com.mycompany.pracrical2_2_upr2;

//import java.io.FileWriter;
//import java.io.IOException;
import java.util.HashMap;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author KonGo
 */
public class Pracrical2_2_upr2 {
    //это код из первого упражнения
    //    public static void main(String[] args) {
//        System.out.println("Start program!");
//        String server = "https://www.mirea.ru/";
//        HTTPRunnable httpRunnable = new HTTPRunnable(server, null);
//        Thread th = new Thread(httpRunnable);
//        th.start();
//        try{
//            th.join();
//        }catch(InterruptedException ex) {
//            
//        }finally{
//            try{
//                FileWriter fw = new FileWriter("C:\\resp.html");
//                fw.write(httpRunnable.getResponsBody());
//                fw.close();
//                System.out.println("Succes " + server);
//            } catch (IOException ex){
//                System.out.println("Error " + ex.getMessage());
//            }
//            
//            
//        }
//    }

    //это уже второе
    public static void main(String[] args) {
        System.out.println("Start program!");
        String server = "https://android-for-students.ru";
        String serverPath = "/materials/practical/hello.php";
        HashMap<String,String> map = new HashMap();
        map.put("name","Bystrov");
        map.put("group", "RIBO-03-22");
        HTTPRunnable httpRunnable = new HTTPRunnable(server + serverPath, map);
        Thread th = new Thread(httpRunnable);
        th.start();
        try{
            th.join();
        }catch(InterruptedException ex) {
            
        }finally{
            JSONObject jSONObject = new JSONObject(httpRunnable.getResponseBody());
            int result = jSONObject.getInt("result_code");
            System.out.println("Result: " + result);
            System.out.println("Type: " + jSONObject.getString("message_type"));
            System.out.println("Text: " + jSONObject.getString("message_text"));
            switch (result) { 
                case 1:
                    JSONArray jSONArray = jSONObject.getJSONArray("task_list");
                    System.out.println("Task list: ");
                    for(int i = 0; i < jSONArray.length();i++){
                        System.out.println((i + 1) + ") " + jSONArray.get(i));
                    }
                    break;
                case 0:
                    System.out.println("server returned an error");
                    break;
                default:
                    break;
            }
            
            
        }
    }
}
 