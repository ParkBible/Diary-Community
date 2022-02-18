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
	
	// ȸ������
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
            // ������ �߻��ϸ� alert�� ���� �ڷΰ���
            out.println("<script>alert('�̹� ���Ե� ID�Դϴ�.'); history.go(-1);</script>");
            out.flush();
		}
		
		return uc;
	}

	// �α���
	// id, pw ���� �����ͺ��̽��� ������ ����Ͽ�, id���� �����Ѵ�.
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
            // ������ �߻��ϸ� alert�� ���� �ڷΰ���
            out.println("<script>alert('ID �Ǵ� ��й�ȣ�� �߸��Ǿ����ϴ�.'); history.go(-1);</script>");
            out.flush();
		}
		return idInfo;
	}

}
