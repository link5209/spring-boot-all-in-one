package com.springboot.allinone.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.springboot.allinone.SpringBootAllInOneApplication;
import com.springboot.allinone.entity.Tutorial;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
//@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = SpringBootAllInOneApplication.class)
@SpringBootTest

public class TutorialControllerTest {
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private WebApplicationContext wac;

    @Before
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();

    }

    @Test
    public void shouldCreateTutorial() throws Exception {
        Tutorial tutorial = new Tutorial(1, "Spring Boot @WebMvcTest", "Description", true);
        mockMvc.perform(post("/api/tutorials")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(tutorial)))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    public void getList() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/tutorials")
                .accept(MediaType.APPLICATION_JSON))
//                .andExpect(jsonPath("$", hasSize(4)))
                .andDo(print()).andExpect(status().isOk());
    }
}