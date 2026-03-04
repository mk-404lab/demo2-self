package com.back;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Demo2SelfApplication {

    public static void main(String[] args) {
        SpringApplication.run(Demo2SelfApplication.class, args);

        // 답글로부터 질문을 찾아가는 방법
//        Answer a = new Answer();
//
//        Question question = a.getQuestion();
//        question.getContent();

        // 특정 질문에 달린 모든 답글
//        Question q1 = questionRepository.findById(1);
//        q1.getAnswerList();
    }

}
