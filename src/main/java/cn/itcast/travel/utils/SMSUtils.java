package cn.itcast.travel.utils;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsRequest;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.profile.DefaultProfile;

/**
 * 短信发送工具类
 */
public class SMSUtils {
   /**
    * 发送短信
    * @param signName 签名
    * @param templateCode 模板
    * @param phoneNumbers 手机号
    * @param param 参数
    */
   public static void sendMessage(String signName,
                                  String templateCode,
                                  String phoneNumbers,
                                  String param){
      DefaultProfile profile = DefaultProfile.getProfile("cn-hangzhou",
                              "LTAI5tKGwdLfabHWm6uyCbFV",
              "MDW5jdVKrWFRxb8DpC7afeUxVKf7gm");
      IAcsClient client = new DefaultAcsClient(profile);

      SendSmsRequest request = new SendSmsRequest();
      request.setSysRegionId("cn-hangzhou");
      request.setPhoneNumbers(phoneNumbers);
      request.setSignName(signName);
      request.setTemplateCode(templateCode);
      request.setTemplateParam("{\"number\":\""+param+"\"}");
      try {
         SendSmsResponse response = client.getAcsResponse(request);
         String code = response.getCode();
         if(code!=null && "OK".equals(code)){
            System.out.println("短信发送成功");
         }else{
            String message = response.getMessage();
            System.out.println(message);
         }
      }catch (ClientException e) {
         e.printStackTrace();
      }
   }

   public static void main(String[] args) {
      SMSUtils.sendMessage("K品优购", "SMS_125118628", "18736896752", "123456");
   }

}
