package com.demo.testGraphql.models.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.ZonedDateTime;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookDto implements Serializable {
    private static final long serialVersionUID = 1466956427135915190L;

    private Long id;
    private String name;
    private String description;
    private Float price;
    // datetime
    private ZonedDateTime createAt;
    // date
    private LocalDate createOn;

}
