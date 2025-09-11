package com.spring.common.openapi.data.service;

import com.spring.common.openapi.URLConnectUtil;
import com.spring.common.openapi.data.dto.OpenApiDTO;
import org.springframework.stereotype.Service;

@Service
public class DataServiceImpl implements DataService{
    private final String serviceKey = "36658245c5a83843ac5ac63b3cfa86fddc93a8e881083629a5b7519518b76f92";

    @Override
    public String busanWalkingList() {
        try {
            String baseUrl = "http://apis.data.go.kr/6260000/WalkingService/getWalkingKr";
            String params = String.format("?serviceKey=%s&pageNo=1&numOfRows=12&resultType=json", serviceKey);
            String site = baseUrl + params;

            OpenApiDTO openApi = new OpenApiDTO(site, "GET");
            return URLConnectUtil.openAPIData(openApi).toString();
        }catch (Exception e) {
            return "API 호출 중 오류 발생";
        }
    }

    @Override
    public String busanWalkingDetail(String seq) {
        try{
            String baseUrl = "http://apis.data.go.kr/6260000/WalkingService/getWalkingKr";
            String params = String.format("?serviceKey=%s&pageNo=1&numOfRows=12&resultType=json&UC_SEQ=%s", serviceKey, seq);
            String site = baseUrl + params;

            OpenApiDTO openApi = new OpenApiDTO(site, "GET");
            return URLConnectUtil.openAPIData(openApi).toString();
        }catch (Exception e){
            return "API 호출 중 오류 발생";
        }
    }
}
