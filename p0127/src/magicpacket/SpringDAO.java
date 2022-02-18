package magicpacket;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface SpringDAO {
    public List<SpringVO> findAll(Integer type, String id) throws Exception;
	public int add(SpringVO pvo, HttpServletRequest request, HttpServletResponse response) throws Exception;
	public int del(int no) throws Exception;
	public int up(String id, int no, HttpServletRequest request, HttpServletResponse response) throws Exception;
	public int add4(SpringVO pvo, HttpServletRequest request, HttpServletResponse response) throws Exception;
	public List<SpringVO> find_rep(String no) throws Exception;
	public int rep_del(int no) throws Exception;
}