package jpabook.jpashop;



import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;


@Entity
@Data

public class Member {
    @Id @GeneratedValue
    private Long id;
    private String username;
}
