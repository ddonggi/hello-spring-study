package DevDglee.hellospring;

import DevDglee.hellospring.repository.JdbcMemberRepository;
import DevDglee.hellospring.repository.MemberRepository;
import DevDglee.hellospring.repository.MemoryMemberRepository;
import DevDglee.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class SpringConfig {

    private DataSource dataSource;

    @Autowired
    public SpringConfig(DataSource dataSource) {
        this.dataSource = dataSource;
    }
    //다른 코드를 손대지 않고 구현체만 바꿨을 뿐인데, 그대로 쓸 수 있다. 데이터를 DB에 저장하므로 서버를 다시 실행해도 데이터가 안전하게 저장된다.
    // 다형성을 활용한 것, 구현체 바꿔끼기 - 스프링은 이것을 편하게 해주게 도와주고 있다.
    // 과거에는, 의존성이 높아 하나 고치면 다른것들도 함께 고쳐야했다.
    // 어쎔블리, 조립하는쪽만 손대면 다른코드는 손대지 않아도 된다.
    //개방 - 폐쇄 원칙( OCP, Open-closed Principle ) 확장에는 열려있고, 수정(변경)에는 닫혀있다.
    @Bean
    public MemberService memberService(){
        return new MemberService(memberRepository());
    }

    @Bean
    public MemberRepository memberRepository(){
//        return new MemoryMemberRepository();
        return new JdbcMemberRepository(dataSource);
        //다른 코드를 손대지 않고 구현체만 바꿨을 뿐인데, 그대로 쓸 수 있다. 데이터를 DB에 저장하므로 서버를 다시 실행해도 데이터가 안전하게 저장된다.
        // 다형성을 활용한 것, 구현체 바꿔끼기 - 스프링은 이것을 편하게 해주게 도와주고 있다.
        // 과거에는, 의존성이 높아 하나 고치면 다른것들도 함께 고쳐야했다.
        // 어쎔블리, 조립하는쪽만 손대면 다른코드는 손대지 않아도 된다.
        //개방 - 폐쇄 원칙( OCP, Open-closed Principle ) 확장에는 열려있고, 수정(변경)에는 닫혀있다.
    }
}
