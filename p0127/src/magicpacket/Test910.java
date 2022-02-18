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
        
        // CAPTCHA 인증을 성공했다면 세션 유지하기 위해 session을 정의.
        HttpSession session = request.getSession();
	
		String key = request.getParameter("key");
		String captcha = request.getParameter("captcha");
		
		System.out.println(key);
		System.out.println(captcha);
		
		
		String clientId = "wfdjoumhc3";//애플리케이션 클라이언트 아이디값";
	     String clientSecret = "OarQwFSEppnhPo9NK1qvF5KIoDWiTRU4Gm4s71vy";//애플리케이션 클라이언트 시크릿값";
	     try {
	         String code = "1"; // 키 발급시 0,  캡차 이미지 비교시 1로 세팅
	         String value = captcha; // 사용자가 입력한 캡차 이미지 글자값
	         String apiURL = "https://naveropenapi.apigw.ntruss.com/captcha/v1/nkey?code=" + code +"&key="+ key + "&value="+ value;

	         URL url = new URL(apiURL);
	         HttpURLConnection con = (HttpURLConnection)url.openConnection();
	         con.setRequestMethod("GET");
	         con.setRequestProperty("X-NCP-APIGW-API-KEY-ID", clientId);
	         con.setRequestProperty("X-NCP-APIGW-API-KEY", clientSecret);
	         int responseCode = con.getResponseCode();
	         BufferedReader br;
	         if(responseCode==200) { // 정상 호출
	             br = new BufferedReader(new InputStreamReader(con.getInputStream()));
	         } else {  // 오류 발생
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
	        	 out.println("<script>alert('CAPTCHA 인증에 성공하였습니다.'); location.replace('./test909');</script>");
	        	// 기존 CAPTCHA 인증정보 삭제
	         	if(session.getAttribute("CAPTCHAres") != null) {
	     			session.removeAttribute("CAPTCHAres");
	     		}
	        	 session.setAttribute("CAPTCHAres", "success");
	         }
	         else {
	        	 response.setContentType("text/html; charset=UTF-8");
	        	 out.println("<script>alert('CAPTCHA 문자 입력이 잘못되었습니다.'); location.replace('./test909');</script>");
	        	 session.setAttribute("CAPTCHAres", "fail");
	         }
	         
	     } catch (Exception e) {
	         System.out.println(e);
	     }
	}

}
