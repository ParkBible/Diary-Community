package magicpacket;

import java.io.PrintWriter;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;

public class UserDAO_Springimpl implements UserDAO{
	private JdbcTemplate jdbcTemplate = null;

	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
	// 회원가입
	@Override
	public int add(UserVO pvo, HttpServletRequest request, HttpServletResponse response) throws Exception {
		int uc = 0;
		PreparedStatementSetter pss = new PreparedStatementSetter() {
			@Override
			public void setValues(PreparedStatement stmt) throws SQLException {
				stmt.setString(1, Util.han(pvo.getId()));
				stmt.setString(2, Util.han(pvo.getPw()));
				stmt.setString(3, pvo.getPhone());
			}
		};
		try {
			uc = jdbcTemplate.update("INSERT INTO User_T values (?, ?, ?)", pss);
		}
		catch(Exception e) {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			System.out.println(e.toString());
            // 에러가 발생하면 alert을 띄우고 뒤로가기
            out.println("<script>alert('이미 가입된 ID입니다.'); history.go(-1);</script>");
            out.flush();
		}
		
		return uc;
	}

	// 로그인
	// id, pw 값이 데이터베이스에 있으면 통과하여, id값을 리턴한다.
	@Override
	public String findIdPw(UserVO pvo, HttpServletRequest request, HttpServletResponse response) throws Exception {
		String idInfo = null;
		try {
			idInfo = jdbcTemplate.queryForObject("SELECT id FROM User_T WHERE id = ? AND pw = ?", String.class, pvo.getId(), pvo.getPw() );
		}
		catch(Exception e){
			System.out.println(e.toString());
			response.setContentType("text/html; charset=UTF-8");
            PrintWriter out = response.getWriter();
            // 에러가 발생하면 alert을 띄우고 뒤로가기
            out.println("<script>alert('ID 또는 비밀번호가 잘못되었습니다.'); history.go(-1);</script>");
            out.flush();
		}
		return idInfo;
	}

}
