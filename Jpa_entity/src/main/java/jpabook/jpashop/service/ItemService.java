package jpabook.jpashop.service;

import jpabook.jpashop.domain.item.Item;
import jpabook.jpashop.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;

    @Transactional
    public void saveItem(Item item){
        itemRepository.save(item);
    }

    @Transactional
    public void updateItem(Long itemid,String name,int price,int stockQuantity){
        //파라미터 많아지면 dto주입해서 새로 클래스 만든다.
        // 그래서 dto.name이렇게

        Item findItem = itemRepository.findOne(itemid);
        findItem.setPrice(price);
        findItem.setName(name);
        findItem.setStockQuantity(stockQuantity);

        //변경시에도 findItem.change(price,name,stockQuantity);
        //이렇게 엔티티에서 추적가능하게 하는것이 좋음

    }
    //실제로는  merge쓰면안된다
    // merge쓰면 입력되지 않은 속성은 NULL처리한다
    //완전히


    public List<Item> findItems(){
        return itemRepository.findAll();
    }
    public Item findOne(Long itemId){
        return itemRepository.findOne(itemId);
    }

}
// 단순히 위임만 하는 서비스이다
// 그래서 그냥 바로 컨트롤러에서 repo에 바로 접근해서 해도 된다.