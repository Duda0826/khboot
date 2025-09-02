package com.boot.example.controller;

import com.boot.example.domain.ExampleDTO;
import com.boot.example.domain.SampleDTO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/example")
public class ExampleController {

    @GetMapping("/exam01")
    public String example(Model model){
        model.addAttribute("data", "현재 페이지에 출력 데이터");
        return "/example/exam01";
    }

    @GetMapping("/test")
    public String test(Model model){
        model.addAttribute("data1", "뷰에 출력할 데이터");
        model.addAttribute("data2", "뷰에 출력할 <strong>데이터</strong>");

        model.addAttribute("number", 3);
        model.addAttribute("data", "Spring!");
        model.addAttribute("auth", "admin");

        ExampleDTO exampleDTO = new ExampleDTO();
        exampleDTO.setName("홍길동");
        exampleDTO.setAge(38);
        exampleDTO.setEmail("javauser@naver.com");
        model.addAttribute("exampleDTO", exampleDTO);

        List<ExampleDTO> list = new ArrayList<>();
        list.add(new ExampleDTO("김철수", 26, "springuser@naver"));
        list.add(new ExampleDTO("한늘봄", 32, "jsp1234@naver.com"));
        model.addAttribute("list", list);

        Map<Integer, ExampleDTO> map = new HashMap<>();
        map.put(1, new ExampleDTO("김미숙", 30, "javauser@naver.com"));
        map.put(2, new ExampleDTO("조한미", 23, "bootuser@naver.com"));
        model.addAttribute("map", map);

        model.addAttribute("localDateTime", LocalDateTime.now());

        return "example/test";
    }

    @GetMapping("exam02")
    public String exam01(Model model,
                         @RequestParam(required = false, defaultValue = "java") String language,
                         @RequestParam(required = false)String word){
        model.addAttribute("language", language);
        model.addAttribute("word", word);

        return "example/exam02";
    }

    @GetMapping("/form")
    public String form(){
        return "example/form";
    }

    @GetMapping("/exam03")
    public String exam03(@RequestParam String name, @RequestParam int age, Model model){
        //public String exam03(@RequestParam String name, @RequestParam(required = false, defaultValue = "0") int age, Model model) {
        model.addAttribute("name", name);
        model.addAttribute("age", age);
        return "example/exam03";
    }

    @PostMapping("/exam04")
    public String exam04(@ModelAttribute SampleDTO dto, Model model){
        //public String exam04(@ModelAttribute SampleDTO dto) {
        model.addAttribute("dto", dto);
        return "example/exam04";
    }

    @GetMapping("/exam05")
    @ResponseBody
    public String exam05() {
        return "Date 전송";
    }
}