package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class FileController {

    @ResponseBody
    @RequestMapping("/uploadFile")
    public String testUpload(MultipartFile file){
        System.out.println("originalFilename :"+
                file.getOriginalFilename());
        return "success";
    }


}
