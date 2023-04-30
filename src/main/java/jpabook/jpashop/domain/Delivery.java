package jpabook.jpashop.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class Delivery {

  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "delivery_id")
  private Long id;
  @OneToOne(mappedBy = "delivery") //거울
  private Order order;
  @Embedded
  private Address address;
  @Enumerated(EnumType.STRING)
  private DeliveryStatus status;
}
