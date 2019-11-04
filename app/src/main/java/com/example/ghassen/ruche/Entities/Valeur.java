package com.example.ghassen.ruche.Entities;

public class Valeur {

    private Integer id;
    private String CodeCapteur;
    private String 	content;
    private String 	dateCreation;

    @Override
    public String toString() {
        return "" + content;
    }


    public String toString1() {
        return ""+ dateCreation;
    }

    public Valeur(Integer id, String codeCapteur, String content, String dateCreation) {
        this.id = id;
        CodeCapteur = codeCapteur;
        this.content = content;
        this.dateCreation = dateCreation;
    }


    public Valeur(Integer id, String dateCreation) {
        this.id = id;
        this.dateCreation = dateCreation;
    }

    public Valeur(String content) {
        this.content = content;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setCodeCapteur(String codeCapteur) {
        CodeCapteur = codeCapteur;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setDateCreation(String dateCreation) {
        this.dateCreation = dateCreation;
    }

    public Integer getId() {
        return id;
    }

    public String getCodeCapteur() {
        return CodeCapteur;
    }

    public String getContent() {
        return content;
    }

    public String getDateCreation() {
        return dateCreation;
    }
}
