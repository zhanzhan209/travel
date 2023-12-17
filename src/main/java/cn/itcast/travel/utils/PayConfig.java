package cn.itcast.travel.utils;

import com.github.wxpay.sdk.WXPayConfig;

import java.io.InputStream;

public class PayConfig implements WXPayConfig {

    // //企业方公众号Id
    // public static String appId = "wx8397f8696b538317";
    // //财付通平台的商户账号
    // public static String partner = "1473426802";
    // //财付通平台的商户密钥
    // public static String partnerKey = "T6m9iK73b0kn9g5v426MKfHQH7X8rKwb";

    //-------   上面的过期了，采用下面配置    -----------

    //企业方公众号Id
    public static String appId = "wxb709cf6e6a7d9d2a";
    public static String appSecret = "d9a9ff00a633cd7353a8925119063b01";
    //财付通平台的商户账号
    public static String partner = "1473426802";
    //财付通平台的商户密钥
    public static String partnerKey = "T6m9iK73b0kn9g5v426MKfHQH7X8rKwb";
    //回调URL(必须提供,但是实际上扫码支付用不到)
    public static String notifyurl = "http://90d5ff0.nat123.fun/pay/callback";


    @Override
    public String getAppID() {
        return appId;
    }

    @Override
    public String getMchID() {
        return partner;
    }

    @Override
    public String getKey() {
        return partnerKey;
    }

    @Override
    public InputStream getCertStream() {
        return null;
    }

    @Override
    public int getHttpConnectTimeoutMs() {
        return 8000;
    }

    @Override
    public int getHttpReadTimeoutMs() {
        return 10000;
    }
}
