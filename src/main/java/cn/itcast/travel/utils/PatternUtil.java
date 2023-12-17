package cn.itcast.travel.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PatternUtil {
    private final static Pattern pattern = Pattern.compile("^1[3-9]\\d{9}$");

    /**
     * 校验手机号
     * @param telephone
     * @return
     */
    public static boolean validTelephone(String telephone) {
        // todo 正则表达式
        Matcher matcher = pattern.matcher(telephone);
        return matcher.matches();
    }

    public static void main(String[] args) {
        System.out.println(PatternUtil.validTelephone("12736896752"));
    }
}
