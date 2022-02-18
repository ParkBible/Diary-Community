package magicpacket;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

// User ó��
@Controller
public class CtrlTest2 {
	private UserDAO userDao = null;

	public UserDAO getUserDao() {
		return userDao;
	}

	public void setUserDao(UserDAO userDao) {
		this.userDao = userDao;
	}

	@ResponseBody
	@RequestMapping("/ping2.pknu")
	public String ping() throws Exception {
		return userDao.toString();
	}
	
	 // ȸ������ �������� �̵�
    @RequestMapping("/join.pknu")
    public ModelAndView join(HttpSession session) throws Exception {
    	ModelAndView mnv = new ModelAndView();
		mnv.setViewName("view_join");
		return mnv;
    }
    
    // ȸ������ ����
    @RequestMapping("/add3.pknu")
	public String add3(@ModelAttribute UserVO vo , HttpSession session, HttpServletRequest request, HttpServletResponse response) throws Exception {
		userDao.add(vo, request, response);
		// ���� �α������� ����
    	if(session.getAttribute("idInfo") != null) {
			session.removeAttribute("idInfo");
		}
    	
		return "redirect:list.pknu";
	}
    
    // �α��� �������� �̵�
    @RequestMapping("/login.pknu")
	public ModelAndView login() throws Exception {
    	ModelAndView mnv = new ModelAndView();
    	mnv.setViewName("view_login");
		return mnv;
	}
    
    // �α��� ����
//    @RequestMapping("/login2.pknu")
//	public ModelAndView login2(@ModelAttribute UserVO vo) throws Exception {
//    	ModelAndView mnv = new ModelAndView();
//    	mnv.setViewName("view_list");
//    	mnv.addObject("idInfo", userDao.findIdPw(vo));
//		return mnv;
//		
//	}
    
    // �α��� ����
    @RequestMapping("/login2.pknu")
	public String login2(@ModelAttribute UserVO vo, HttpSession session, HttpServletRequest request, HttpServletResponse response) throws Exception {
    	// ���� �α������� ����
    	if(session.getAttribute("idInfo") != null) {
			session.removeAttribute("idInfo");
		}
    	session.setAttribute("idInfo", userDao.findIdPw(vo, request, response));
    	return "redirect:list.pknu";
	}
    
 // �α׾ƿ� ����
    @RequestMapping("/logout.pknu")
	public String logout(HttpSession session) throws Exception {
    	// ���� �α������� ����
    	if(session.getAttribute("idInfo") != null) {
			session.removeAttribute("idInfo");
		}
    	return "redirect:list.pknu";
	}
    
    @RequestMapping("/remove.pknu")
    public String captchaRemove(HttpSession session) throws Exception{
        // ���� CAPTCHA �������� ����
    	if(session.getAttribute("CAPTCHAres") != null) {
			session.removeAttribute("CAPTCHAres");
		}
    	return "redirect:test909";
    }
}
