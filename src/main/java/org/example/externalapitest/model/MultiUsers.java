package org.example.externalapitest.model;

import lombok.Builder;
import lombok.With;

import java.util.List;

@With
@Builder
public record MultiUsers(List<User> data) {
}
