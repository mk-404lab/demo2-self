package com.back;

import jakarta.persistence.*;

@Entity
public class Answer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String content;

    /*
    DB적 사고가 아닌 자바적 사고하기
    => qustionId를 가져오는 게 아닌, Question 객체 자체를 가져와서 연결해주기
    => @ManyToOne 붙여주기 : 질문 한 개에 여러 개의 답이 달릴 수 있음
     */

    @ManyToOne
    private Question question;
}
