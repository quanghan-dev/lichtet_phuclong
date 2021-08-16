package com.project.lichtet.services;

import com.project.lichtet.models.Cart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public class MailService {

    @Autowired
    JavaMailSender emailSender;

    public void sendEmail(HashMap<String, Cart> cartItems, String hoVaTen, String email, String diaChi, int phone, String ghiChu) {

        String mailContent = "";

        for(Cart cart : cartItems.values()) {
            mailContent = mailContent.concat(cart.getLich().getTen() + " (Số lượng: " + cart.getQuantity() + ")\n\n");
        }

        mailContent = mailContent.concat("Khách Hàng\n")
                .concat("Họ và tên: " + hoVaTen + "\n")
                .concat("Số điện thoại: " + phone + "\n")
                .concat("Email: " + email + "\n")
                .concat("Địa Chỉ: " + diaChi + "\n")
                .concat("Ghi Chú: " + ghiChu + "\n");

        SimpleMailMessage message = new SimpleMailMessage();

        message.setFrom("phuclong.lichtet@gmail.com");

        message.setTo("mothanhuy@gmail.com");

        message.setSubject("Đặt Hàng");

        message.setText(mailContent);

        emailSender.send(message);
    }

}
