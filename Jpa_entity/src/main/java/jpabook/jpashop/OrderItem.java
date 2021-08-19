package jpabook.jpashop;


import jpabook.jpashop.domain.item.Item;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
//밖에서 생성자 못쓰게 하는거 createOrderitem만 쓰도록하는거 롬복
// 댜시 잘 찾아보기
public class OrderItem {
    @Id @GeneratedValue
    @Column(name ="order_item_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="item_id")
    private Item item;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="order_id")
    // 외래키 개념인거같음
    private Order order;

    private int orderPrice; //주문
    private int count;

    //==생성 메서드 ==//\
    public static OrderItem createOrderItem(Item item, int orderPrice, int count){
        // item엔티티의 가격을 가져와서 orderPrice에 넣을 수 있지만
        // 세일가격 같은거 일수도 있어서 따로 넣는ㄱ ㅔ맞다
        OrderItem orderItem = new OrderItem();
        orderItem.setItem(item);
        orderItem.setOrderPrice(orderPrice);
        orderItem.setCount(count);

        item.removeStock(count);
        return orderItem;
    }



    //==비지니스 로직==//
    public void cancel() {
        getItem().addStock(count);
        //주문한 아이템의 재고수량을 지금 산 만큼 +해준다 (원복해준다)
    }
    //==조회 로직==//
    public int getTotalPrice() {
        return getOrderPrice() * getCount();
    }
}
