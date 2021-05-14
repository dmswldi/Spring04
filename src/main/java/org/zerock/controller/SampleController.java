package org.zerock.controller;

import lombok.extern.log4j.Log4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.zerock.domain.SampleDTO;
import org.zerock.domain.SampleDTOList;
import org.zerock.domain.TodoDTO;

import java.util.ArrayList;
import java.util.Arrays;

@Controller
@RequestMapping("/sample/*")
@Log4j
public class SampleController {

    @RequestMapping(value = "", method = {RequestMethod.GET, RequestMethod.POST})
    public void basic(){
        log.info("basic...!");
    }

    @GetMapping("/basicOnlyGet")
    public void basicGet2(){
        log.info("basic get only get...");
    }

    @GetMapping("/ex01")
    public String ex01(SampleDTO dto){
        log.info("" + dto);
        return "ex01";
    }

    @GetMapping("/ex02")
    public String ex02(@RequestParam("name") String name, @RequestParam("age") int age){
        log.info("name: " + name);
        log.info("age: " + age);
        return "ex02";
    }

    @GetMapping("/ex02List")
    public String ex02List(@RequestParam("ids") ArrayList<String> ids){
        log.info("ids: " + ids);
        return "ex02List";
    }

    @GetMapping("/ex02Array")
    public String ex02Array(@RequestParam("ids") String[] ids){
        log.info("array ids: " + Arrays.toString(ids));
        return "ex02Array";// forwarding to: /views/ex02Array.jsp
    }

    @GetMapping("/ex02Bean")
    public String ex02Bean(SampleDTOList list){
        log.info("list dtos: " + list);
        return "ex02Bean";
    }

    /* 6.3.4 @InitBinder*/
    /*
    @InitBinder
    public void initBinder(WebDataBinder binder){
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        binder.registerCustomEditor(java.util.Date.class, new CustomDateEditor(dateFormat, false));
    }
    */
    @GetMapping("/ex03")
    public String ex03(TodoDTO todo){
        log.info("todo: " + todo);
        return "ex03";
    }

    /* Model */
    /* 기본 자료형은 @ModelAttribute를 선언하여 파라미터를 모델에 담아 jsp로 전달 */
    @GetMapping("/ex04")
    public String ex04(SampleDTO dto, @ModelAttribute("page") int page){
        log.info("dto: " + dto);
        log.info("page: " + page);
        return "/sample/ex04";// forwarding to: /views/sample/ex04.jsp
    }

    @GetMapping("/ex05")// url 경로가 jsp 파일 이름, forwarding to: /views/sample/ex05.jsp
    public void ex05(){
        log.info("/ex05...");
    }

    /* VO나 DTO 리턴 -> JSON data, 몸체가 내용이 됨 */
    @GetMapping("/ex06")
    public @ResponseBody SampleDTO ex06(){
        log.info("/ex06...");

        SampleDTO dto = new SampleDTO();
        dto.setAge(10);
        dto.setName("홍길동");

        return dto;
    }

    @GetMapping("/ex07")
    public ResponseEntity<String> ex07(){
        log.info("/ex07...");

        String msg = "{\"name\": \"홍길동\"}";// {"name": "홍길동"}

        HttpHeaders header = new HttpHeaders();
        header.add("Content-Type", "application/json;charset=UTF-8");

        return new ResponseEntity<>(msg, header, HttpStatus.OK);
    }

    /* file upload */
    @GetMapping("/exUpload")
    public void exUpload(){
        log.info("/exUpload...");
    }

    @PostMapping("/exUploadPost")
    public void exUploadPost(ArrayList<MultipartFile> files){
        files.forEach(file -> {
            log.info("---------");
            log.info("name: " + file.getOriginalFilename());
            log.info("size: " + file.getSize());
        });
    }


     @GetMapping("/test")
    public void test() throws ClassNotFoundException {
        //int[] array = new int[3];

        //array[3] = 5;// 런타임 에러 발생
        Class.forName("org.zerock");
    }
}
