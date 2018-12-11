package com.example.mobile.domain;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Collection;

@Entity
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer idQuestion;
    @Size(max = 65535)
    @NotNull
    private String description;



    @OneToMany(cascade = CascadeType.ALL, mappedBy = "id_question")
    private Collection<Answer> answerCollection;



    public Question() {
    }

    public Question(String descriptionQ) {
        this.description = descriptionQ;
    }

    public Collection<Answer> getAnswerCollection() {
        return answerCollection;
    }

    public void setAnswerCollection(Collection<Answer> answerCollection) {
        this.answerCollection = answerCollection;
    }


    public Integer getIdQuestion() {
        return idQuestion;
    }

    public void setIdQuestion(Integer idQuestion) {
        this.idQuestion = idQuestion;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String descriptionQ) {
        this.description = descriptionQ;
    }

    @Override
    public  String toString(){
        return  "{\"idQuestion\": \""+idQuestion+"\", \"description\": \""+description+"\"}";
    }

}
