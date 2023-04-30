package jpabook.jpashop.domain;

import lombok.Getter;

import javax.persistence.Embeddable;

@Embeddable // 값 타입을 정의
@Getter
public class Address {
  private String city;
  private String street;
  private String zipcode;
}
