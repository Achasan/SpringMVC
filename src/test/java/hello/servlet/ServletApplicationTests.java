package hello.servlet;

import hello.servlet.domain.member.Member;
import hello.servlet.domain.member.MemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
class ServletApplicationTests {

	MemberRepository memberRepository = MemberRepository.getInstance();

	// @Test 어노테이션이 있는 테스트 메서드 각각 하나 씩 끝날 때 마다 실행되는 메서드
	@AfterEach
	void afterEach() {
		memberRepository.clearStore();
	}

	@Test
	void save() {
		//given
		Member member = new Member("hello", 20);
		//when
		Member savedMember = memberRepository.save(member);
		//then
		Member findMember = memberRepository.findById(savedMember.getId());
		assertThat(findMember).isEqualTo(savedMember);
	}

	@Test
	void findAll() {
		//given
		Member member1 = new Member("hello1", 20);
		Member member2 = new Member("hello2", 30);
		memberRepository.save(member1);
		memberRepository.save(member2);
		//when
		List<Member> list = memberRepository.findAll();

		//then
		// List 객체의 크기가 2개인지 확인, member1과 member2를 save 했으므로 size()는 2가 나와야함
		assertThat(list.size()).isEqualTo(2);
		// List 객체가 member1, member2 객체를 포함하는 지 확인
		assertThat(list).contains(member1, member2);
	}

}
