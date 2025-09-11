package com.spring.common.openapi.data.controller;

import com.spring.common.openapi.data.service.DataService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/data")
@RequiredArgsConstructor
public class DataController {
    private final DataService dataService;

    /* 부산도보여행정보 리스트 화면 */
    @GetMapping("/busanWalkingView")
    public String busanWalkingView() {
        return "data/busanWalkingView";
    }

    // http://localhost:8080/data/busanWalkingList
    @ResponseBody
    @GetMapping(value = "/busanWalkingList", produces = "application/json; charset=UTF-8")
    public String busanWalkingList(){
        return dataService.busanWalkingList();
    }
}
