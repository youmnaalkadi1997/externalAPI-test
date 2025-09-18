package org.example.externalapitest.model;

import lombok.Builder;
import lombok.With;

import java.util.List;

@Builder
@With
public record MultiCharacters(List<Character> results) {
}
