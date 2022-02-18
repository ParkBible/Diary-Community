package magicpacket;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;

public class Test909 extends HttpServlet{

	@Override
	public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		String clientId = "wfdjoumhc3";//���ø����̼� Ŭ���̾�Ʈ ���̵�";
        String clientSecret = "OarQwFSEppnhPo9NK1qvF5KIoDWiTRU4Gm4s71vy";//���ø����̼� Ŭ���̾�Ʈ ��ũ����";
        String key = null;
        try {
            String code = "0"; // Ű �߱޽� 0,  ĸ�� �̹��� �񱳽� 1�� ����
            String apiURL = "https://naveropenapi.apigw.ntruss.com/captcha/v1/nkey?code=" + code;
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
            
            JSONObject jo = new JSONObject(sb.toString());
            key = jo.getString("key");
            
        } catch (Exception e) {
            System.out.println(e);
        }
        
        String tempname = null;
        
        try {
            String apiURL = "https://naveropenapi.apigw.ntruss.com/captcha-bin/v1/ncaptcha?key=" + key + "&X-NCP-APIGW-API-KEY-ID" + clientId;
            URL url = new URL(apiURL);
            HttpURLConnection con = (HttpURLConnection)url.openConnection();
            con.setRequestMethod("GET");
            int responseCode = con.getResponseCode();
            BufferedReader br;
            if(responseCode==200) { // ���� ȣ��
                InputStream is = con.getInputStream();
                int read = 0;
                byte[] bytes = new byte[1024];
                // ������ �̸����� ���� ����
                tempname = Long.valueOf(new Date().getTime()).toString();
                File f = new File(Util.upload() + tempname + ".jpg");
                f.createNewFile();
                OutputStream outputStream = new FileOutputStream(f);
                while ((read =is.read(bytes)) != -1) {
                    outputStream.write(bytes, 0, read);
                }
               
                is.close();
            } else {  // ���� �߻�
                br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
                String inputLine;
                StringBuffer sb = new StringBuffer();
                while ((inputLine = br.readLine()) != null) {
                    sb.append(inputLine);
                }
                br.close();
                System.out.println(response.toString());
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        
        request.setAttribute("key", key);
        request.setAttribute("fname", tempname);
        // jsp�ҷ����µ� ���� jsp������ �̹��� ��û�Ҷ� fname�� tempname�� ��Ƽ� image.jsp�� ��û�Ѵ�.
        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/view_join.jsp");

		rd.forward(request, response);
        
	}

}

/*
<servlet>
	<servlet-name>abcd909</servlet-name>
	<servlet-class>apple.Test909</servlet-class>
</servlet>

<servlet-mapping>
	<servlet-name>abcd909</servlet-name>
	<url-pattern>/test909</url-pattern>
</servlet-mapping>
*/
