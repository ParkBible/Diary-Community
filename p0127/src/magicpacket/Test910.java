package magicpacket;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class Test910 extends HttpServlet{

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=UTF-8");
        PrintWriter out = response.getWriter();
        
        // CAPTCHA ������ �����ߴٸ� ���� �����ϱ� ���� session�� ����.
        HttpSession session = request.getSession();
	
		String key = request.getParameter("key");
		String captcha = request.getParameter("captcha");
		
		System.out.println(key);
		System.out.println(captcha);
		
		
		String clientId = "wfdjoumhc3";//���ø����̼� Ŭ���̾�Ʈ ���̵�";
	     String clientSecret = "OarQwFSEppnhPo9NK1qvF5KIoDWiTRU4Gm4s71vy";//���ø����̼� Ŭ���̾�Ʈ ��ũ����";
	     try {
	         String code = "1"; // Ű �߱޽� 0,  ĸ�� �̹��� �񱳽� 1�� ����
	         String value = captcha; // ����ڰ� �Է��� ĸ�� �̹��� ���ڰ�
	         String apiURL = "https://naveropenapi.apigw.ntruss.com/captcha/v1/nkey?code=" + code +"&key="+ key + "&value="+ value;

	         URL url = new URL(apiURL);
	         HttpURLConnection con = (HttpURLConnection)url.openConnection();
	         con.setRequestMethod("GET");
	         con.setRequestProperty("X-NCP-APIGW-API-KEY-ID", clientId);
	         con.setRequestProperty("X-NCP-APIGW-API-KEY", clientSecret);
	         int responseCode = con.getResponseCode();
	         BufferedReader br;
	         if(responseCode==200) { // ���� ȣ��
	             br = new BufferedReader(new InputStreamReader(con.getInputStream()));
	         } else {  // ���� �߻�
	             br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
	         }
	         String inputLine;
	         StringBuffer sb = new StringBuffer();
	         while ((inputLine = br.readLine()) != null) {
	             sb.append(inputLine);
	         }
	         br.close();
	         System.out.println(sb.toString());
	         if(sb.toString().toCharArray()[10] == 't') {
	        	 out.println("<script>alert('CAPTCHA ������ �����Ͽ����ϴ�.'); location.replace('./test909');</script>");
	        	// ���� CAPTCHA �������� ����
	         	if(session.getAttribute("CAPTCHAres") != null) {
	     			session.removeAttribute("CAPTCHAres");
	     		}
	        	 session.setAttribute("CAPTCHAres", "success");
	         }
	         else {
	        	 response.setContentType("text/html; charset=UTF-8");
	        	 out.println("<script>alert('CAPTCHA ���� �Է��� �߸��Ǿ����ϴ�.'); location.replace('./test909');</script>");
	        	 session.setAttribute("CAPTCHAres", "fail");
	         }
	         
	     } catch (Exception e) {
	         System.out.println(e);
	     }
	}

}
