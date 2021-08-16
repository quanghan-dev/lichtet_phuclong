package com.project.lichtet;

import com.project.lichtet.models.LichDto;
import com.project.lichtet.services.InitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.concurrent.ExecutionException;

@Controller
public class MainController {

    @Autowired
    private InitService initService;

    @RequestMapping("/")
    public String index(Model model) throws InterruptedException, ExecutionException {

        List<LichDto> lichBanChay = initService.getLichByBanChay();

        List<LichDto> lichTrangChu = initService.getLichByTrangChu();

        model.addAttribute("lichBanChayList", lichBanChay);

        model.addAttribute("lichTrangChuList", lichTrangChu);

        return "index";
    }

}
