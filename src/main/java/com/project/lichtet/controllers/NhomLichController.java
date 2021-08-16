package com.project.lichtet.controllers;

import com.project.lichtet.models.LichDto;
import com.project.lichtet.models.NhomLichDto;
import com.project.lichtet.services.LichService;
import com.project.lichtet.services.NhomLichService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.concurrent.ExecutionException;

@Controller
@RequestMapping("/n")
public class NhomLichController {

    @Autowired
    private NhomLichService nhomLichService;

    @Autowired
    private LichService lichService;

    @GetMapping("/{nhomLich}")
    public String getAllNhomLich(Model model, @PathVariable(name = "nhomLich") String nhomLich) throws InterruptedException, ExecutionException {

        NhomLichDto nhomLichDto = nhomLichService.getNhomLichById(nhomLich);

        List<LichDto> lichDtoList = lichService.getLichByNhomLich(nhomLich);

        model.addAttribute("tenNhomLich", nhomLichDto.getTen());

        if (!lichDtoList.isEmpty())
            model.addAttribute("lichList", lichDtoList);

        return "group";
    }
}
