package com.project.lichtet.services;

import com.project.lichtet.models.LabelValue;
import com.project.lichtet.models.LichDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;


@Service
public class LabelValueService {

    @Autowired
    LichService lichService;

    public List<LabelValue> getLabelValueForSearch() throws InterruptedException, ExecutionException {

        List<LabelValue> labelValueList = new ArrayList<>();

        List<LichDto> lichDtoList = lichService.getAllLich();

        for(LichDto lich : lichDtoList) {
            LabelValue labelValue = new LabelValue();
            labelValue.setLabel(lich.getTen());
            labelValue.setValue("/" + lich.getNhomLich() + "/" + lich.getId());
            labelValueList.add(labelValue);
        }

        return labelValueList;
    }
}
