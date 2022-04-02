package com.atguigu.msmservice.service.impl;

import com.atguigu.msmservice.service.MsmService;
import com.atguigu.msmservice.utils.RandomUtil;
import com.atguigu.msmservice.utils.SendMailUtil;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Properties;

import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;
import javax.activation.*;

@Service
public class MsmServiceImpl implements MsmService {
    @Override
    public boolean send(String email, Map<String, Object> param) {
        String code = (String) param.get("code");
        //邮件主题
        String emailTitle = "邮箱验证";
        //邮件内容
        String emailContent = "您正在进行邮箱验证，您的验证码为：" + code + "，请于5分钟内完成验证！";

        //发送邮件
        try {
            SendMailUtil.sendEmail(email, emailTitle, emailContent);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
