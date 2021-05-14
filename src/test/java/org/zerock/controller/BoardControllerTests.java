package org.zerock.controller;

import lombok.Setter;
import lombok.extern.log4j.Log4j;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration // for Controller Test
@ContextConfiguration({
        "file:src/main/webapp/WEB-INF/spring/applicationContext.xml",
        "file:src/main/webapp/WEB-INF/spring/appServlet/dispatcher-servlet.xml"})
@Log4j
public class BoardControllerTests {

    @Setter(onMethod_ = @Autowired)
    private WebApplicationContext ctx;

    private MockMvc mockMvc;

    @Before // Test 전 매번 실행
    public void setup(){
        this.mockMvc = MockMvcBuilders.webAppContextSetup(ctx).build();
    }

    @Test
    public void testList() throws Exception {
        log.info(
                mockMvc.perform(MockMvcRequestBuilders.get("/board/list")
                .param("pageNum", "2")
                .param("amount", "10"))
                .andReturn()
                .getModelAndView()
                .getModelMap());
    }

    @Test
    public void testRegister() throws Exception {
        String resultPage = mockMvc.perform(MockMvcRequestBuilders.post("/board/register")
            .param("title", "테스트 제목")
            .param("content", "테스트 내용")
            .param("writer", "테스트 작성자")
        ).andReturn().getModelAndView().getViewName();

        log.info(resultPage);
    }

    @Test
    public void testGet() throws Exception {
        log.info(mockMvc.perform(MockMvcRequestBuilders.get("/board/get")
            .param("bno", "17")) // 값 String으로 담기
            .andReturn()
            .getModelAndView()
            .getModelMap());
    }

    @Test
    public void testModify() throws Exception {
        String resultPage = mockMvc.perform(MockMvcRequestBuilders.post("/board/modify")
                .param("bno", "17")
                .param("title", "수정된 제목")
                .param("content", "수정된 내용")
                .param("writer", "작성자0"))
            .andReturn().getModelAndView().getViewName();

        log.info(resultPage);
    }

    @Test
    public void testRemove() throws Exception {
        String resultPage = mockMvc.perform(MockMvcRequestBuilders.post("/board/remove")
            .param("bno", "15"))
            .andReturn().getModelAndView().getViewName();

        log.info(resultPage);
        /* 여기서 rttr 값 못 받아보나?
        log.info("result?????? -> " + mockMvc.perform(MockMvcRequestBuilders.post("/board/remove")
                .param("bno", "15"))
                .andReturn().getModelAndView().getModelMap().getAttribute("result"));
         */
    }
}
