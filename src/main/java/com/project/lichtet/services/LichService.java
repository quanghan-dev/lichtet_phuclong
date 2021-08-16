package com.project.lichtet.services;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Query;
import com.google.cloud.firestore.QuerySnapshot;
import com.project.lichtet.models.LichDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

@Service
public class LichService {

    @Autowired
    FirebaseInitialize db;

    public List<LichDto> getAllLich() throws InterruptedException, ExecutionException {

        List<LichDto> lichDtoList = new ArrayList<>();

        CollectionReference lichCollection = db.getFirebase().collection("Lich");

        ApiFuture<QuerySnapshot> querySnapshotApiFuture = lichCollection.get();

        querySnapshotApiFuture.get()
                .getDocuments()
                .stream()
                .forEach(doc -> lichDtoList.add(doc.toObject(LichDto.class)));

        return lichDtoList;
    }

    //    get Lich By nhomLich
    public List<LichDto> getLichByNhomLich(String nhomLich) throws InterruptedException, ExecutionException {

        List<LichDto> lichDtoList = new ArrayList<>();

        CollectionReference lichCollection = db.getFirebase().collection("Lich");

        Query query = lichCollection.whereEqualTo("nhomLich", nhomLich);

        query.get()
                .get()
                .getDocuments()
                .stream()
                .forEach(doc -> lichDtoList.add(doc.toObject(LichDto.class)));

        return lichDtoList;
    }

    public LichDto getLichById(String id) throws InterruptedException, ExecutionException {

        CollectionReference lichCollection = db.getFirebase().collection("Lich");

        ApiFuture<QuerySnapshot> querySnapshotApiFuture = lichCollection.get();

        DocumentSnapshot documentSnapshot = querySnapshotApiFuture.get()
                .getDocuments()
                .stream()
                .filter(doc -> doc.getId().equals(id))
                .findFirst()
                .orElse(null);

        return documentSnapshot.toObject(LichDto.class);
    }

    public List<LichDto> getSimilarLich(String nhomLich, String id) throws InterruptedException, ExecutionException {
        List<LichDto> lichDtoList = new ArrayList<>();

        CollectionReference lichCollection = db.getFirebase().collection("Lich");

        Query query = lichCollection.whereEqualTo("nhomLich", nhomLich);

        query.get()
                .get()
                .getDocuments()
                .stream()
                .filter(doc -> !doc.getId().equals(id))
                .forEach(doc -> lichDtoList.add(doc.toObject(LichDto.class)));

        return lichDtoList;
    }

    public List<String> getAllTenLich() throws InterruptedException, ExecutionException {
        List<String> allTenLich = new ArrayList<>();

        CollectionReference lichCollection = db.getFirebase().collection("Lich");

        ApiFuture<QuerySnapshot> querySnapshotApiFuture = lichCollection.get();

        querySnapshotApiFuture.get()
                .getDocuments()
                .stream()
                .forEach(doc -> allTenLich.add(doc.toObject(LichDto.class).getTen()));

        return allTenLich;
    }

    public LichDto getLichByTen(String ten) throws InterruptedException, ExecutionException {

        CollectionReference lichCollection = db.getFirebase().collection("Lich");

        ApiFuture<QuerySnapshot> querySnapshotApiFuture = lichCollection.get();

        DocumentSnapshot documentSnapshot = querySnapshotApiFuture.get()
                .getDocuments()
                .stream()
                .filter(doc -> doc.toObject(LichDto.class).getTen().equals(ten))
                .findFirst()
                .orElse(null);

        return documentSnapshot.toObject(LichDto.class);
    }
}
