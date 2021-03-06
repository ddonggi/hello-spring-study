package DevDglee.hellospring.service;
/*
 * Created by 이동기 on 2021-10-26
 */

import DevDglee.hellospring.domain.Member;
import DevDglee.hellospring.repository.MemberRepository;
import DevDglee.hellospring.repository.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

//@Service
@Transactional
public class MemberService {

    private final MemberRepository memberRepository;

    @Autowired
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

//MemberRepository interface 이지만, 스프링은 구현체인 MemoryMemberRepository를 넣어준다.
    //private final MemberRepository memberRepository = new MemoryMemberRepository();
    //외부에서 받아오도록 바꿔주자.

//    emberService의 생성자도 memberRepository를 매개변수로 받기 때문에,
//    MemoryMemberRepository클래스에 @Repository 를 추가하여 스프링 빈으로 등록하면,
//    MemberService가 생성될때 스프링 컨테이너에 등록된MemoryMemberRepository 스프링 빈을 가져와 쓴다.)

    /**
     * join
     */
    @Transactional
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
//        long start = System.currentTimeMillis();
//        try {
            validateDuplicateMember(member);
            memberRepository.save(member);
            return member.getId();
//        }finally {
//            long finish = System.currentTimeMillis();
//            long timeMs = finish-start;
//            System.out.println("join = "+timeMs+"ms");
//        }
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
