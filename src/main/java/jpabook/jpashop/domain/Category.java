package jpabook.jpashop.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Category {
  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "category_id")
  private Long id;
  private String name;
  @ManyToMany //다대다
  @JoinTable(name = "category_item",
      joinColumns = @JoinColumn(name = "category_id"),
      inverseJoinColumns = @JoinColumn(name = "item_id"))
  private List<Item> items = new ArrayList<>();


  //<--계층관계 잡아주기
  @ManyToOne //내 부모가 내 타입
  @JoinColumn(name = "parent_id")
  private Category parent;

  @OneToMany(mappedBy = "parent") // 내 자식은 여러개 가질 수 있으니까
  private List<Category> child = new ArrayList<>();
  //--> 셀프로 양방향 해준거래
}
