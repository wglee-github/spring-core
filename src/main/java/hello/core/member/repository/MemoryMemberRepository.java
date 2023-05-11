package hello.core.member.repository;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

import hello.core.member.Member;

@Component
public class MemoryMemberRepository implements MemberRepository{

	// 실무에서는 hashMap 사용 시 동시성 이슈가 발생할 수 있다. 이런 경우 ConcurrentHashMap 을 사용하자
	private static Map<Long, Member> store = new HashMap<>();
	
	@Override
	public void save(Member member) {
		store.put(member.getId(), member);
	}

	@Override
	public Member findById(Long id) {
		return store.get(id);
	}

}
