package cn.itcast.travel.controller;

import cn.itcast.travel.domain.ResultInfo;
import cn.itcast.travel.domain.SmsResult;
import cn.itcast.travel.domain.User;
import cn.itcast.travel.service.UserService;
import cn.itcast.travel.utils.PatternUtil;
import cn.itcast.travel.utils.SMSUtils;
import cn.itcast.travel.utils.ValidateCodeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    /**
     * 查询用户信息
     */
    @GetMapping("findOne")
    @ResponseBody
    public User findOne(HttpServletRequest request) {
        //1.获取用户信息  前端是name 所以用USer接收
        User user = (User) request.getSession().getAttribute("user");
        //2.返回
        return user;
    }

    @GetMapping("exit")
    //@ResponseBody
    public String exit(HttpServletRequest request){
        //1.销毁session
        request.getSession().invalidate();
        //2.重定向到首页
        return "redirect:/login.html";
    }

    /**
     * 发送短信验证码
     * /user/sendSmsCheckCode 需要与前端访问的路径
     */
    @GetMapping("/sendSmsCheckCode")
    @ResponseBody //响应对象 回转json返回给
    public SmsResult sendSmsCheckCode(String telephone){
        //1.做校验手机号 保证数据的完整性
        if (telephone == null || "".equals(telephone)) {
            return new SmsResult(1, "手机号不能为空", null);
        }
        //校验手机号的格式 -- 正则表达式
        if (!PatternUtil.validTelephone(telephone)) {
            return new SmsResult(1, "手机号格式不正确", null);
        }
        //2.生成验证码
        Integer code = ValidateCodeUtils.generateValidateCode(6);
        System.out.println("短信验证码为："+code);
        //3.调用阿里云短信 发送验证码
        // todo 写完之后再打开短信验证
        //SMSUtils.sendMessage("K品优购", "SMS_125118628", telephone, code+"");
        //4.返回数据 发送成功 0
        return new SmsResult(0, "短信发送成功", code);
    }

    /**
     * 用户注册
     *
     */
    @PostMapping("registered")
    @ResponseBody
    public ResultInfo registered(User user,String checkCodeKey,String check){
        //1.校验验证码 比较好的做法 将验证码存到服务器（session Redis ） 注册时拿着数据的
        if (check == null || checkCodeKey == null || !check.equals(checkCodeKey)) {
            return new ResultInfo(false,null,"验证码不正确");
        }
        //2.校验用户名是否重复 需要查询数据库
        User userDb = userService.selectUserByUserName(user.getUsername());
        if (userDb != null) {
            System.out.println("用户名已存在");
            return new ResultInfo(false,null,"亲，用户名已经存在了哦！");
        }
        //todo 3.校验手机号是否重复
        User userPhone = userService.selectUserByTelephone(user.getTelephone());
        if (userPhone != null) {
            System.out.println("手机号已存在");
            return new ResultInfo(false,null,"手机号已存在了哦");
        }
        // todo 4.校验邮箱是否重复
        User userEmail = userService.selectUserByEmail(user.getEmail());
        if (userEmail != null) {
            System.out.println("邮箱已存在");
            return new ResultInfo(false,null,"邮箱已经存在了哦");
        }
        //5.保存用户信息到数据库
        userService.insert(user);
        System.out.println("注册成功");
        //6.返回数据
        return new ResultInfo(true, user, "注册成功");
    }

    /**
     * 登录
     */
    @PostMapping("/login")
    @ResponseBody
    public ResultInfo ligin(String username, String password, String check, HttpServletRequest request){
        //1.参数校验
        if (StringUtils.isEmpty(username) || StringUtils.isEmpty(password) ||StringUtils.isEmpty(check)) {
            return new ResultInfo(false,null,"");
        }
        //2.验证码校验
        String checkCode = (String) request.getSession().getAttribute("CHECK_CODE");
        if (StringUtils.isEmpty(checkCode) || !check.equalsIgnoreCase(checkCode)) {
            return new ResultInfo(false,null,"亲，验证码不正确！");
        }
        //3.根据用户名和密码查询数据库，校验用户名密码是否正确
        User userDb = userService.selectUserByUserNameAndPassword(username, password);
        if (userDb == null) {
            //为空不能登录
            return new ResultInfo(false,null,"亲，用户名或密码不正确！");
        }
        //4.返回数据

        request.getSession().setAttribute("user",userDb);//把userDb放入
        return new ResultInfo(true,userDb,"登录成功");
    }

}
