package org.localdevelopers.payfinder.controller;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.localdevelopers.payfinder.dto.TestDto;
import org.localdevelopers.payfinder.repository.TempRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@Log4j2
public class TestController {

    private TempRepository tempRepository;

    @GetMapping("/test")
    public void getTest() {
        List<TestDto> testDtos = tempRepository.findAll();
        log.info(testDtos.get(0).toString());
    }


}
