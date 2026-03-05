package com.back;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@ActiveProfiles("test")
public class QuestionRepositoryTest {

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private AnswerRepository answerRepository;

//    @Test
//    void t1() {
//        Question q1 = new Question();
//        q1.setSubject("sbb가 무엇인가요?");
//        q1.setContent("sbb에 대해서 알고 싶습니다.");
//        questionRepository.save(q1);
//
//        Answer a1 = new Answer();
//        a1.setContent("sbb는 스프링부트 게시판입니다.1");
//        a1.setQuestion(q1);
//        answerRepository.save(a1);
//
//        Answer a2 = new Answer();
//        a2.setContent("sbb는 스프링부트 게시판입니다.2");
//        a2.setQuestion(q1);
//        answerRepository.save(a2);
//
//    }

    @Test
    @Transactional
    void t2() {
        Question q1 = questionRepository.findById(1).get();

        assertThat(q1.getSubject()).isEqualTo("sbb가 무엇인가요?");

    }

    @Test
    void t1(){
        List<Question> all = this.questionRepository.findAll();
        assertEquals(2, all.size());

        System.out.println(all.size());

        Question q = all.get(0);
        assertEquals("sbb가 무엇인가요?", q.getSubject());

        System.out.println(q.getSubject());
    }

    @Test
    void t3(){
        Question q1 = questionRepository.findBySubject("sbb가 무엇인가요?").get();

        assertThat(q1.getId()).isEqualTo(1);
        assertThat(q1.getContent()).isEqualTo("sbb에 대해서 알고 싶습니다.");

    }

    @Test
    @DisplayName("질문 수정")
    void t4(){
        Question q1 = questionRepository.findById(1).get();

        q1.setSubject("sbb가 무엇인가요? - 수정");
        questionRepository.save(q1);
        questionRepository.flush();

        Question q1_2 = questionRepository.findById(1).get();
        assertThat(q1_2.getSubject()).isEqualTo("sbb가 무엇인가요? - 수정");
    }

    @Test
    @DisplayName("질문 삭제")
    void t5(){
        Question q1 = questionRepository.findById(1).get();
        questionRepository.delete(q1);

        assertThat(questionRepository.count()).isEqualTo(1);
    }

    @Test
    @DisplayName("답글 저장-OneToMany 방식")
    void t6() {
        Question q1 = new Question();
        q1.setSubject("새 질문");

        Answer a1 = new Answer();
        a1.setContent("답글 1");

        /*
        새로운 Question, Answer를 각각 만들었고, Question이 가지고 있는 필드 answer에 a1 저장
        => 질문과 답변이 한 세트로 q1 객체에 저장됨
        q1.저장 -> flush(): DB에 즉시 반영되도록 강제로 밀어넣는 것
        => cascade가 persist임에 따라 연결된 a1도 함께 저장됨
         */

        q1.addAnswer(a1);
        questionRepository.save(q1);
        questionRepository.flush();

        Answer foundedAnswer = answerRepository.findById(1).get();

        assertThat(foundedAnswer.getId()).isEqualTo(1);
        assertThat(foundedAnswer.getContent()).isEqualTo("답글 1");

    }
}
