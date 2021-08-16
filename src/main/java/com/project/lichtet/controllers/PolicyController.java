package com.project.lichtet.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "chinh-sach")
public class PolicyController {

    @RequestMapping(value = "giao-hang")
    public String delivery(Model model) {
        model.addAttribute("chinhSach", "delivery");
        return "policy";
    }

    @RequestMapping(value = "mua-hang-va-thanh-toan")
    public String buying(Model model) {
        model.addAttribute("chinhSach", "purchase_payment");
        return "policy";
    }

    @RequestMapping(value = "doi-tra-hang")
    public String returnGoods(Model model) {
        model.addAttribute("chinhSach", "return_goods");
        return "policy";
    }

    @RequestMapping(value = "bao-mat-thong-tin")
    public String infomationSecurity(Model model) {
        model.addAttribute("chinhSach", "information_security");
        return "policy";
    }
}
