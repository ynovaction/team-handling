package com.alassaneniang.service;

import lombok.*;

@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TeamDto {

    private Long id;
    private String name;
    private String slogan;

    public TeamDto ( String name, String slogan ) {
        this.name = name;
        this.slogan = slogan;
    }
}
