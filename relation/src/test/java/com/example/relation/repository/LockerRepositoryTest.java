package com.example.relation.repository;

import static org.mockito.Mockito.validateMockitoUsage;

import java.util.concurrent.locks.Lock;
import java.util.stream.IntStream;
import java.util.stream.LongStream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.relation.entity.sports.Locker;
import com.example.relation.entity.sports.SportsMember;
import com.example.relation.repository.sports.LockerRepository;
import com.example.relation.repository.sports.SportsMemberRepository;

@SpringBootTest
public class LockerRepositoryTest {

    @Autowired
    private LockerRepository lockerRepository;

    @Autowired
    private SportsMemberRepository sportsMemberRepository;

    // 단방향(SprtsMember => Locker)
    @Test
    public void testInsert() {
        IntStream.range(1, 6).forEach(i -> {
            Locker locker = Locker.builder().name("locker" + i).build();
            lockerRepository.save(locker);
        });

        LongStream.range(1, 6).forEach(i -> {
            SportsMember sportsMember = SportsMember.builder()
                    .locker(Locker.builder().id(i).build())
                    .name("member" + i)
                    .build();
            sportsMemberRepository.save(sportsMember);
        });
    }

    // 조회
    @Test
    public void testRead() {
        System.out.println(lockerRepository.findById(1L).get());
        System.out.println(sportsMemberRepository.findById(1L).get());
    }

    @Test
    public void testRead2() {
        SportsMember sportsMember = sportsMemberRepository.findById(2L).get();

        System.out.println(sportsMember);
        System.out.println(sportsMember.getLocker());
    }

    @Test
    public void testUpdate() {
        // 3번 회원 이름을 홍길동으로 변환
        SportsMember member = sportsMemberRepository.findById(3L).get();
        member.setName("홍길동");
        sportsMemberRepository.save(member);
    }

    @Test
    public void testDelele() {
        // 3번 회원 삭제
        sportsMemberRepository.deleteById(5L);
    }

    @Test
    public void testDelele2() {
        // 4번 locker 삭제(무결성제약조건 - 4번 locker 를 할당받은 member 존재)
        // sportsMemberRepository.deleteById(4L);

        // 4번 회원에서 5번 locker 할당
        SportsMember member = sportsMemberRepository.findById(4L).get();
        Locker locker = lockerRepository.findById(5L).get();

        member.setLocker(locker);
        sportsMemberRepository.save(member);

        // 4번 락커 제거
        lockerRepository.deleteById(4L);

    }

    // --------------------------
    // locker => sportsMemver 접근
    // --------------------------
    @Test
    public void testRead3() {
        Locker locker = lockerRepository.findById(1L).get();

        System.out.println(locker);
        System.out.println(locker.getSportsMember());
    }
}
