package hello.core.order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import hello.core.annotation.MainDiscountPolicy;
import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.member.Member;
import hello.core.member.repository.MemberRepository;
import hello.core.member.repository.MemoryMemberRepository;

@Component
public class OrderServiceImpl implements OrderService{

	/**
	 *  memberService에 의존하는게 아니라 memberRepository에 의존하자.
	 *  memberService에 의존하면 회원 도메인의 이슈로 인해 변경이 일어 날때마다 영향을 받을 수 있다.
	 *  하지만 memberRepository 조회의 역할만 하기때문에 회원 도메인 입장에서도 변경될 이슈가 적다. 
	 *  따라서 지금과 같이 단순히 조회만 하는 경우 service를 거치지 말고 repository에 바로 접근하는것이 유리할 수 있다. 
	 */
//	private final MemberRepository memberRepository = new MemoryMemberRepository();
//	private final DiscountPolicy discountPolicy = new FixDiscountPolicy();
//	private final DiscountPolicy discountPolicy = new RateDiscountPolicy();
	
	/**
	 * DIP 를 지키자.
	 */
	private final DiscountPolicy discountPolicy;
	private final MemberRepository memberRepository;
	
	
//	@Autowired
//	public void setDiscountPolicy(DiscountPolicy discountPolicy) {
//		System.out.println("setDiscountPolicy = " + discountPolicy.getClass());
//		this.discountPolicy = discountPolicy;
//	}
	
//	@Autowired(required = false) // required false를 하면 autowired 시 주입해야 되는 객체가 스프링 빈에 등록되어 있지 않으면 해당 set 메소드를 실행하지 않는다. 그래서 오류도 발생하지 않는다.
//	public void setMemberRepository(MemberRepository memberRepository) {
//		System.out.println("setMemberRepository = " + memberRepository.getClass());
//		this.memberRepository = memberRepository;
//	}

	/**
	 *  @RequiredArgsConstructor 사용하면 final로 선언된 필드들을 주입받을 수 있는 생성자를 자동으로 만들어 준다.
	 *  따라서 아래 코드 필요 없음.
	 */
	@Autowired
	public OrderServiceImpl(MemberRepository memberRepository, @MainDiscountPolicy DiscountPolicy rateDiscountPolicy) {
		System.out.println("OrderServiceImpl");
		this.memberRepository = memberRepository;
		this.discountPolicy = rateDiscountPolicy;
	}

	/**
	 * 테스트 용도 - 없으면 특정 Test 클래스에서 컴파일 오류 발생.
	 * final로 선언된 필드는 무조건 초기화를 해줘야 하기때문에 빈 기본생성자를 추가하는 경우 기본 생성자 안에서 무조건 필드를 초기화 해줘야 한다.
	 * 빈 기본생성자는 생성이 안된다.
	 */
	public OrderServiceImpl() {
		this.memberRepository = new MemoryMemberRepository(); 
		this.discountPolicy = new FixDiscountPolicy();
	}

	@Override
	public Order createOrder(Long memberId, String itemName, int itemPrice) {
		Member member = memberRepository.findById(memberId);
		int discountPrice = discountPolicy.discount(member, itemPrice);
		
		return new Order(memberId, itemName, itemPrice, discountPrice);
	}
	

	//테스트 용도
	public MemberRepository getMemberRepository() {
		return memberRepository;
	}	
	
	//테스트 용도
	public DiscountPolicy getdisDiscountPolicy() {
		return discountPolicy;
	}
}
