package jpabook.jpashop.service;

import jpabook.jpashop.Delivery;
import jpabook.jpashop.Member;
import jpabook.jpashop.Order;
import jpabook.jpashop.OrderItem;
import jpabook.jpashop.domain.item.Item;
import jpabook.jpashop.repository.ItemRepository;
import jpabook.jpashop.repository.MemberRepository;
import jpabook.jpashop.repository.OrderRepository;
import jpabook.jpashop.repository.OrderSearch;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final MemberRepository memberRepository;
    private final ItemRepository itemRepository;

    //주문
    @Transactional
    public Long order(Long memberId, Long itemId, int count){
        //엔티티 조회
        Member member = memberRepository.findOne(memberId);
        Item item = itemRepository.findOne(itemId);

        //배송정보 생성
        Delivery delivery = new Delivery();
        delivery.setAddress(member.getAddress());

        //주문상품 생성
        OrderItem orderItem = OrderItem.createOrderItem(item,item.getPrice(),count);

        //주문 생성
        Order order = Order.createOrder(member, delivery, orderItem);
        //컨트롤 스페이스하면 자동으로 인자 채워줌

        //주문 저장
        orderRepository.save(order);

        //원래라면 delivery save하고 다 save해야하는데 order안에 이런것들
        //옵션이 cascade로 들어가서 다 일일이 저장 안해도 된다.
        //만약 딜리버리가 다른데서 막쓰고 중요한거라면 cascade하면안된다
        // private같이 그 안에서만쓰이는거는 ㄱㅊ

        return order.getId();
    }


    //주문 취소
    @Transactional
    public void cancelOrder(Long orderId){
        //주문 엔티티 조회
        Order order = orderRepository.findOne(orderId);
        //주문취소
        order.cancel();
    }
    //검색
    public List<Order> findOrders(OrderSearch orderSearch){
        return orderRepository.findAllByCriteria(orderSearch);
    }

}
//도메인모델패턴