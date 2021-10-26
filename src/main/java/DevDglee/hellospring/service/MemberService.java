package DevDglee.hellospring.service;
/*
 * Created by 이동기 on 2021-10-26
 */

import DevDglee.hellospring.domain.Member;
import DevDglee.hellospring.repository.MemberRepository;
import DevDglee.hellospring.repository.MemoryMemberRepository;

import java.util.Optional;

public class MemberService {

    private final MemberRepository memberRepository = new MemoryMemberRepository();

    /**
     * join
     */
    public Long join(Member member){
        //dont dupl name
        Optional<Member> result = memberRepository.findByName(member.getName());
        result.ifPresent(m->{
            throw new IllegalStateException("exist member");
        });

        memberRepository.save(member);
        return member.getId();
    }
}
