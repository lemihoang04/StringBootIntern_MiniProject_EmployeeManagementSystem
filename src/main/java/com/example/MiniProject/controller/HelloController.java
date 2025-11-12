package com.example.MiniProject.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.RestController;
import com.example.MiniProject.service.UtilityService;

@RestController
public class HelloController {

    private final UtilityService utilityService;
    private ModelMapper modelMapper = new ModelMapper();

    public HelloController(UtilityService utilityService) {
        this.utilityService = utilityService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/hello")
    public String hello() {
        return "Project chạy thành công";
    }

    @GetMapping("/employee-code")
    public String generateCode() {
        String code = utilityService.generateEmployeeCode("EMP-");
        String formattedName = utilityService.formatString("nguyen van a");
        
        return "Mã nhân viên: " + code + 
               "<br/>Tên đã định dạng: " + formattedName;
    }
    
    @GetMapping("/check-beans")
    public String checkBeans() {
        String code = utilityService.generateEmployeeCode("TEST-");
        String mapperStatus = (modelMapper != null) ? "ModelMapper đã được inject thành công." : "LỖI: ModelMapper chưa được inject.";
        
        return "UtilityService hoạt động (Mã: " + code + 
               ")<br/>" + mapperStatus;
    }
}