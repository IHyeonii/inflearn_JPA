package jpabook.jpashop.repository;

import jpabook.jpashop.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class MemberRepository {

//  @PersistenceContext //엔티티 매니저 주입
//  private EntityManager em; // 엔티티 관리해줄 매니저

  private final EntityManager em;

  //저장
  public void save (Member member) {
    em.persist(member); // 반환할게 없으니까 void
  }

  //조회
  public Member findOne(Long id) { //매개변수 Member 아니야, id(PK)로 조회할거니까
    return em.find(Member.class, id);
  }

  //리스트 조회
  public List<Member> findAll () {
    List<Member> resultList = em.createQuery("select m from Member m", Member.class) //JPQL -> Entity 객체를 조회
        .getResultList(); // 결과를 리스트로 반환..?
    return resultList;
  }

  // 이름으로 회원 검색
  public List<Member> findByName(String name) {
    return em.createQuery("select m from Member m where m.username = :name", Member.class)
        .setParameter("name", name)
        .getResultList();
  } // :name -> 파라미터 바인딩, 조회타입은 Member.class

}
