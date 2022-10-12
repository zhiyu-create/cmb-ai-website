package com.example.cmbaiwebsite.controller;

import com.example.cmbaiwebsite.service.TestService;
import com.example.cmbaiwebsite.vo.Result;
import com.example.cmbaiwebsite.vo.TagVo;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;


@RestController
@Slf4j
public class TestController {
  @Autowired
  private TestService testService;

    @PostMapping(path = "testString")
    public Result testMethod(
            @RequestHeader("testHeader")
            @NonNull
            String testHeader,
            @RequestBody TagVo vo) {
        log.info("id: " + vo.getId());
        return testService.selectList(vo.getId());
    }


    //    public Result testGetMethod() {
    //        log.info("id: "+);
    //        return testService.selectList();
    //    }

}
