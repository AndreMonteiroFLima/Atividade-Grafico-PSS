package com.mycompany.exercicioavaliativografico.service;

import com.mycompany.exercicioavaliativografico.model.SubjectModel;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class DataProcessingService {

    private String SOLTEIRO = "Solteiro(a)";
    private String CASADO = "Casado(a)";
    private String MASCULINO = "Masculino";
    private String FEMININO = "Feminino";


    public Map<String,Double> processData(Collection<SubjectModel> subjectCollection){
        Map<String,Double> data = new HashMap<>();
        double qtd_MS = 0;
        double qtd_FS = 0;
        double qtd_MC = 0;
        double qtd_FC = 0;

        for (SubjectModel subjectModel: subjectCollection) {
            if(subjectModel.getCivilState().equals(SOLTEIRO)){
                if(subjectModel.getGender().equals(MASCULINO)){
                    qtd_MS++;
                }else {
                    qtd_FS++;
                }
            }else{
                if(subjectModel.getGender().equals(MASCULINO)){
                    qtd_MC++;
                }else {
                    qtd_FC++;
                }
            }
        }

        data.put(MASCULINO+SOLTEIRO,qtd_MS);
        data.put(FEMININO+SOLTEIRO,qtd_FS);
        data.put(MASCULINO+CASADO,qtd_MC);
        data.put(FEMININO+CASADO,qtd_FC);

        return data;
    }

    public String getCASADO() {
        return CASADO;
    }

    public String getFEMININO() {
        return FEMININO;
    }

    public String getMASCULINO() {
        return MASCULINO;
    }

    public String getSOLTEIRO() {
        return SOLTEIRO;
    }
}

