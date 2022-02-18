package magicpacket;

import java.io.PrintWriter;
import java.net.URLEncoder;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;

public class SpringDAO_Springimpl implements SpringDAO{
	
	private JdbcTemplate jdbcTemplate = null;

	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public List<SpringVO> findAll(Integer type, String id) throws Exception {
		
		RowMapper<SpringVO> rowMapper = new RowMapper<SpringVO>() {

			@Override
			public SpringVO mapRow(ResultSet rs, int arg1) throws SQLException {
				SpringVO vo = new SpringVO();
				vo.setNo(rs.getInt("no"));
				vo.setId(rs.getString("id"));
				vo.setEmotion(rs.getString("emotion"));
				vo.setTime(rs.getString("the_time"));
				vo.setData(rs.getString("data"));
				vo.setUp(rs.getInt("up"));
				vo.setReple(rs.getString("reple"));
				return vo;
			}
		};
		
		List<SpringVO> ls = null;
		if(type == 0) {
			// �ֽűۼ�(default)
			ls = jdbcTemplate.query("select * from Text_T ORDER BY no DESC", rowMapper);
		}
		else if(type == 1) {
			// ������
			ls = jdbcTemplate.query("select * from Text_T ORDER BY up DESC", rowMapper);
		}

		else if(type == 2){
			// ���ۺ���
			ls = jdbcTemplate.query("select * from Text_T WHERE id = ?", rowMapper, id);
		}
		else if(type == 3){
			// �����ȼ�
			ls = jdbcTemplate.query("select * from Text_T ORDER BY no", rowMapper);
		}
		else if(type == 4){
			// ���/����
			ls = jdbcTemplate.query("select * from Text_T WHERE emotion = '���/����' ORDER BY the_time DESC", rowMapper);
		}
		else if(type == 5){
			// ����/��ħ
			ls = jdbcTemplate.query("select * from Text_T WHERE emotion = '����/��ħ' ORDER BY the_time DESC", rowMapper);
		}
		else if(type == 6){
			// ȭ��/¥��
			ls = jdbcTemplate.query("select * from Text_T WHERE emotion = 'ȭ��/¥��' ORDER BY the_time DESC", rowMapper);
		}
		else if(type == 7){
			// ���/����
			ls = jdbcTemplate.query("select * from Text_T WHERE emotion = '���/����' ORDER BY the_time DESC", rowMapper);
		}
		else if(type == 8){
			// ��Ÿ
			ls = jdbcTemplate.query("select * from Text_T WHERE emotion = '��Ÿ' ORDER BY the_time DESC", rowMapper);
		}
		else {
			ls = jdbcTemplate.query("select * from Text_T ORDER BY up DESC", rowMapper);
		}
		
		return ls;
	}

	@Override
	public int add(SpringVO pvo, HttpServletRequest request, HttpServletResponse response) throws Exception {
		PreparedStatementSetter pss = new PreparedStatementSetter() {
			@Override
			public void setValues(PreparedStatement stmt) throws SQLException {
				//stmt.setString(1, Util.han(pvo.getEmotion()));
				stmt.setString(1, Util.han(pvo.getId()));
				stmt.setString(2, Util.han(pvo.getEmotion()));
				stmt.setString(3, Util.han(pvo.getData()));
				stmt.setString(4, Util.han(pvo.getReple()));
			}
		};
		
		int uc = 0;
		try {
			uc = jdbcTemplate.update("INSERT INTO Text_T VALUES(default, ?, ?, ?, NOW(), 0, ?);", pss);
		}
		catch(Exception e) {
			System.out.println(e.toString());
			response.setContentType("text/html; charset=UTF-8");
            PrintWriter out = response.getWriter();
            // ������ �߻��ϸ� alert�� ���� �ڷΰ���
            out.println("<script>alert('������ 1000�ڸ� �ʰ��մϴ�.'); history.go(-1);</script>");
            out.flush();
		}
		
		return uc;
	}

	@Override
	public int del(int no) throws Exception {
		int uc = jdbcTemplate.update("DELETE FROM Text_T WHERE no = " + no);
		
		return uc;
	}

