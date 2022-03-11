package DevDglee.hellospring;

import DevDglee.hellospring.repository.MemberRepository;
import DevDglee.hellospring.repository.MemoryMemberRepository;
import DevDglee.hellospring.service.MemberService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringConfig {
    @Bean
    public MemberService memberService(){
        return new MemberService(memberRepository());
    }

    @Bean
    public MemberRepository memberRepository(){
        return new MemoryMemberRepository();
    }
}
