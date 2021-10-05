package DevDglee.hellospring.repository;

/*
 * Created by 이동기 on 2021-09-27
 */

import DevDglee.hellospring.domain.Member;

import java.util.List;
import java.util.Optional;

public interface MemberRepository {
    Member save(Member member);
    Optional<Member> findById(Long id); //optional java8에서 추가된 기능
    Optional<Member> findByName(String name);
    List<Member> findAll();
}

