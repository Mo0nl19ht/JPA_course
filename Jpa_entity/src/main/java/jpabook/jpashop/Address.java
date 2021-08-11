package jpabook.jpashop;

import lombok.Getter;

import javax.persistence.Embeddable;

@Embeddable // jpa 내장타입 내장될수있다
@Getter
public class Address {
    private String city;
    private String street;
    private String zipcode;
    protected Address() {
    }
    public Address(String city, String street, String zipcode){
        this.city= city;
        this.street=street;
        this.zipcode=zipcode;
    }
    //protected 가능

}
