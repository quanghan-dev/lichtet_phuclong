package com.project.lichtet.services;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.project.lichtet.models.NhomLichDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.concurrent.ExecutionException;

@Service
public class NhomLichService {

    @Autowired
    FirebaseInitialize db;

//    get nhomLich By ID
    public NhomLichDto getNhomLichById(String ten) throws InterruptedException, ExecutionException {

        CollectionReference nhomLichCollection = db.getFirebase().collection("NhomLich");

        ApiFuture<QuerySnapshot> querySnapshotApiFuture = nhomLichCollection.get();

        DocumentSnapshot documentSnapshot = querySnapshotApiFuture.get()
                .getDocuments()
                .stream()
                .filter(doc -> doc.getId().equals(ten))
                .findFirst()
                .orElse(null);

            return documentSnapshot.toObject(NhomLichDto.class);
    }
}
