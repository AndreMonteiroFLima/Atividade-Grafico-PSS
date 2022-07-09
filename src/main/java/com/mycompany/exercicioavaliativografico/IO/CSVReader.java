/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.exercicioavaliativografico.IO;

import com.mycompany.exercicioavaliativografico.model.SubjectModel;


import java.io.*;
import java.util.*;

/**
 *
 * @author andre.lima
 */
public class CSVReader {

    private static final String COMMA_DELIMITER = ",";

    static public Collection<SubjectModel> readCsv(){
        List<SubjectModel> subjectModels = new ArrayList<>();
        boolean firsRow = true;
        try (BufferedReader br = new BufferedReader(new FileReader("pessoas.csv"))) {
            String line;
            while ((line = br.readLine()) != null) {
                if(firsRow==false) {
                    String[] values = line.split(COMMA_DELIMITER);
                    subjectModels.add(new SubjectModel(values[0], values[1], values[2]));
                }
                firsRow=false;
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return subjectModels;
    }

}
