package com.spring.openapi.data.service;

import com.spring.openapi.data.vo.AnimalDaejeonDTO;

public interface DataService {
    public String busanWalkingList();
    public String busanWalkingDetail(String seq);
    public String animalDaejeonList(AnimalDaejeonDTO animalDaejeonDTO);

    public String animalDaejeonItem(AnimalDaejeonDTO animalDaejeonDTO);
}
