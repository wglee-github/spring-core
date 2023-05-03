package hello.core.beanfind;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import hello.core.AppConfig;

public class ApplicationContextInfoTest {

	/**
	 * 스프링에 등록된 모든 빈 정보를 출력해보자.
	 */
//	@Test
	@DisplayName("모든 빈 출력하기")
	void findAllBean() {
		// given
		AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
		
		// when
		// ac.getBeanDefinitionNames() : 스프링에 등록된 모든 빈 이름을 조회한다.
		String[] beans =  ac.getBeanDefinitionNames();
		
		// then
		for (String beanName : beans) {
			// ac.getBean() : 빈 이름으로 빈 객체(인스턴스)를 조회한다.
			Object bean = ac.getBean(beanName);
			System.out.println("beanName = " + beanName + " | bean =  " + bean);
		}
		
	}
	
	/**
	 * 스프링이 내부에서 사용하는 빈은 제외하고, 내가 등록한 빈만 출력해보자.
	 */
	@Test
	@DisplayName("애플리케이션 빈 출력하기")
	void findApplicationBean() {
		// given
		AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
		
		// when
		String[] beans =  ac.getBeanDefinitionNames();
		
		// then
		for (String beanName : beans) {
			BeanDefinition beanDefinition = ac.getBeanDefinition(beanName);
			
			/**
			 * 스프링이 내부에서 사용하는 빈은 getRole() 로 구분할 수 있다.
			 	* Role ROLE_APPLICATION: 직접 등록한 애플리케이션 빈
			 	* Role ROLE_INFRASTRUCTURE: 스프링이 내부에서 사용하는 빈
			 */
			if(beanDefinition.getRole() == BeanDefinition.ROLE_APPLICATION) {
				Object bean = ac.getBean(beanName);
				System.out.println("beanName = " + beanName + " | bean =  " + bean);
			}
		}
		
	}
	
}
