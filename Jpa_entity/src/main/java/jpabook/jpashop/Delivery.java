package jpabook.jpashop;

import lombok.Getter;
import lombok.Setter;


import javax.persistence.*;

import static javax.persistence.FetchType.LAZY;

@Entity
@Getter
@Setter
public class Delivery {
    @Id
    @GeneratedValue
    @Column(name ="delivery_id")
    private Long id;

    @OneToOne(mappedBy = "delivery",fetch = LAZY)
    private Order order;

    @Embedded // 이거 뭔지 정확히 알고가기
    private Address address;

    @Enumerated(EnumType.STRING) // ORDINAL 쓰면 숫자로 ready 1 comp2 이렇게 매핑되는데
                                // 중간에 xxx라는게 들어오면 comp가 3으로 밀리면서 나중에 comp 쓰려했는데 xxx가 써진다
    private DeliveryStatus status; //READY COMP
}
