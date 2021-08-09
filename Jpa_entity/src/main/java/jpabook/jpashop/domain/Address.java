package jpabook.jpashop.domain;

import javax.persistence.Embeddable;

@Embeddable // jpa 내장타입 내장될수있다
public class Address {
    private String city;
    private String street;
    private String zipcode;
}
