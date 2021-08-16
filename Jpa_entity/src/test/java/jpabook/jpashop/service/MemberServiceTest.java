package jpabook.jpashop.service;

import jpabook.jpashop.Member;
import jpabook.jpashop.repository.MemberRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional // 데이터 변경해야하기 때문 , 이게 있어야 롤백이 된다
public class MemberServiceTest {
    @Autowired
    MemberService memberService;
    @Autowired
    MemberRepository memberRepository;
    @Autowired
    EntityManager em;
    @Test
    public void 회원가입 () throws Exception{
        Member member = new Member();
        member.setName("kim");
        //given

        Long saveId = memberService.join(member);
        // when

        em.flush();//영속성컨텍스트에 있는 변경사항을 db에 보낸다.
        assertEquals(member,memberRepository.findOne(saveId));
        //then

    }
    @Test(expected = IllegalStateException.class)
    public void 중복_회원_예외() throws Exception{
        //given
        Member member1 = new Member();
        member1.setName("kim");

        Member member2 = new Member();
        member2.setName("kim");

        //when
        memberService.join(member1);
        memberService.join(member2);

        //then
        Assert.fail("예외가 발생해야한다, 근데 발생안함");
        //예외가 발생해야 테스트되는건데 그냥 예외가 발생안하고 여기까지 와버린거기 떄문에 테스트 실패임을 알려줌


    }

}