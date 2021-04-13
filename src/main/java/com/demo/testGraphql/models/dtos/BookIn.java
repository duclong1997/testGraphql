package com.demo.testGraphql.models.dtos;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
public class BookIn implements Serializable {
    private Long id;
    private String name;
    private String description;
    private Float price;
}
