/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.exercicioavaliativografico.model;

/**
 *
 * @author andre.lima
 */
public class SubjectModel {
    
    private String gender;
    private String name;
    private String civilState;


    public SubjectModel() {
    }

    public SubjectModel(String gender, String name, String civilState) {
        this.gender = gender;
        this.name = name;
        this.civilState = civilState;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCivilState() {
        return civilState;
    }

    public void setCivilState(String civilState) {
        this.civilState = civilState;
    }

    @Override
    public String toString() {
        return "Subjetc{" +
                "gender='" + gender + '\'' +
                ", name='" + name + '\'' +
                ", civilState='" + civilState + '\'' +
                '}';
    }
}
