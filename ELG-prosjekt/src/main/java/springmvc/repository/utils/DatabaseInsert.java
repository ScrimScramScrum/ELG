/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package springmvc.repository.utils;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 *
 * @author eiriksandberg
 */
public class DatabaseInsert {
      private String filnavn = "/home/hoxmark/scriptet.txt";
      private ArrayList<String> sql = new ArrayList<>();
       
       
        public ArrayList<String> lesInn() throws IOException{
            FileInputStream fstream = new FileInputStream(filnavn);
            BufferedReader br = new BufferedReader(new InputStreamReader(fstream));
             String setning = "f";
             while (setning != null){
                 setning = br.readLine();
                 if(setning == null){
                     break;
                 }
                 sql.add(setning);
             }
             return sql;
         }
}
