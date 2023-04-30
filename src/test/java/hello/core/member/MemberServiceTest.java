package hello.core.member;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import hello.core.member.service.MemberServcieImpl;
import hello.core.member.service.MemberService;

class MemberServiceTest {

	MemberService memberService = new MemberServcieImpl();
	
	@Test
	void join() {
		// given
		Member member = new Member(1L, "memberA", Grade.VIP);
		
		// when
		memberService.join(member);
		Member findMember = memberService.findMember(1L);
		
		// then
		/**
		 *  isEqualTo() 메서드는 값 비교 메서드이지만, 객체 비교를 할 때에는 참조 주소로 비교합니다.
			· 여기서는 메모리에 저장된 Member 객체를 조회 했기 때문에 true 가 나오지만
			· 실제 DB에서 조회한 경우에는 참조 주소가 달라지기 때문에 false가 나온다.
		 */
		
		Assertions.assertThat(member).isEqualTo(findMember);
		Assertions.assertThat(findMember.getName()).isEqualTo("memberA");
	}

}
