package com.epam.bp.autobase.model.entity;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.Pattern;

@Entity
@NamedQueries({
        @NamedQuery(name = "Color.getAll", query = "SELECT c FROM Color c ORDER BY c.id"),
        @NamedQuery(name = "Color.getAllSortedByEn", query = "SELECT c FROM Color c ORDER BY c.value_en"),
        @NamedQuery(name = "Color.getAllSortedByRu", query = "SELECT c FROM Color c ORDER BY c.value_ru")
})
public class Color implements Identifiable<Color> {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @NotEmpty
    @Pattern(regexp = "[A-Z]{1}[a-z]{2,20}", message = "{com.epam.bp.autobase.model.entity.color.value_en.message}")
    @Column(name = "VALUE_EN")
    private String value_en;
    @NotEmpty
    @Pattern(regexp = "[А-Я]{1}[а-я]{2,20}", message = "{com.epam.bp.autobase.model.entity.color.value_ru.message}")
    @Column(name = "VALUE_RU")
    private String value_ru;

    public Integer getId() {
        return id;
    }

    public Color setId(Integer id) {
        this.id = id;
        return this;
    }

    public String getValue_en() {
        return value_en;
    }

    public Color setValue_en(String valueEn) {
        this.value_en = valueEn;
        return this;
    }

    public String getValue_ru() {
        return value_ru;
    }

    public Color setValue_ru(String valueRu) {
        this.value_ru = valueRu;
        return this;
    }

    @Override
    public String toString() {
        return "Color{id: " + id + ", value_ru:" + value_ru + ", value_en:" + value_en + '}';
    }
}