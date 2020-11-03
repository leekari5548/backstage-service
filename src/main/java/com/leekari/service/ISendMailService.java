package com.leekari.service;

import javax.mail.MessagingException;

/**
 * @author litao
 * @date 2020/9/24 14:19
 * @description
 */
public interface ISendMailService {

    void sendMail(String[] to, String subject, String[] cc, String title, String code) throws MessagingException;
}
