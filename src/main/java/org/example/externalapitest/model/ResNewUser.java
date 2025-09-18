package org.example.externalapitest.model;

import lombok.Builder;
import lombok.With;

@With
@Builder
public record ResNewUser(String name , String job , String id , String createdAt) {
}
