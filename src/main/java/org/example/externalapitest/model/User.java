package org.example.externalapitest.model;

import lombok.Builder;
import lombok.With;

@With
@Builder
public record User(int id , String email, String firstName, String lastName , String avatar)  {

}
