package com.example.sahibindendev.service;

import com.example.sahibindendev.exception.AccessLogNotFoundException;
import com.example.sahibindendev.model.AccessLogDTO;
import com.example.sahibindendev.model.ClassifiedDTO;
import com.example.sahibindendev.repository.AccessLogRepository;
import kotlin.collections.ArrayDeque;
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

    public List<AccessLogDTO> saveAllAccessLogsComesFromSahibindenApi(){
        return accessLogRepository.saveAllAndFlush(getAllAccessLogsFromApi());
    }

    protected List<AccessLogDTO> getAccessLogsByUserId(Long userId){
        return accessLogRepository.findAllByUsersId(userId)
                .orElseThrow(() -> new AccessLogNotFoundException("AccessLog could not found with id " + userId));
    }


//FIXME: we are loading just 14th pages datas. We must load all datas in all pages.
// But I couldn figure out how to load all given data from sahibindens api to my Db.
// Also I didn't want to use mock this method. Because of it just 14th page comes
    private List<AccessLogDTO> getAllAccessLogsFromApi() {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request=HttpRequest.newBuilder().uri(URI.create("https://api-devakademi.sahibinden.com/v1/api/access-logs?pageNo=15")).build();
        return client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                .thenApply(HttpResponse::body)
                .thenApply(AccessLogService::parse)
                .join();
    }
    private static List<AccessLogDTO> parse(String responseBody) {
        responseBody=responseBody.substring(8);
        List<AccessLogDTO> accessLogDTOList=new ArrayList<>();
        JSONArray classifieds = new JSONArray(responseBody);
        Long id=0L;
        Long usersId=0L;
        String endPoint="";
        for (int i = 0 ; i < classifieds.length(); i++) {
            JSONObject album = classifieds.getJSONObject(i);
            id = album.getLong("id");
            usersId = album.getLong("userId");
            endPoint = album.getString("endpoint");
            accessLogDTOList.add(new AccessLogDTO(id,usersId,endPoint));
        }
        return accessLogDTOList;
    }

    //TO TEST
    public List<AccessLogDTO> getAllAccessLogsFromOurDb(){
        return accessLogRepository.findAll();
    }

}
