package magicpacket;

import java.net.URLEncoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

// 글 처리
@Controller
public class CtrlTest {
	private SpringDAO springDao = null;

	public SpringDAO getSpringDao() {
		return springDao;
	}

	public void setSpringDao(SpringDAO springDao) {
		this.springDao = springDao;
	}
	
	private UserDAO userDao = null;

	public UserDAO getUserDao() {
		return userDao;
	}

	public void setUserDao(UserDAO userDao) {
		this.userDao = userDao;
	}

	@ResponseBody
	@RequestMapping("/ping.pknu")
	public String ping() throws Exception {
		return springDao.toString();
	}
	
	@RequestMapping("/list.pknu")
	public ModelAndView list(Integer type, String id) throws Exception {
		if(type == null) {
			type = 0;
		}
		ModelAndView mnv = new ModelAndView();
		mnv.setViewName("view_list2");    // 어떤 페이지를 보여줄지
		if(type == 2) {
			mnv.addObject("list", springDao.findAll(type, id));
		}
		else {
			mnv.addObject("list", springDao.findAll(type, ""));    // key, value를 담아 보냄. jsp에서 "list"로 호출.
		}
		return mnv;
	}
	
	// 글쓰기 동작
	@RequestMapping("/add2.pknu")
	public String add2(@ModelAttribute SpringVO vo, HttpServletRequest request, HttpServletResponse response) throws Exception {
		springDao.add(vo, request, response);
		return "redirect:list.pknu";
	}
	
	@RequestMapping("/del2.pknu")
	public String del2(int no) throws Exception {
		springDao.del(no);
		return "redirect:list.pknu";
	}
	
	// 추천
	@RequestMapping("/up.pknu")
	public String up2(String id, int no, String type_num, HttpServletRequest request, HttpServletResponse response) throws Exception {
		springDao.up(id, no, request, response);
		return "redirect:list.pknu?type=" + type_num;
	}
	
	// 댓글쓰기 동작
	@RequestMapping("/add4.pknu")
	public String rep_add(@ModelAttribute SpringVO vo, HttpServletRequest request, HttpServletResponse response) throws Exception {
		springDao.add4(vo, request, response);
		return "redirect:list.pknu";
	}
	
	// 댓글 열람
	@RequestMapping("/reple.pknu")
	public ModelAndView rep_list(String rep_no, String idinfo) throws Exception {
		ModelAndView mnv = new ModelAndView();
		mnv.setViewName("view_reple");    // 어떤 페이지를 보여줄지
		mnv.addObject("rep", springDao.find_rep(rep_no)); // key, value를 담아 보냄. jsp에서 "rep"로 호출.
		mnv.addObject("num", rep_no);
		mnv.addObject("idinfo", idinfo);
		return mnv;
	}
	
	// 댓글 삭제
	// reple_no : 리플 번호, rep_no : 글 번호
	@RequestMapping("/reple_del.pknu")
	public String rep_del2(int reple_no, int rep_no) throws Exception {
		springDao.rep_del(reple_no);
		//return "redirect:reple.pknu";
		return "redirect:reple.pknu?rep_no=" + rep_no;
	}

}
