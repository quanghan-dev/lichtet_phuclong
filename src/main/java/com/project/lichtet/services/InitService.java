package com.project.lichtet.services;

import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.Query;
import com.project.lichtet.models.LichDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

@Service
public class InitService {

    @Autowired
    FirebaseInitialize db;

    //    get Lich By banChay
    public List<LichDto> getLichByBanChay() throws InterruptedException, ExecutionException {

        List<LichDto> lichDtoList = new ArrayList<>();

        CollectionReference nhomLichCollection = db.getFirebase().collection("Lich");

        Query query = nhomLichCollection.whereEqualTo("banChay", true);

        query.get()
                .get()
                .getDocuments()
                .stream()
                .forEach(doc -> lichDtoList.add(doc.toObject(LichDto.class)));

        return lichDtoList;
    }

    //    get Lich By trangChu
    public List<LichDto> getLichByTrangChu() throws InterruptedException, ExecutionException {

        List<LichDto> lichDtoList = new ArrayList<>();

        CollectionReference nhomLichCollection = db.getFirebase().collection("Lich");

        Query query = nhomLichCollection.whereEqualTo("trangChu", true);

        query.get()
                .get()
                .getDocuments()
                .stream()
                .forEach(doc -> lichDtoList.add(doc.toObject(LichDto.class)));

        return lichDtoList;
    }
}
