package org.example.externalapitest.service;


import org.example.externalapitest.model.MultiUsers;
import org.example.externalapitest.model.ReqNewUser;
import org.example.externalapitest.model.ResNewUser;
import org.example.externalapitest.model.User;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.List;

@Service
public class UserService {

    private final RestClient restClient;
    public UserService(RestClient.Builder restClientBuilder) {
        this.restClient = restClientBuilder
                .baseUrl("https://reqres.in/api")
                .defaultHeader("x-api-key", "reqres-free-v1")
                .build();

    }

    public List<User> getAllUsers() {
        List <User> allUsers =
                restClient.get()
                        .uri("/users")
                        .retrieve()
                        .body(MultiUsers.class)
                        .data();
        return allUsers;
    }

    public ResNewUser addUser(ReqNewUser reqNewUser) {
        return restClient.post()
                .uri("/users")
                .contentType(MediaType.APPLICATION_JSON)
                .body(reqNewUser)
                .retrieve()
                .body(ResNewUser.class);
    }
}
