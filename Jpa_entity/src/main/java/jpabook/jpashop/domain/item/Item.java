package jpabook.jpashop.domain.item;

import exception.NotenoughStockException;
import jpabook.jpashop.Category;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "dtype")
@Getter @Setter
public abstract class Item {
    @Id @GeneratedValue
    @Column(name = "item_id")
    private Long id;

    private String name;
    private int price;
    private int stockQuantity;
    @ManyToMany(mappedBy = "items")
    private List<Category> categories = new ArrayList<>();
    //==비지니스로직==//
    //stock 증가
    public void addStock(int quantity){
        this.stockQuantity += quantity;
    }
    //stock 감소
    public void removeStock(int quantity){
        int restStock= this.stockQuantity - quantity;
        if (restStock < 0 ){
            throw new NotenoughStockException("need more stock");
        }
        this.stockQuantity=restStock;
    }
    //data 를 가지고 있는 쪽에 비지니스 로직이 있는것이 좋다
}
