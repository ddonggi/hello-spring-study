package DevDglee.hellospring.repository;

import DevDglee.hellospring.domain.Member;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import javax.xml.transform.Result;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

//@Repository
public class JdbcMemberRepository implements MemberRepository{
    /*
    * DB와 연동해서 이용하기위한 MemberRepository 인터페이스의 구현체 클래스
    */

    private final DataSource dataSource;

    //application.properties 에 datasource 세팅을 해 놓았기때문서, 스프링 부트가 데이터소스를 만들어 놓고 주입해 준다.
    public JdbcMemberRepository(DataSource dataSource) {//throws SQLException {
        this.dataSource = dataSource;
//        dataSource.getConnection();//connection을 얻을 수 있다. 하지만 새로운 connection 이 계속 주어지기때문에,
        //DataSourceUtils 를 통해서 connection 을 유지시켜서 써야 한다. 닫을때도 마찬가지로 utils로 닫는다.
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
        String sql = "select * from member where id =?";

        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try{
            conn = getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setLong(1,id);

            rs = pstmt.executeQuery(); //조회기때문에 update가 아니고 query만 함

            if(rs.next()){
                Member member = new Member();
                member.setId(rs.getLong("id"));
                member.setName(rs.getString("name"));
                return Optional.of(member);
            }else{
                return Optional.empty();
            }

        } catch (SQLException e) {
            throw new IllegalStateException(e);
        } finally{
            close(conn,pstmt,rs);
        }
    }

    @Override
    public Optional<Member> findByName(String name) {
        String sql = "select * from member where name =?";

        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try{
            conn = getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1,name);

            rs = pstmt.executeQuery();

            if(rs.next()){
                Member member = new Member();
                member.setId(rs.getLong("id"));
                member.setName(rs.getString("name"));
                return Optional.of(member);
            }else{
                return Optional.empty();
            }

        } catch (SQLException e) {
            throw new IllegalStateException(e);
        } finally{
            close(conn,pstmt,rs);
        }
    }

    @Override
    public List<Member> findAll() {
        String sql = "select * from member";

        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try{
            conn = getConnection();
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();

            List<Member> members = new ArrayList<>();
            while(rs.next()){
                Member member = new Member();
                member.setId(rs.getLong("id"));
                member.setName(rs.getString("name"));
                members.add(member);
            }
            return members;
        }catch (Exception e){
            throw new IllegalStateException(e);
        }finally {
            close(conn,pstmt,rs);
        }
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
