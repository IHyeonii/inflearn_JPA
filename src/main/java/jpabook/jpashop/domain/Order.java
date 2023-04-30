package jpabook.jpashop.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "orders")
public class Order {
  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "order_id")
  private Long id;
  @ManyToOne //오더(다)랑 멤버(1)는 다대일, 얘가 연관관계 주인
  @JoinColumn(name = "member_id") //매핑을 뭘로 할꺼야? FK가 member_id
  private Member member; // 연관관계 주인 -> 오더의 member 바꾸면 업데이트
  @OneToMany(mappedBy = "order")
  private List<OrderItem> orderItems = new ArrayList<>();
  @OneToOne
  @JoinColumn(name = "delivery_id") //FK, 얘가 연관관계 주인으로
  private Delivery delivery; //배송정보
  private LocalDateTime orderDate; //주문시간
  @Enumerated(EnumType.STRING)
  private OrderStatus status; //주문상태
}
