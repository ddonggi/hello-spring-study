package DevDglee.hellospring.service;

import DevDglee.hellospring.domain.Member;
import DevDglee.hellospring.repository.MemoryMemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;


/*
 * Created by 이동기 on 2022-02-21
 */

class MemberServiceTest {

//    MemberService memberService = new MemberService(memberRepository);

    //new 로 다른 객체가 생성이 되면, 내용물이 달라질 수 있다.
    //현재 MemberService 에 MemoryMemberRepository가 인스턴스화 되어있기때문에, 여기서도 new 를 써서 다른 인스턴스를 쓸 필요는 없다.
//    MemoryMemberRepository memberRepository = new MemoryMemberRepository();
    //같은걸로 테스트 하도록 바꾸는게 좋다. -> MemberService 에 선언한 것을 바꿔보자
    MemberService memberService;
    MemoryMemberRepository memberRepository;


    //각 테스트를 실행하기 전에 전에->
    @BeforeEach
    public void beforeEach(){
        memberRepository = new MemoryMemberRepository(); //MemoryMemberRepository 인스턴스화 후 멤버변수에 선언
        memberService = new MemberService(memberRepository);
    }
    //MemberService 입장에서는 직접 new 하지 않고, memberRepository를 외부에서 넣어준다 : DI(dependency Injection)

    @AfterEach //동작한 후에
    public void afterEach(){ // memory clear
        memberRepository.clearStore();
    }

    @Test
    void 회원가입() {
        //given
        Member member = new Member();
        member.setName("spring");

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
        Member member2 = new Member();
        member2.setName("spring");

        //when
        memberService.join(member1);
        //이름이 똑같이 설정했기 떄문에 예외사항이 발생해야함 ->
        // 1. try catch로 잡을수도 있음
/*        try {
            memberService.join(member2);
            fail();
        }catch (IllegalStateException e){
            assertThat(e.getMessage()).isEqualTo("exist member");
        }*/

        // 2. assertThrows + 람다
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));
        assertThat(e.getMessage()).isEqualTo("exist member");

        //then
    }

    @Test
    void findMembers() {
    }

    @Test
    void findOne() {
    }
}