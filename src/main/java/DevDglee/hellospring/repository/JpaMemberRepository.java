package DevDglee.hellospring.repository;

import DevDglee.hellospring.domain.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

public class JpaMemberRepository implements MemberRepository{

    private final EntityManager entityManager;

    public JpaMemberRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public Member save(Member member) {
        entityManager.persist(member);//영속하다. 영구 저장하다
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        Member member = entityManager.find(Member.class, id);
        return Optional.ofNullable(member);
    }

    @Override
    public Optional<Member> findByName(String name) {
        List<Member> result = entityManager.createQuery("select m from Member m where m.name = :name",Member.class)
                .setParameter("name",name)
                .getResultList();

        return result.stream().findAny();
    }

    @Override
    public List<Member> findAll() {

        //List<Member> result = entityManager.createQuery("select m from Member m",Member.class)
        //.getResultList();
        //return result;

        //⌃ + t 로 리팩터 옵션을 활성화 하고, inline 입력으로 바꿔줄 수 있다.
        return entityManager.createQuery("select m from Member m",Member.class)
                .getResultList();
    }
}
