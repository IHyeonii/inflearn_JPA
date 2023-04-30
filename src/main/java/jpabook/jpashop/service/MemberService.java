package jpabook.jpashop.service;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true) //JPA 데이터 변경은 Transactional 안에서, (readOnly = true) 읽기전용, 데이터 변경 안 됨
@RequiredArgsConstructor // final 필드만 생성자 만들어줘
public class MemberService {

//  @Autowired
//  private MemberRepository memberRepository;

  private final MemberRepository memberRepository;


  // 1) 회원가입 -> Member 객체 넘겨서
  @Transactional // 얘는 변경 가능
  public Long join(Member member) {
    validateDuplicateMember(member); //2) 중복회원 검증 로직 추가
    memberRepository.save(member);
    return member.getId();
  }

  // 3) 중복이면
  private void validateDuplicateMember(Member member) {
    // 이름이 중복되면 안 되게
    List<Member> findMembers = memberRepository.findByName(member.getUsername());

    if(!findMembers.isEmpty()) { // findMembers가 존재하면
      throw new IllegalStateException("이미 존재하는 회원입니다.");
    }
  }

  //회원 전체 조회
  public List<Member> findMembers() {
    return memberRepository.findAll();
  }

  // id로 한 회원만 조회
  public Member findOne(Long memberId) {
    return memberRepository.findOne(memberId);
  }

  //나는
//  public Member findOne(Member member) {
//    return memberRepository.findOne(member.getId());
//  }
}
