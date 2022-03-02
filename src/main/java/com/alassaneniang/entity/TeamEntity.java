package com.alassaneniang.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
@Table( name = "team" )
@Entity
public class TeamEntity {

    @Id
    @GeneratedValue
    private Long id;

    private String name;
    private String slogan;

    public TeamEntity ( String name, String slogan ) {
        this.name = name;
        this.slogan = slogan;
    }

    @Override
    public boolean equals ( Object o ) {
        if ( this == o ) return true;
        if ( o == null || getClass() != o.getClass() ) return false;

        TeamEntity that = (TeamEntity) o;

        return Objects.equals( id, that.id );
    }

    @Override
    public int hashCode () {
        return id != null ? id.hashCode() : 0;
    }
}
