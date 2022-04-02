package DevDglee.hellospring.repository;

import DevDglee.hellospring.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface SpringDataJpaRepository  extends JpaRepository<Member,Long>,MemberRepository {
    @Override
    Optional<Member> findByName(String name);
}