	@Override
	public int up(String id, int no, HttpServletRequest request, HttpServletResponse response) throws Exception {
		int uc = 0;
		int uc2 = 0;
		try {
			// ��õ ���̺� id, no�� ���
			uc = jdbcTemplate.update("INSERT INTO Up_T VALUES(?, ?);", id, no);
			// �� ���̺� ���� +1 ó��
			uc2 = jdbcTemplate.update("UPDATE Text_T SET up = up + 1 WHERE no = ?;", no);
		}
		catch(Exception e) {
			System.out.println(e.toString());
			response.setContentType("text/html; charset=UTF-8");
            PrintWriter out = response.getWriter();
            // ������ �߻��ϸ� alert�� ���� �ڷΰ���
            out.println("<script>alert('�̹� ������ ���Դϴ�.'); history.go(-1);</script>");
            out.flush();
		}
		
		
		return uc;
	}
	
	// ��۾���
	@Override
	public int add4(SpringVO pvo, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		PreparedStatementSetter pss = new PreparedStatementSetter() {
			@Override
			public void setValues(PreparedStatement stmt) throws SQLException {
				stmt.setInt(1, Integer.valueOf(pvo.getRep_no()));
				stmt.setString(2, Util.han(pvo.getRep_id()));
				stmt.setString(3, Util.han(pvo.getRep_data()));    // data�� �޾ƿ´�.
			}
		};
		
		int uc = 0;
		try {
			uc = jdbcTemplate.update("INSERT INTO Reple_T VALUES(default, ?, ?, ?, NOW())", pss);
		}
		catch(Exception e) {
			System.out.println(e.toString());
			response.setContentType("text/html; charset=UTF-8");
            PrintWriter out = response.getWriter();
            // ������ �߻��ϸ� alert�� ���� �ڷΰ���
            out.println("<script>alert('������ 500�ڸ� �ʰ��մϴ�.'); history.go(-1);</script>");
            out.flush();
		}
		
		
		
		return uc;
	}

	@Override
	public List<SpringVO> find_rep(String no) throws Exception {
		RowMapper<SpringVO> rowMapper = new RowMapper<SpringVO>() {

			@Override
			public SpringVO mapRow(ResultSet rs, int arg1) throws SQLException {
				SpringVO vo = new SpringVO();
				vo.setReple_no(rs.getInt("no"));
				vo.setRep_id(rs.getString("id"));
				vo.setRep_data(rs.getString("rep_data"));
				return vo;
			}
		};
		
		List<SpringVO> ls = jdbcTemplate.query("select * from Reple_T WHERE table_no = ? ORDER BY no ASC", rowMapper, no);

		return ls;
	}
	
	@Override
	public int rep_del(int no) throws Exception {
		int uc = jdbcTemplate.update("DELETE FROM Reple_T WHERE no = " + no);
		
		return uc;
	}

	
}

// �ۿ� �������°� ��ȣ, ���, ����, ����
// ȸ�� db : id(pk), pw
// �� db : no(pk), id(fk), emotion, data, up
// ��õ db : id(pk, fk), no(pk, fk2)

/*

CREATE TABLE User_T(
id VARCHAR(20) NOT NULL PRIMARY KEY,
pw VARCHAR(20) NOT NULL,
phone CHAR(11) NOT NULL
);

CREATE TABLE Text_T(
no INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
id VARCHAR(20) NOT NULL,
emotion VARCHAR(10) NOT NULL,
data VARCHAR(1000) NOT NULL,
the_time datetime NOT NULL,
up INT NOT NULL,
reple VARCHAR(1) NOT NULL
);

alter table Text_T add reple VARCHAR(1) NOT NULL

CREATE TABLE Up_T(
id VARCHAR(20) NOT NULL,
no INT NOT NULL
);

CREATE TABLE Reple_T(
no INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
table_no INT NOT NULL,
id VARCHAR(20) NOT NULL,
rep_data VARCHAR(500) NOT NULL,
time datetime NOT NULL
);

ALTER TABLE Up_T ADD UNIQUE (id, no);
INSERT INTO Up_T VALUES('jimin', 5);

ALTER TABLE Text_T DROP img;
 */
