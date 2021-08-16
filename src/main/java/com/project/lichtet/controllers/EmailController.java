package com.project.lichtet.controllers;

import com.project.lichtet.models.Cart;
import com.project.lichtet.services.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.HashMap;

@Controller
public class EmailController {

    @Autowired
    MailService mailService;

    @RequestMapping("/dat-hang")
    public String sendMail(HttpSession session, String hoVaTen, String email, String diaChi, int soDienThoai, String ghiChu) {

        mailService.sendEmail((HashMap<String, Cart>) session.getAttribute("cart"), hoVaTen, email, diaChi, soDienThoai, ghiChu);

        session.removeAttribute("cart");

        session.removeAttribute("total");

        session.removeAttribute("cartNum");

        session.setAttribute("success", true);

        return "redirect:/dat-hang/thanh-cong";
    }

    @RequestMapping("/dat-hang/thanh-cong")
    public String returnCart() {
        return "cart";
    }
}
