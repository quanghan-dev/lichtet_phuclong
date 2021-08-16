package com.project.lichtet.controllers;

import com.project.lichtet.models.LabelValue;
import com.project.lichtet.models.LichDto;
import com.project.lichtet.models.NhomLichDto;
import com.project.lichtet.services.LabelValueService;
import com.project.lichtet.services.LichService;
import com.project.lichtet.services.NhomLichService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.concurrent.ExecutionException;

@Controller
public class LichController {

    @Autowired
    LichService lichService;

    @Autowired
    NhomLichService nhomLichService;

    @Autowired
    LabelValueService labelValueService;

    @GetMapping("/{nhomLich:^[^.]*$}/{lich:^[^.]*$}")
    public String getAllNhomLich(Model model, @PathVariable String nhomLich, @PathVariable String lich) throws InterruptedException, ExecutionException {

        NhomLichDto nhomLichDto = nhomLichService.getNhomLichById(nhomLich);

        LichDto lichDto = lichService.getLichById(lich);

        List<LichDto> lichDtoList = lichService.getSimilarLich(nhomLich, lich);

        model.addAttribute("lichTuongTu", lichDtoList);

        model.addAttribute("lichDto", lichDto);

        model.addAttribute("nhomLich", nhomLichDto);

        return "detail";
    }

    @GetMapping("/search/{keySearch}")
    @ResponseBody
    public List<LabelValue> tenLichAutocomplete(@PathVariable String keySearch) throws InterruptedException, ExecutionException {
        return labelValueService.getLabelValueForSearch();
    }
}
