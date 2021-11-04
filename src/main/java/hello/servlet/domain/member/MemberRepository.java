package hello.servlet.domain.member;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// 동시성 문제는 고려하지 않았음, 실무에서는 ConcurrentHashMap, AtomicLong 사용을 고려한다.
public class MemberRepository {

    private static Map<Long, Member> store = new HashMap<>();
    private static long sequence = 0L;

    // 싱글턴으로 사용하기 위해 객체 생성성
    private static final MemberRepository instance = new MemberRepository();

    public static MemberRepository getInstance() {return instance;}

    private MemberRepository() {}

    public Member save(Member member) {
        member.setId(++sequence);
        store.put(member.getId(), member);
        return member;
    }

    public Member findById(Long id) {
        return store.get(id);
    }

    public List<Member> findAll() {
        // ArrayList<>(store.values()) : store에 저장되어있는 모든 Member 객체를 가져와서 ArrayList 객체에 담는다.
        // store 객체에 있는 값들을 변경하지 않고 ArrayList에 담을 수 있음 store 자체를 보호하기 위해서 사용
        return new ArrayList<>(store.values());
    }

    public void clearStore() {
        store.clear();
    }

}
