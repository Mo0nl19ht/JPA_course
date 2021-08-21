package jpabook.jpashop.controller;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
public class MemberForm {
    @NotEmpty(message = "회원 이름은 필수 입니다")
    private String name;

    private String city;
    private String street;
    private String zipcode;
}
//데이터를 그냥 바로 member에 넣지 말고 화면에서 주는 것만 해당된걸 따로만들어서 하는게 좋다
//컨트롤러 같은데서 정제 or validattion
//엔티티에는 화면을 위한 로직은 없어야한다
//폼 객체나 dto(데이터 전송 객체) 여기서는 멤버 폼??를 사용한다

//api를만들떄는 엔티티를 외부로 반환하면 안된다.