package com.stackoverflow.soq76090017.web;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import com.stackoverflow.soq76090017.ResourceNotFoundException;
import com.stackoverflow.soq76090017.service.MyService;

@WebMvcTest(MyController.class)
class MyWebTest {

    @MockBean
    private MyService myService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void testGetMy_success() throws Exception {
        // given
        UUID uid = UUID.randomUUID();
        when(myService.doSomething(uid)).thenCallRealMethod();

        // when
        mockMvc.perform(get("/my/" + uid).with(user("test")))
                // then
                .andExpectAll(
                        status().isOk(),
                        content().string(uid.toString()));
        verify(myService).doSomething(uid);
    }

    @Test
    void testGetMy_unauthorized() throws Exception {
        // given
        UUID uid = UUID.randomUUID();

        // when
        mockMvc.perform(get("/my/" + uid))
                // then
                .andExpectAll(
                        status().isUnauthorized());
    }

    @Test
    void testGetMy_notFound() throws Exception {
        // given
        UUID uuid = UUID.fromString("ac0a1859-8e59-4ede-8def-043c32aa3208");
        when(myService.doSomething(uuid)).thenThrow(new ResourceNotFoundException("mockba"));

        // when
        mockMvc.perform(get("/my/" + uuid).with(user("tester")))
                // then
                .andExpect(status().isNotFound());

        verify(myService).doSomething(uuid);
    }

}