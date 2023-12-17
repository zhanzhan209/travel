package cn.itcast.travel.controller;

import cn.itcast.travel.utils.ImageUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.IOException;

@Controller
public class ImageController {

    @GetMapping("imageCheckCode")
    public void imageCheckCode(HttpServletResponse response, HttpServletRequest request) throws IOException {
        //1.设置response对象
        //服务器通知浏览器不要缓存
        response.setHeader("pragma", "no-cache");
        response.setHeader("cache-control", "no-cache");
        response.setHeader("expires", "0");

        //2.生成图片验证码
        String checkCode = ImageUtil.getCheckCode();
        //将验证码放入session域中
        request.getSession().setAttribute("CHECK_CODE",checkCode);
        //3.将验证码转换为图片
        BufferedImage bufferedImage = ImageUtil.createImage(80, 35, BufferedImage.TYPE_3BYTE_BGR, checkCode);
        //4.通过io流刷回浏览器
        ImageIO.write(bufferedImage, "png", response.getOutputStream());
    }
}
