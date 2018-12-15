package com.example.mobile.domain;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Collection;


@Entity
@Table(name="answer")
public class Answer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id_answer;

    @JoinColumn(name = "id_question", referencedColumnName = "idQuestion")
    @ManyToOne(optional = false)
    private  Question id_question;

    @Size(max = 255)
    @NotNull
    private  String description;
    @NotNull
    private  Boolean result;



    @OneToMany(cascade = CascadeType.ALL, mappedBy = "id_answer")
    private Collection<UserAnswer> userAnswers;

    public Answer() {
    }

    public Answer( Question id_question, String description, Boolean result) {
        this.id_question = id_question;
        this.description = description;
        this.result = result;
    }
    public Collection<UserAnswer> getUserAnswers() {
        return userAnswers;
    }

    public void setUserAnswers(Collection<UserAnswer> userAnswers) {
        this.userAnswers = userAnswers;
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

    @Override
    public  String toString(){
        return  "{\"description\": \""+description+"\", \"result\": \""+result+"\"}";
    }
}
