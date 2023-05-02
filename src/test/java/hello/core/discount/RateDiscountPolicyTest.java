package hello.core.discount;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import hello.core.member.Grade;
import hello.core.member.Member;

class RateDiscountPolicyTest {

	RateDiscountPolicy rateDiscountPolicy = new RateDiscountPolicy();
	
	@Test
	@DisplayName("VIP 회원이면 10%할인 금액이 적용되어야 한다.")
	void vip_o() {
		
		// given
		Member member = new Member(1L, "memberA", Grade.VIP);
		
		// when
		int discount =  rateDiscountPolicy.discount(member, 10000);
		
		// then
		Assertions.assertThat(discount).isEqualTo(1000);
	}
	
	@Test
	@DisplayName("VIP 회원이 아니면 10%할인 금액이 적용되지 않아야 한다.")
	void vip_x() {
		
		// given
		Member member = new Member(1L, "memberA", Grade.BASIC);
		
		// when
		int discount =  rateDiscountPolicy.discount(member, 10000);
		
		// then
		Assertions.assertThat(discount).isEqualTo(0);
	}

}
