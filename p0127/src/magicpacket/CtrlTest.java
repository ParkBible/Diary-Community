package magicpacket;

import java.net.URLEncoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

// �� ó��
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
		mnv.setViewName("view_list2");    // � �������� ��������
		if(type == 2) {
			mnv.addObject("list", springDao.findAll(type, id));
		}
		else {
			mnv.addObject("list", springDao.findAll(type, ""));    // key, value�� ��� ����. jsp���� "list"�� ȣ��.
		}
		return mnv;
	}
	
	// �۾��� ����
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
	
	// ��õ
	@RequestMapping("/up.pknu")
	public String up2(String id, int no, String type_num, HttpServletRequest request, HttpServletResponse response) throws Exception {
		springDao.up(id, no, request, response);
		return "redirect:list.pknu?type=" + type_num;
	}
	
	// ��۾��� ����
	@RequestMapping("/add4.pknu")
	public String rep_add(@ModelAttribute SpringVO vo, HttpServletRequest request, HttpServletResponse response) throws Exception {
		springDao.add4(vo, request, response);
		return "redirect:list.pknu";
	}
	
	// ��� ����
	@RequestMapping("/reple.pknu")
	public ModelAndView rep_list(String rep_no, String idinfo) throws Exception {
		ModelAndView mnv = new ModelAndView();
		mnv.setViewName("view_reple");    // � �������� ��������
		mnv.addObject("rep", springDao.find_rep(rep_no)); // key, value�� ��� ����. jsp���� "rep"�� ȣ��.
		mnv.addObject("num", rep_no);
		mnv.addObject("idinfo", idinfo);
		return mnv;
	}
	
	// ��� ����
	// reple_no : ���� ��ȣ, rep_no : �� ��ȣ
	@RequestMapping("/reple_del.pknu")
	public String rep_del2(int reple_no, int rep_no) throws Exception {
		springDao.rep_del(reple_no);
		//return "redirect:reple.pknu";
		return "redirect:reple.pknu?rep_no=" + rep_no;
	}

}
