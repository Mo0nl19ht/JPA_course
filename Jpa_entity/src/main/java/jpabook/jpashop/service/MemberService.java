package jpabook.jpashop.service;

import jpabook.jpashop.Member;
import jpabook.jpashop.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
// 기본적으로 false 즉 쓰기 옵션이나 이 서비스에서는 주로 조회가 많기 때문에 기본으로 true를 주고
// 쓰기에만 따로 다시 @Transactional을 준다
public class MemberService {

    private final MemberRepository memberRepository;

//    public MemberService(MemberRepository memberRepository){
//        this.memberRepository = memberRepository;
//    }
    // 장점
    //final 사용가능 ->
    @Transactional
    public Long join(Member member){
        validateDuplicateMember(member); // 중복회원 검증
        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        List<Member> findMembers = memberRepository.findByName(member.getName());
        if(!findMembers.isEmpty()){
            throw new IllegalStateException("이미 존재하는 회원 입니다");
        }

    }
    public List<Member> findMembers(){
        return memberRepository.findAll();
    }
    public Member findOne(Long memberId){
        return memberRepository.findOne(memberId);
    }

}
