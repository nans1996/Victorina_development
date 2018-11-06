package com.example.mobile.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int idQuestion;
    private String description;

    public Question() {
    }

    public Question(String descriptionQ) {
        this.description = descriptionQ;
    }

    public int getIdQuestion() {
        return idQuestion;
    }

    public void setIdQuestion(int idQuestion) {
        this.idQuestion = idQuestion;
    }

    public String getDescriptionQ() {
        return description;
    }

    public void setDescriptionQ(String descriptionQ) {
        this.description = descriptionQ;
    }



}
