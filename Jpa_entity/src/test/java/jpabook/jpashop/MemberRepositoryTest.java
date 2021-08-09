package jpabook.jpashop;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MemberRepositoryTest {
    @Autowired
     MemberRepository memberRepository;
    @Test
    @Transactional
    @Rollback(false)
    public void testMember() throws Exception{
        //given
        Member member = new Member();
        member.setUsername("memberA");

        //whene
        Long saveId = memberRepository.save(member);
        Member findMember = memberRepository.find(saveId);

        //then
        Assertions.assertThat(findMember.getId()).isEqualTo(member.getId());
        Assertions.assertThat(findMember.getUsername()).isEqualTo((member.getUsername()));

    }
}

// 엔티티 매니저를 통한 모든 데이터 변경은 트랜잭션안에서 이뤄져야함
// 트랜잭션??
//같은 트랜잭션안에서 저장하고 조회하면 영속성 컨텍스트 똑가타
//같은 영속성 컨텐스트안에서 식별자가 같으면 같은 엔티티로 인식한다
