package jpabook.jpashop.service;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.repository.MemberRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional // 데이터 변경할거니까, 이거 있어야 rollback 됨
class MemberServiceTest {

  @Autowired MemberService memberService;
  @Autowired MemberRepository memberRepository;

  @Test
  @Rollback(false)
  void 회원가입() throws Exception {

    // given : 이런게 주어졌을 때
    Member member = new Member();
    member.setUsername("test");

    // when : 이렇게 하면
    Long saveId = memberService.join(member);

    //then: 결과가 이렇게 나와야 돼
    assertEquals(member, memberRepository.findOne(saveId));
  } // join한 member가 repository에서 찾은거랑 같아야돼

  // 테스트 성공해도 DB에 저장X -> Transactional 때문에 commit 안 해
  // DB에 남기고 싶으면 @Rollback(false) 추가

  @Test
  void 중복회원_예외() throws Exception {
    //given : 회원 만들고
    Member member1 = new Member();
    member1.setUsername("test1");

    Member member2 = new Member();
    member2.setUsername("test1");

    //when
    memberService.join(member1);
    try {
      memberService.join(member2); // name이 같아서 예외가 발생해야 돼 -> try catch
    } catch (IllegalStateException e) {
      return;
    }

    //then
    fail("예외가 발생해야 한다.");

  }

}