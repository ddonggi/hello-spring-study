package DevDglee.hellospring.repository;

import DevDglee.hellospring.domain.Member;
import org.springframework.jdbc.datasource.DataSourceUtils;

import javax.sql.DataSource;
import java.sql.*;
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

        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try{
            conn = getConnection();
            pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            pstmt.setString(1, member.getName());

            pstmt.executeUpdate();
            rs=pstmt.getGeneratedKeys();

            if(rs.next()){
                member.setId(rs.getLong(1));
            }else{
                throw new SQLException("id 조회 실패");
            }
            return member;
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }finally {
            close(conn,pstmt,rs);
        }


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

    private Connection getConnection(){
        return DataSourceUtils.getConnection(dataSource);
    }
    private void close(Connection conn,PreparedStatement pstmt, ResultSet rs){
        try{
            if(rs!=null){
                rs.close();
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        try{
            if(pstmt!=null){
                pstmt.close();
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        try{
            if(conn!=null){
                close(conn);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
    private void close(Connection conn) throws SQLException {
        DataSourceUtils.releaseConnection(conn,dataSource);
    }
}
