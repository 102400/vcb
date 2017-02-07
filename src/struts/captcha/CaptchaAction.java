package struts.captcha;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class CaptchaAction extends ActionSupport {
	
	private static final int WIDTH = 100;
	private static final int HEIGHT = 25;
	
    private ByteArrayInputStream inputStream;

    public ByteArrayInputStream getInputStream()
    {
        return inputStream;
    }

    public void setInputStream(ByteArrayInputStream inputStream)
    {
        this.inputStream = inputStream;
    }

	
	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		String d = "qwertyuiopasdfghjklzxcvbnm0123456789QWERTYUIOPASDFGHJKLZXCVBNM";
		Random r = new Random();
		String s = "";
		for(int i=0;i<4;i++) {
			s = s + d.charAt(r.nextInt(d.length()));
		}
//		String s = "abcdefg";

		BufferedImage bi = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
		Graphics2D g = (Graphics2D)bi.getGraphics();
		
		g.drawString(s, 10, 10);
		
		HttpServletRequest request = ServletActionContext.getRequest();
//		HttpServletResponse response = ServletActionContext.getResponse();
		
		HttpSession session = request.getSession();
		session.setMaxInactiveInterval(60*5);
		session.setAttribute("CAPTCHA", s);
		
//		Cookie cookie = new Cookie("JSESSIONID",session.getId());
//		cookie.setMaxAge(60*2);
//		
//		response.addCookie(cookie);
		
//		ImageIO.write(bi, "png", response.getOutputStream());
		
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        ImageIO.write(bi, "png",outputStream);

        ByteArrayInputStream input = new ByteArrayInputStream(outputStream.toByteArray());

        this.setInputStream(input);
		
		return SUCCESS;
	}
	

}
