package DevDglee.hellospring.service;

import DevDglee.hellospring.domain.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

/*
 * Created by 이동기 on 2022-02-21
 */

class MemberServiceTest {

    MemberService memberService = new MemberService();

    @Test
    void 회원가입() {
        //given
        Member member = new Member();
        member.setName("dglee");

        //when
        Long saveId = memberService.join(member);
        //then
        //MemberService의 join을 검증
        Member findMember = memberService.findOne(saveId).get();
        Assertions.assertThat(member.getName()).isEqualTo(findMember.getName());

    }
    //예외사항도 잘 확인해봐야함 join 의 validateDuplicateMember 을 검증해봐야함
    @Test
    public void 중복_회원_예외(){
        //given
        Member member1 = new Member();
        member1.setName("spring");
        Member member2 = new Member();
        member2.setName("spring");

        //when
        memberService.join(member1);
        //이름이 똑같이 설정했기 떄문에 예외사항이 발생해야함 -> 1. try catch로 잡을수도 있음
        try {
            memberService.join(member2);
            fail();
        }catch (IllegalStateException e){
            Assertions.assertThat(e.getMessage()).isEqualTo("exist member");
        }

        // 더 좋은 문법이 있다. assertThrows, 람다
        assertThrows(IllegalStateException.class, ()-> memberService.join(member2));

        //then
    }

    @Test
    void findMembers() {
    }

    @Test
    void findOne() {
    }
}