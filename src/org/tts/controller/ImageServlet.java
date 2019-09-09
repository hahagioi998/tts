package org.tts.controller;




import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

/**
 * @author xujin
 * @package-name org.tts.controller
 * @createtime 2019-08-23 14:49
 */
@WebServlet(name = "ImageServlet",urlPatterns = {"/imageServlet"})
public class ImageServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws  IOException {
        BufferedImage bufferedImage=new BufferedImage(70,30,BufferedImage.TYPE_INT_BGR);
        Graphics graphics=bufferedImage.getGraphics();
        Color color=new Color(220,220,220);
        graphics.setFont(new Font("宋体", Font.BOLD, 20));
        graphics.setColor(color);
        graphics.fillRect(0,0,70,30);
        Random random=new Random();
        StringBuffer stringBuffer=new StringBuffer();
        String string="ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789abcdefghijklmnopqrstuvwxyz";
        for(int i=0;i<4;i++){
            char c=string.charAt(random.nextInt(string.length()));
            stringBuffer.append(c);
            graphics.setColor(new Color(random.nextInt(256),random.nextInt(256),random.nextInt(256)));
            graphics.drawString(c+"",i*12+12,21);
        }
        req.getSession().setAttribute("checkcode",stringBuffer.toString());
        ImageIO.write(bufferedImage,"png",resp.getOutputStream());
    }
}
