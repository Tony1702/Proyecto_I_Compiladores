/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Triangle.SyntacticAnalyzer;

import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Fabian
 */
public class HtmlWriter {
    private String fileName;
    public HtmlWriter(String fileName){
        this.fileName = fileName.replace(".tri", ".html");
    }
    public void write(String data){
        try {
            FileWriter fileWriter =  new FileWriter(fileName);
            fileWriter.write("<!DOCTYPE html><html><head><style>p{font-family: 'DejaVu Sans', monospace; \n display:inline;}</style></head><body> <p >" + data + "</p></body></html>");
            fileWriter.close();
        } catch (IOException ex) {
            Logger.getLogger(HtmlWriter.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
}
