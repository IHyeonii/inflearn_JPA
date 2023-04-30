package jpabook.jpashop.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE) //1)
@DiscriminatorColumn(name = "DTYPE") //2) DB 입장에서 구분 -> 자식클래스에 @DiscriminatorValue 선언
@Getter
@Setter
public abstract class Item {
  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "item_id")
  private Long id;

  //상속 공통속성, 상속관계 매핑 해줘야 돼 -> 1) 부모클래스에 SINGLE_TABLE 전략 선언
  private String name;
  private int price;
  private int stockQuantity;
  @ManyToMany(mappedBy = "items")
  private List<Category> categories = new ArrayList<Category>();
}
