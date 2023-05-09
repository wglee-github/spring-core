package hello.core.beandefinition;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import hello.core.AppConfig;

/**
 * 
 	* 스프링 빈 설정 메타 정보 - BeanDefinition
		· 스프링은 어떻게 이런 다양한 설정 형식을 지원하는 것일까? 그 중심에는 BeanDefinition 이라는 추상화가 있다.
		· 쉽게 이야기해서 역할과 구현을 개념적으로 나눈 것이다!
		· XML을 읽거나, 자바 코드를 읽어서 BeanDefinition을 만들면 된다.
		· 스프링 컨테이너는 자바 코드인지, XML인지 몰라도 된다. 오직 BeanDefinition만 알면 된다.
		· BeanDefinition 을 빈 설정 메타정보라 한다.
		· @Bean , <bean> 으로 등록된 각각이 메타 정보(BeanDefinition)가 생성된다.
		· 스프링 컨테이너는 이 메타정보를 기반으로 스프링 빈을 생성한다.

	* BeanDefinition 생성
		· AnnotationConfigApplicationContext 는 AnnotatedBeanDefinitionReader 를 사용해서 AppConfig.class 를 읽고 BeanDefinition 을 생성한다.
		· GenericXmlApplicationContext 는 XmlBeanDefinitionReader 를 사용해서 appConfig.xml 설정정보를 읽고 BeanDefinition 을 생성한다.
		· 새로운 형식의 설정 정보가 추가되면, XxxBeanDefinitionReader를 만들어서 BeanDefinition 을 생성하면 된다.	


 	* BeanDefinition 살펴보기
		· BeanDefinition 정보
			· BeanClassName: 생성할 빈의 클래스 명(자바 설정 처럼 팩토리 역할의 빈을 사용하면 없음)
			· factoryBeanName: 팩토리 역할의 빈을 사용할 경우 이름, 예) appConfig
			· factoryMethodName: 빈을 생성할 팩토리 메서드 지정, 예) memberService
			· Scope: 싱글톤(기본값)
			· lazyInit: 스프링 컨테이너를 생성할 때 빈을 생성하는 것이 아니라, 실제 빈을 사용할 때 까지 최대한 생성을 지연처리 하는지 여부
			· InitMethodName: 빈을 생성하고, 의존관계를 적용한 뒤에 호출되는 초기화 메서드 명
			· DestroyMethodName: 빈의 생명주기가 끝나서 제거하기 직전에 호출되는 메서드 명
			· Constructor arguments, Properties: 의존관계 주입에서 사용한다. (자바 설정 처럼 팩토리 역할의 빈을 사용하면 없음)

 */
public class BeanDefinitionTest {

	AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
	
	@Test
	void findApplicationBean() {
		// Spring에 등록된 모든 bean 조회
		String[] beanDefinitionNames = ac.getBeanDefinitionNames();
		
		for (String beanDefinitionName : beanDefinitionNames) {
			// Spring에 등록된 모든 beanDefinition 정보 조회
			BeanDefinition beanDefinition =  ac.getBeanDefinition(beanDefinitionName);
			// 등록된 모든 beanDefinition 중 application에서 직접 정의한 role만 
			if(beanDefinition.getRole() == BeanDefinition.ROLE_APPLICATION) {
				/**
				 	* 조회 결과
				  	· beanDefinitionName = memberService
				  	· bean = hello.core.member.service.MemberServiceImpl@36676c1a 
				  	· beanDefinition = Root bean: 
				  		· class [null]; 
				  		· scope=; 
				  		· abstract=false; 
				  		· lazyInit=null; 
				  		· autowireMode=3; 
				  		· dependencyCheck=0; 
				  		· autowireCandidate=true; 
				  		· primary=false; 
				  		· factoryBeanName=appConfig; 
				  		· factoryMethodName=memberService; 
				  		· initMethodNames=null; 
				  		· destroyMethodNames=[(inferred)]; 
				  		defined in hello.core.AppConfig
				 */
				System.out.println("beanDefinitionName = " + beanDefinitionName + " / bean = " + ac.getBean(beanDefinitionName) + " / beanDefinition = " + beanDefinition);
			}
			
		}
	}
	
	
}
