package jpabook.jpashop.domain;

import lombok.Getter;
import lombok.Setter;



import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
public class Member {
    @Id @GeneratedValue
    @Column(name = "member_id")
    private Long id;
    @Column
    private String name;

    @Embedded
    private Address address;


    @OneToMany(mappedBy = "member") // order필드의 member에 의해 매핑됨
    private List<Order> orders = new ArrayList<>();
    // 여기에 값 넣는다고 외래키 값이 변경되지않는다?


}
