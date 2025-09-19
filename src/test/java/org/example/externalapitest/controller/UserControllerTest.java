package org.example.externalapitest.controller;

import org.example.externalapitest.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.client.RestClientTest;
import org.springframework.http.MediaType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.client.AutoConfigureMockRestServiceServer;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpMethod;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;


import static org.springframework.test.web.client.match.MockRestRequestMatchers.*;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;


@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureMockRestServiceServer
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private MockRestServiceServer mockRestServiceServer;

    @Test
    void getAllUsers() throws Exception {

        mockRestServiceServer.expect(requestTo("https://reqres.in/api/users"))
                .andExpect(method(HttpMethod.GET))
                .andExpect(header("x-api-key", "reqres-free-v1"))
                .andRespond(withSuccess(
                        """
    {
    "page": 2,
    "per_page": 6,
    "total": 12,
    "total_pages": 2,
    "data": [
        {
            "id": 7,
            "email": "michael.lawson@reqres.in",
            "first_name": "Michael",
            "last_name": "Lawson",
            "avatar": "https://reqres.in/img/faces/7-image.jpg"
        }
    ],
    "support": {
        "url": "https://contentcaddy.io?utm_source=reqres&utm_medium=json&utm_campaign=referral",
        "text": "Tired of writing endless social media content? Let Content Caddy generate it for you."
    }
}
                                """ ,MediaType.APPLICATION_JSON
                ));

        mockMvc.perform(MockMvcRequestBuilders.get("/api/users"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(
                        """
                                   [
                                   {
                                    "id": 7,
                                    "email": "michael.lawson@reqres.in",
                                    "first_name": "Michael",
                                    "last_name": "Lawson",
                                    "avatar": "https://reqres.in/img/faces/7-image.jpg"
                                   }
                                   ]
                                   """
                ));
    }

    @Test
    void addUser() throws Exception {
        mockRestServiceServer.expect(requestTo("https://reqres.in/api/users"))
                .andExpect(method(HttpMethod.POST))
                .andExpect(header("x-api-key", "reqres-free-v1"))
                .andExpect(content().json("""
                        {
                         "name": "morpheus",
                         "job": "leader"
                         }
                """ ))
                .andRespond(withSuccess(
                        """
                                {
                                    "name": "morpheus",
                                    "job": "leader",
                                    "id": "411",
                                    "createdAt": "2025-09-19T17:26:19.000Z"
                                }
                                """ ,MediaType.APPLICATION_JSON
                ));

        mockMvc.perform(MockMvcRequestBuilders.post("/api/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content("""
                   {
                      "name": "morpheus",
                      "job": "leader"
                    }
                    """))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(
                        """
                                   {
                                    "name": "morpheus",
                                    "job": "leader",
                                    "id": "411",
                                    "createdAt": "2025-09-19T17:26:19.000Z"
                                    }
                                   """
                ));

    }
}