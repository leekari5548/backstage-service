package com.leekari.service.impl;

import com.leekari.service.ISendMailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

/**
 * @author litao
 * @date 2020/9/24 14:19
 * @description
 */
@Service
public class SendMailService implements ISendMailService {
    @Autowired
    private JavaMailSender mailSender;
    @Value("${spring.mail.username}")
    private String fromUsername;
    @Autowired
    private TemplateEngine templateEngine;


    @Override
    public void sendMail(String[] to, String subject, String[] cc, String title, String code) throws MessagingException {
        MimeMessage mimeMessage =mailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
        mimeMessageHelper.setFrom(fromUsername);
        mimeMessageHelper.setTo(to);
        mimeMessageHelper.setSubject(subject);
        Context ctx = new Context();
        ctx.setVariable("title", title);
        ctx.setVariable("code", code);
        String emailText = templateEngine.process("mail", ctx);
        mimeMessageHelper.setText(emailText, true);
        mailSender.send(mimeMessage);
        System.err.println("发送邮件成功");
    }
}
