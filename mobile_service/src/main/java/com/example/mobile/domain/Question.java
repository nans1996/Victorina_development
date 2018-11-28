package com.example.mobile.domain;

import javax.persistence.*;
<<<<<<< HEAD
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
=======
>>>>>>> parent of 97f15ce... сервис
import java.util.Collection;

@Entity
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
<<<<<<< HEAD
    private Integer idQuestion;
    @Size(max = 65535)
    @NotNull
=======
    private int idQuestion;
>>>>>>> parent of 97f15ce... сервис
    private String description;



    @OneToMany(cascade = CascadeType.ALL, mappedBy = "id_question")
    private Collection<Answer> answerCollection;

    @ManyToMany(mappedBy = "questionCollection")
    private  Collection<Users>  users;

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

    public Collection<Users> getUsers() {
        return users;
    }

    public void setUsers(Collection<Users> users) {
        this.users = users;
    }

    public int getIdQuestion() {
        return idQuestion;
    }

    public void setIdQuestion(int idQuestion) {
        this.idQuestion = idQuestion;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String descriptionQ) {
        this.description = descriptionQ;
    }



}
