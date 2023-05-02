package hello.core.member.service;

import hello.core.member.Member;
import hello.core.member.repository.MemberRepository;
import hello.core.member.repository.MemoryMemberRepository;

public class MemberServiceImpl implements MemberService {

	/**
	 * 1. DIP(의존관계 역전 원칙 : Dependency inversion principle)를 위반
	 * 2. memberRepository의 구현체가 달라지면 OCP(개방-폐쇄 원칙 : Open/closed principle)를 위반하게 된다.
	 */
//	private final MemberRepository memberRepository = new MemoryMemberRepository();
	
	/**
	 * DIP, OCP를 지키기 위해 
	 * 1. 인터페이스만 의존하도록 변경한다.
	 * 2. 외부에서 구현체를 대신 생성하고 주입해준다.(AppConfig.java) 
	 */
	private final MemberRepository memberRepository;
	
	public MemberServiceImpl(MemberRepository memberRepository) {
		this.memberRepository = memberRepository;
	}

	/**
	 * final로 선언된 필드가 있는 경우 선언과 동시에 초기화를 해주거나, 생성자를 통해서 주입해줘야 한다.
	 * 그런데 위에 처럼 이민 argument가 있는 생성자를 만든 경우 default 생성자는 만들 수 없다. 
	 * final로 선언된 필드를 초기화 해줘야 하기 때문이다. 따라서 해당 필드를 초기화 해줘야 한다.
	 */
	public MemberServiceImpl() {
		this.memberRepository = new MemoryMemberRepository();
	}





	@Override
	public void join(Member member) {
		memberRepository.save(member);
	}

	@Override
	public Member findMember(Long id) {
		return memberRepository.findById(id);
	}

}
