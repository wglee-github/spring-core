package hello.core.scan;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Primary;

import hello.core.AutoAppConfig;
import hello.core.discount.DiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.repository.MemberRepository;
import hello.core.member.service.MemberService;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;

public class AutoAppConfigTest {

	@Test
	void basicScan() {
		ApplicationContext ac = new AnnotationConfigApplicationContext(AutoAppConfig.class);
		
		MemberService memberService = ac.getBean(MemberService.class);
		Assertions.assertThat(memberService).isInstanceOf(MemberService.class);
		
		OrderServiceImpl bean = ac.getBean(OrderServiceImpl.class);
		MemberRepository memberRepository = bean.getMemberRepository();
		System.out.println("memberRepository = " + memberRepository);
	}
	
	/**
	 * 빈 타입이 중복되는 경우 오류가 발생한다.
	 * @Autowired는 빈의 Type으로 조회 하기 때문에 DI 시엥 빈이 중복되어 있으면 오류가 발생한다.(빈 이름이 달라도 오류발생)
	 * @Qualifier("mainRepository") 를 사용하거나, @Primary 를 사용하면 된다.
	 * 
			예) @Primary 선언된 구현체를 DI 해준다.
		  	@Autowired 
		  	private DiscountPolicy discountPolicy;
		  
		    예) @Primary 선언된 구현체가 있어도 @Qualifier 선언하면 해당 구현체를 DI 해준다.
		  	@Autowired 
		  	private @Qualifier("mainRepository") DiscountPolicy discountPolicy; 
	 *  
	 */
	@Test
	void qualifierAndPrimaryTest() {
		ApplicationContext ac = new AnnotationConfigApplicationContext(AutoAppConfig.class);
		OrderServiceImpl bean = ac.getBean(OrderServiceImpl.class);
		DiscountPolicy discountPolicy = bean.getdisDiscountPolicy();
		System.out.println("discountPolicy = " + discountPolicy);
	}
	
}
