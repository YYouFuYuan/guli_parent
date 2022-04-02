import com.atguigu.msmservice.utils.RandomUtil;
import com.atguigu.msmservice.utils.SendMailUtil;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class Test {


    public static void main(String [] args)  {
        //生成验证码
        String verifyCode = RandomUtil.getFourBitRandom();
        //邮件主题
        String emailTitle = "邮箱验证";
        //邮件内容
        String emailContent = "您正在进行邮箱验证，您的验证码为：" + verifyCode + "，请于5分钟内完成验证！";

        String mailAddress = "1648666524@qq.com";
        //发送邮件
        try {
            SendMailUtil.sendEmail(mailAddress, emailTitle, emailContent);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
