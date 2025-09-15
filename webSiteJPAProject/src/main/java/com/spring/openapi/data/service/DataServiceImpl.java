package com.spring.openapi.data.service;

import com.spring.common.openapi.URLConnectUtil;
import com.spring.openapi.data.dto.OpenApiDTO;
import com.spring.openapi.data.vo.AnimalDaejeonDTO;
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

    @Override
    public String animalDaejeonList(AnimalDaejeonDTO animalDaejeonDTO) {
        try {
            StringBuilder site = getAnimalDaejeonData(animalDaejeonDTO);

            OpenApiDTO openApi = new OpenApiDTO(site.toString(), "GET");
            return URLConnectUtil.openAPIData(openApi).toString();
        } catch (Exception e) {
            return "API 호출 중 오류 발생";
        }
    }

    // 요청 url: http://localhost:8080/data/animalDaejeonItem?animalSeq=44348
    @Override
    public String animalDaejeonItem(AnimalDaejeonDTO animalDaejeonDTO) {
        try{
            String baseUrl = "http://apis.data.go.kr/6300000/animalDaejeonService/animalDaejeonItem";
            String params = String.format("?serviceKey=%s&animalSeq=%s", serviceKey, animalDaejeonDTO.getAnimalSeq());
            String site = baseUrl + params;

            OpenApiDTO openApi = new OpenApiDTO(site, "GET");
            return URLConnectUtil.openAPIData(openApi).toString();
        }catch (Exception e){
            return "API 호출 중 오류 발생";
        }
    }

    private StringBuilder getAnimalDaejeonData(AnimalDaejeonDTO animalDaejeonDTO) {
        StringBuilder site = new StringBuilder("http://apis.data.go.kr/6300000/animalDaejeonService/animalDaejeonList");
        site.append("?serviceKey="+serviceKey);
        site.append("&pageNo=1");     // 페이지 번호 - 필수
        site.append("&numOfRows=10");  // 페이지당 레코드수 - 필수
        site.append("&searchCondition=" + animalDaejeonDTO.getSearchCondition());   // 유기동물구분 - 옵션(1:개, 2:고양이, 3:기타동물)
        site.append("&searchCondition3=" + animalDaejeonDTO.getSearchCondition3()); // 입양상태 - 옵션(1:공고중,2:입양가능,3:입양예정,4:입양완료,5:자연사,6:안락사,7:주인반환,8:임시보호,9:입양불가,10:방사,11:주민참여,12:입원중)

        //site.append("&gubun=1");      // 성별구분 - 옵션(1:암, 2:수)
        //site.append("&searchKeyword=" + URLEncoder.encode("동물종구분", "UTF-8")); // 검색키워드 - 옵션(동물종구분,기타사항,등록번호등 검색키워드로 검색)

        return site;
    }
}
