package hello.core.beanfind;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Iterator;
import java.util.Map;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoUniqueBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;

public class ApplicationContextExtendsFindTest {

	AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig.class);
	
	@Test
	@DisplayName("부모 타입으로 조회 시 자식 타입이 둘 이상 있으면 중복오류가 발생한다.")
	void findBeanByParentTypeDuplicate() {
		
		assertThrows(NoUniqueBeanDefinitionException.class, () -> ac.getBean(DiscountPolicy.class));
	}
	
	@Test
	@DisplayName("부모 타입으로 조회 시 자식 타입이 둘 이상 있으면 이름을 지정하면 된다.")
	void findBeanByParentBeanName() {
		DiscountPolicy discountPolicy = ac.getBean("fixDiscountPolicy",DiscountPolicy.class);
		assertThat(discountPolicy).isInstanceOf(DiscountPolicy.class);
	}
	
	@Test
	@DisplayName("특정 하위 타입으로 조회")
	void findBeanBySubType() {
		DiscountPolicy discountPolicy = ac.getBean(FixDiscountPolicy.class);
		assertThat(discountPolicy).isInstanceOf(DiscountPolicy.class);
	}
	
	@Test
	@DisplayName("부모타입으로 모두 조회하기")
	void findBeanByParentType() {
		Map<String, DiscountPolicy> beansOfType = ac.getBeansOfType(DiscountPolicy.class);
		for (String key : beansOfType.keySet()) {
			System.out.println("key = " + key + " value = " + ac.getBean(key));
		}
		
		assertThat(beansOfType.size()).isEqualTo(2);
	}
	
	@Test
	@DisplayName("부모타입으로 모두 조회하기 - 오브젝트")
	void findBeanByObjectType() {
		Map<String, Object> beansOfType = ac.getBeansOfType(Object.class);
		for (String key : beansOfType.keySet()) {
			System.out.println("key = " + key + " value = " + ac.getBean(key));
		}
		
	}
	
	@Configuration
	static class TestConfig{
		
		@Bean
		public DiscountPolicy fixDiscountPolicy() {
			return new FixDiscountPolicy();
		}
		
		@Bean
		public DiscountPolicy rateDiscountPolicy() {
			return new RateDiscountPolicy();
		}
	}
	
}
