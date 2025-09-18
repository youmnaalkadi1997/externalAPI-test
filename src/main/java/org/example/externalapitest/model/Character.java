package org.example.externalapitest.model;

import lombok.Builder;
import lombok.With;

@Builder
@With
public record Character(int id , String name , String status , String species) {

}
