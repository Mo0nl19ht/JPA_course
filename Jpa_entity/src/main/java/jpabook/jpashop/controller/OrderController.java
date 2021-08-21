package jpabook.jpashop.controller;

import jpabook.jpashop.Member;
import jpabook.jpashop.Order;
import jpabook.jpashop.domain.item.Item;
import jpabook.jpashop.domain.OrderSearch;
import jpabook.jpashop.service.ItemService;
import jpabook.jpashop.service.MemberService;
import jpabook.jpashop.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;
    private final MemberService memberService;
    private final ItemService itemService;

    @GetMapping("/order")
        public String createForm(Model model){
        List<Member> members = memberService.findMembers();
        List<Item> items = itemService.findItems();

        model.addAttribute("members",members);
        model.addAttribute("items",items);
        return "order/orderForm";
    }
    @PostMapping("/order")
    public String order(@RequestParam("memberId")Long memberId,
                        @RequestParam("itemId") Long itemId,
                        @RequestParam("count") int count){
        orderService.order(memberId, itemId, count);
        return "redirect:/orders";
    }

    @GetMapping(value = "/orders")
    public String orderList(@ModelAttribute("orderSearch") OrderSearch
                                    orderSearch, Model model) {
        List<Order> orders = orderService.findOrders(orderSearch);
        //단순하게 조회만 하는 기능이면 직접 호출해도 ㄱㅊ음
        //서비스에 위임하지 않고
        model.addAttribute("orders", orders);
        return "order/orderList";
    }
    @PostMapping("/orders/{orderId}/cancel")
    public String cancelOrder(@PathVariable("orderId") Long orderId){
        orderService.cancelOrder(orderId);
        return "redirect:/orders";
    }
}
//컨트롤러에서는 엔티티 식별자만 넘기고
// 서비스 계층엣 ㅓ조회하고 사용한다
//트랜잭션 안에서 진행해야 영속성 상태로 진행되기 때문
// 찾고나서 넘겨주면 영속성이랑 관계없어서 더티체킹 같은거 못함