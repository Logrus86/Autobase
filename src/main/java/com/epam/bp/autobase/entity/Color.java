package com.epam.bp.autobase.entity;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.Pattern;

@Entity
@NamedQueries({
        @NamedQuery(name="Color.getAll",  query = "SELECT c FROM Color c ORDER BY c.id"),
        @NamedQuery(name="getAllSortedByEn", query="SELECT c FROM Color c ORDER BY c.value_en"),
        @NamedQuery(name="getAllSortedByRu", query="SELECT c FROM Color c ORDER BY c.value_ru")
})
public class Color implements Identifiable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }

    @NotEmpty
    @Pattern(regexp = "[A-Z]{1}[a-z]{2,20}", message = "Incorrect value_en")
    @Column(name = "VALUE_EN")
    private String value_en;

    @NotEmpty
    @Pattern(regexp = "[А-Я]{1}[а-я]{2,20}", message = "Incorrect value_ru")
    private String value_ru;

    public Color setValue_en(String valueEn) {
        this.value_en = valueEn;
        return this;
    }

    public Color setValue_ru(String valueRu) {
        this.value_ru = valueRu;
        return this;
    }

    public String getValue_en() {
        return value_en;
    }

    public String getValue_ru() {
        return value_ru;
    }

}