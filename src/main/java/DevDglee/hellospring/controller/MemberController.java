package DevDglee.hellospring.controller;

import DevDglee.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

//스프링 빈과 의존관계
@Controller
public class MemberController {

    //스프링 빈을 등록하고 의존관계 설정하기
    // 회원 컨트롤러가 회원서비스와 회원 리포지토리를 사용할 수 있게 의존관계를 준비하자.
    // 멤버 컨트롤러가 멤버 서비스를 의존하도록 , 그리고 스프링 스럽게 작업해보자

    // @Controller annotation 을 통해, 멤버컨트롤러 객체를 생성해서 스프링 컨테이너에 넣고 , 스프링이 관리한다.
    // ( 즉, 스프링 빈이 관리된다 )

    //멤버서비스를 가져다 쓰기 전에, new 를 이용해 생성해서 쓰지는 말자.
//    private final MemberService memberService = new MemberService();
    //스프링이 관리하게 되면, 스프링이 관리하고, 스프링컨테이너에서 받아서 쓰도록 해야한다.
    // new 를 이용하여 객체를 생성하고 사용하게되면, 다른 컨트롤러에서도 가져다 쓸 수 있게되는 문제가 있다.
    // 그리고 여러 기능이 없기때문에, 하나만 생성해서 공용으로 쓰면 된다.
    // 스프링 컨테이너에 등록해서 쓰면 된다(하나만 등록이 됨)
    private final MemberService memberService;

    // 멤버컨트롤러가 스프링컨테이너가 뜰 때 생성이 되기 때문에, 생성자를 호출한다.

    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }
    //멤버 컨트롤러 생성자에 디펜던시 인젝션
    // Autowired annotation이 있다면, 스프링 컨테이너에서 있는 멤버서비스 빈을 연결해준다.

    // 그런데 빨간줄이 뜬다.(bean could not be found) 왜? MemberService에 가보면, 어떤 Annotaion이 없는 순수한 java class 이기 때문에
    // 스프링이 알 수 있는 방법이 없다.
    // 어떻게 해야하나? 스프링이 알 수 있도록 @Service 를 넣어주면 된다.
}