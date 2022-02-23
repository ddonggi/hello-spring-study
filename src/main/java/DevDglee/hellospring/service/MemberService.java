package DevDglee.hellospring.service;
/*
 * Created by 이동기 on 2021-10-26
 */

import DevDglee.hellospring.domain.Member;
import DevDglee.hellospring.repository.MemberRepository;
import DevDglee.hellospring.repository.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MemberService {

//    private final MemberRepository memberRepository = new MemoryMemberRepository();
    //외부에서 받아오도록 바꿔주자.
    private final MemberRepository memberRepository;

    @Autowired //MemberRepository interface 이지만, 스프링은 구현체인 MemoryMemberRepository를 넣어준다.
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    /**
     * join
     */
    public Long join(Member member){
        //dont dupl name
        // memberRepository.findByName(member.getName()); ctrl + alt + v  : auto gen
    /*
        Optional<Member> result = memberRepository.findByName(member.getName());
        //if value is not null
        result.ifPresent(m->{
            throw new IllegalStateException("exist member");
        });
*/
        validateDuplicateMember(member);
        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName())
                .ifPresent(member1 -> {
                    throw new IllegalStateException("exist member");
                });
    }

    /**
     * find all members
     */
    public List<Member> findMembers(){
        return memberRepository.findAll();
    }


    public Optional<Member> findOne(Long memberId){
        return memberRepository.findById(memberId);
    }
}
