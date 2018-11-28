package com.example.mobile.domain;

import javax.persistence.*;
<<<<<<< HEAD
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
=======
>>>>>>> parent of 97f15ce... сервис


@Entity
@Table(name="answer")
public class Answer {

@Id
@GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id_answer;

    @JoinColumn(name = "id_question", referencedColumnName = "idQuestion")
    @ManyToOne(optional = false)
    private  Question id_question;
<<<<<<< HEAD

    @Size(max = 255)
    @NotNull
=======
>>>>>>> parent of 97f15ce... сервис
    private  String description;
    @NotNull
    private  Boolean result;

   
    public Answer() {
    }

    public Answer(Integer id_answer, Question id_question, String description, Boolean result) {
        this.id_answer = id_answer;
        this.id_question = id_question;
        this.description = description;
        this.result = result;
    }



    public Integer getId_answer() {
        return id_answer;
    }

    public void setId_answer(Integer id_answer) {
        this.id_answer = id_answer;
    }

    public Question getId_question() {
        return id_question;
    }

    public void setId_question(Question id_question) {
        this.id_question = id_question;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getResult() {
        return result;
    }

    public void setResult(Boolean result) {
        this.result = result;
    }
}
