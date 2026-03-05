package com.back;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Question {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String subject;
    private String content;

    /*
    CascadeType.PERSIST를 활용하여 답글 저장하기 - t6()
    @OneToMany를 활용해 Question을 저장할 경우 Answer도 함께 저장되도록 설정
     */

    @OneToMany(mappedBy = "question", cascade = CascadeType.PERSIST)
    private List<Answer> answerList = new ArrayList<>();

    public void addAnswer(Answer answer){
        answer.setQuestion(this);
        answerList.add(answer);
    }
}
