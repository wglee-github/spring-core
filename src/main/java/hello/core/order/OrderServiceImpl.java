package hello.core.order;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.member.Member;
import hello.core.member.repository.MemberRepository;
import hello.core.member.repository.MemoryMemberRepository;

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
	
	
	public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
		this.memberRepository = memberRepository;
		this.discountPolicy = discountPolicy;
	}

	
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
	
}
