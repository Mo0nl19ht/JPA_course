package jpabook.jpashop.repository;


import jpabook.jpashop.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class MemberRepository {

    private final EntityManager em;
    // 테스트를 위해 자동 생성자 만듬
    public void save(Member member){
        em.persist(member);
    }
    public Member findOne(Long id){
        return em.find(Member.class,id);
    }
    public List<Member> findAll(){
        return em.createQuery("select m from Member m ", Member.class).getResultList();
    }
    public List<Member> findByName(String name){
        return em.createQuery("select m from Member m where m.name = :name", Member.class)
                .setParameter("name",name)
                .getResultList();
    }
    //sql 테이블을 대상으로 쿼리
    //jpql 이거는 entitiy를 대상으로 쿼리
}
