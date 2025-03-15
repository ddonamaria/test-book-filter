package com.ddu.bookfilter.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Author {
    private String name;
    private String firstSurname;
    private String bio;

    public String getFullName() {
        return firstSurname != null ? 
                name + " " + firstSurname : 
                name;
    }
} 