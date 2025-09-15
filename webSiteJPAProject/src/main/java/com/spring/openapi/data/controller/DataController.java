package com.spring.openapi.data.controller;

import com.spring.openapi.data.service.DataService;
import com.spring.openapi.data.vo.AnimalDaejeonDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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

    /* 부산도보여행정보 상세 화면 */
    @GetMapping("/busanWalkingDetailView/{seq}")
    public String busanWalkingDetailView(@PathVariable String seq, Model model){
        model.addAttribute("seq", seq);
        return "data/busanWalkingDetailView";
    }

    //http://localhostL8080:/data/busanWalkingDetail/283
    @ResponseBody
    @GetMapping(value = "/busanWalkingDetail/{seq}", produces = MediaType.APPLICATION_JSON_VALUE)
    public String busanWalkingDetail(@PathVariable String seq) throws Exception {
        return dataService.busanWalkingDetail(seq);
    }

    /* 대전 유기동물공고 리스트 화면 */
    @GetMapping("/animalDaejeonView")
    public String animalDaejeonView(AnimalDaejeonDTO animalDaejeonDTO){
        return "data/animalDaejeonView";
    }

    @ResponseBody
    @PostMapping(value="/animalDaejeonList", consumes = "application/json", produces = "application/xml; charset=UTF-8")
    public String animalDaejeonList(@RequestBody AnimalDaejeonDTO animalDaejeonDTO) {
        return dataService.animalDaejeonList(animalDaejeonDTO);
    }

    /* 유기동물공고현황 상세조회*/
    @GetMapping(value="/animalDaejeonItemView/{animalSeq}")
    public String animalDaejeonItemView(@PathVariable String animalSeq, AnimalDaejeonDTO animalDaejeonDTO, Model model){
        model.addAttribute("animalSeq", animalSeq);
        return "data/animalDaejeonItemView";
    }

    @ResponseBody
    @PostMapping(value="/animalDaejeonItem", consumes = "application/json", produces = "application/xml; charset=UTF-8")
    public String animalDaejeonItem(@RequestBody AnimalDaejeonDTO animalDaejeonDTO) throws Exception{
        return dataService.animalDaejeonItem(animalDaejeonDTO);
    }
}
