package com.example.sahibindendev.service;

import com.example.sahibindendev.exception.AccessLogNotFoundException;
import com.example.sahibindendev.model.AccessLogDTO;
import com.example.sahibindendev.repository.AccessLogRepository;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class AccessLogService {

    private final AccessLogRepository accessLogRepository;

    public AccessLogService(AccessLogRepository accessLogRepository) {
        this.accessLogRepository = accessLogRepository;
    }

    private static List<AccessLogDTO> parse(String responseBody) {
        responseBody = responseBody.substring(8);
        List<AccessLogDTO> accessLogDTOList = new ArrayList<>();
        JSONArray classifieds = new JSONArray(responseBody);
        Long id = 0L;
        Long usersId = 0L;
        String endPoint = "";
        for (int i = 0; i < classifieds.length(); i++) {
            JSONObject album = classifieds.getJSONObject(i);
            id = album.getLong("id");
            usersId = album.getLong("userId");
            endPoint = album.getString("endpoint");
            accessLogDTOList.add(new AccessLogDTO(id, usersId, endPoint));
        }
        return accessLogDTOList;
    }

    public List<AccessLogDTO> saveAllAccessLogsComesFromSahibindenApi() {
        for (int i = 0; i < 100; i++) { //FIXME: Its an Assumption
            accessLogRepository.saveAllAndFlush(getAllAccessLogsFromApi(i + ""));
        }
        return Collections.emptyList(); //must be fixed later
    }

    protected List<AccessLogDTO> getAccessLogsByUserId(Long userId) {
        return accessLogRepository.findAllByUsersId(userId)
                .orElseThrow(() -> new AccessLogNotFoundException("AccessLog could not found with id " + userId));
    }

    //FIXME: we are loading just first 100 pages datas. We must load all datas in all pages.
    private List<AccessLogDTO> getAllAccessLogsFromApi(String pageNumber) {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create("https://api-devakademi.sahibinden.com/v1/api/access-logs?pageNo=" + pageNumber)).build();
        return client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                .thenApply(HttpResponse::body)
                .thenApply(AccessLogService::parse)
                .join();
    }

    //TO TEST
    public List<AccessLogDTO> getAllAccessLogsFromOurDb() {
        return accessLogRepository.findAll();
    }

}
