package jpabook.jpashop.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Member {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY) //GenerationType.IDENTITY -> AI로 자동기입
  @Column(name = "member_id") //DB 테이블의 PK 컬럼명
  private Long id;
  private String username;
  @Embedded //임베디드 타입 사용 -> Address 클래스에 @Embeddable
  private Address address;
  @OneToMany(mappedBy = "member")
  private List<Order> orders = new ArrayList<>();
  //멤버(1)한테 오더(다)는 일대다, 연관관계 거울(mappedBy=오더테이블의 member 필드)
  //읽기전용으로 값을 넣어도 FK 값 변경 X
}
