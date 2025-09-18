package org.example.externalapitest.model;

import lombok.Builder;
import lombok.With;

@With
@Builder
public record ReqNewUser(String name , String job) {
}
