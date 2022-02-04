package com.example.sahibindendev.service;

import com.example.sahibindendev.model.ClassifiedDTO;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Service
public class ClassifiedService {


    private static ClassifiedDTO parse(String responseBody) {
        responseBody = responseBody.substring(8);
        JSONObject album = new JSONObject(responseBody);
        String category = "";
        double price = 0;
        category = album.getString("category");
        price = album.getDouble("price");
        return new ClassifiedDTO(price, category);
    }

    protected ClassifiedDTO getClassifiedById(String id) {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create("https://api-devakademi.sahibinden.com/v1/api/classifieds/" + id)).build();
        return client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                .thenApply(HttpResponse::body)
                .thenApply(ClassifiedService::parse)
                .join();
    }

}
