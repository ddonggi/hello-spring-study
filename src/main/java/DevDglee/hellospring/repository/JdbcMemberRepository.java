package DevDglee.hellospring.repository;

import DevDglee.hellospring.domain.Member;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class JdbcMemberRepository implements MemberRepository{
    /*
    * DB와 연동해서 이용하기위한 MemberRepository 인터페이스의 구현체 클래스
    */

    private final DataSource dataSource;

    //application.properties 에 datasource 세팅을 해 놓았기때문서, 스프링 부트가 데이터소스를 만들어 놓고 주입해 준다.
    public JdbcMemberRepository(DataSource dataSource) {//throws SQLException {
        this.dataSource = dataSource;
//        dataSource.getConnection();//connection을 얻을 수 있다.
    }

    @Override
    public Member save(Member member) {
        //저장을 위한 쿼리
        String sql = "insert into member(name) values(?)"; // ?는 파라미터 바인딩

        Connection connection = dataSource.getConnection();

        PreparedStatement psmt = connection.prepareStatement();
        pstmt.setString(1,member.getName());
        psmt.executeUpdate();
        return null;
    }

    @Override
    public Optional<Member> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public Optional<Member> findByName(String name) {
        return Optional.empty();
    }

    @Override
    public List<Member> findAll() {
        return null;
    }
}
