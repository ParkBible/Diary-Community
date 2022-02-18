package magicpacket;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface UserDAO {
	public int add(UserVO pvo, HttpServletRequest request, HttpServletResponse response) throws Exception;
	public String findIdPw(UserVO pvo, HttpServletRequest request, HttpServletResponse response) throws Exception;
}