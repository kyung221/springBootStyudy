package org.example.study.web;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;


import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@RunWith(SpringRunner.class)//스프링 부트 테스트와 JUnit 사이의 연결자 역할
@WebMvcTest(controllers = HelloController.class)
public class HelloControllerTest {

    @Autowired//스프링이 관리하는 bean 주입 받음
    private MockMvc mvc; //web api 테스트시 사용

    @Test
    public void returnHello() throws Exception {
        String hello = "hello";

        mvc.perform(get("/hello")) //mockmvc를 틍해 /hello주소로 GET 요청, 체이닝 지원
                .andExpect(status().isOk())//http status 검증
                .andExpect(content().string(hello));//response body 검증
    }

    @Test
    public void returnHelloDto() throws Exception {
        String name = "hello";
        int amount = 1000;

        mvc.perform(get("/hello/dto")
                .param("name", name)
                .param("amount", String.valueOf(amount)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is(name)))
                .andExpect(jsonPath("$.amount", is(amount)));
    }
}
