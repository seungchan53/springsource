package com.example.jpa.repository;

import java.util.stream.LongStream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.jpa.entity.Student;
import com.example.jpa.entity.Student.Grade;

@SpringBootTest // test 용 클래스임
public class StudentRepositoryTest {

    @Autowired // = new StudentRepository() //
    private StudentRepository studentRepository;

    // CRUD test
    // Rpository, Entity 확인
    // C(insert) : save(Entity) : insert
    // U(update) : save(Entity) : update
    // 구별은 어떻개 하는가? 둘다 동일한 save() 호출
    // 원본과 변경된 부분이 있다면 update 로 실행해줌

    @Test // 테스트 메소드임 (테스트 메소드는 리턴 타입이 void 여야 함)
    public void insertTest() {
        // Entity 생성

        LongStream.range(1, 11).forEach(i -> {
            Student student = Student.builder()
                    .name("홍길동")
                    .grade(Grade.JUNIOR)
                    .gender("M")
                    .build();

            // insert
            studentRepository.save(student);
        });

    }

    @Test
    public void updateTest() {

        // findbyId(1L) : select * from 테이블명 where id = 1

        Student student = studentRepository.findById(1L).get();

        // update
        // studenttbl
        // set
        // c_date_time=?,
        // c_date_time2=?,
        // gender=?,
        // grade=?,
        // name=?,
        // u_date_time=?,
        // u_date_time2=?
        // where
        // id=?
        student.setGrade(Grade.SENIOR);
        // update
        studentRepository.save(student);
    }

    // @Test
    // public void selectOneTest() {

    // // Optional<Student> student = studentRepository.findById(1L);

    // // if (student.isPresent()) {
    // // System.out.println(student.get());
    // // }

    // Student student =
    // studentRepository.findById(3L).orElseThrow(EntityNotFoundException::new);
    // System.out.println(student);
    // }

    @Test
    public void selectTest() {
        // List<Student> list = studentRepository.findAll();

        // for (Student student : list) {
        // System.out.println(student);
        // }

        studentRepository.findAll().forEach(student -> System.out.println(student));
    }

    @Test
    public void deleteTest() {

        // Student student = studentRepository.findById(11L).get();

        // studentRepository.delete(student);

        studentRepository.deleteById(12L);
    }
}
