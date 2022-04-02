package DevDglee.hellospring.service;

import DevDglee.hellospring.domain.Member;
import DevDglee.hellospring.repository.MemberRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;


/*
 * Created by 이동기 on 2022-02-21
 */

@SpringBootTest
@Transactional
class MemberServiceIntegrationTest {

    @Autowired MemberService memberService;
    @Autowired MemberRepository memberRepository;

    @Test
//    @Commit
    void 회원가입() {
        //given
        Member member = new Member();
        member.setName("springss");

        //when
        Long saveId = memberService.join(member); //join 검증

        //then
        Member findMember = memberService.findOne(saveId).get();
        //assert 자동 import는 junit이 되기때문에, assertj것을 import하자
        assertThat(member.getName()).isEqualTo(findMember.getName());
    }

    //예외사항도 잘 확인해봐야함 join 의 validateDuplicateMember 을 검증해봐야함
    @Test
    public void 중복_회원_예외(){
        //given
        Member member1 = new Member();
        member1.setName("spring");
//        Member member2 = new Member();
//        member2.setName("spring");

        //when
        Long saveId = memberService.join(member1);
        //이름이 똑같이 설정했기 떄문에 예외사항이 발생해야함 ->
        // 1. try catch로 잡을수도 있음
/*        try {
            memberService.join(member2);
            fail();
        }catch (IllegalStateException e){
            assertThat(e.getMessage()).isEqualTo("exist member");
        }*/

        // 2. assertThrows + 람다
//        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));
//        assertThat(e.getMessage()).isEqualTo("exist member");

        //then
        Member findMember = memberService.findOne(saveId).get();
        assertThat(member1.getName()).isEqualTo(findMember.getName());

    }
}