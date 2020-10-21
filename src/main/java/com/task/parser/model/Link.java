package com.task.parser.model;

import javax.enterprise.inject.Model;

/**
 * Представляет модель ссылки для отображения.
 * Хранит идентификатор ссылки, имя html страницы представленной по ссылке
 * и непосредственно адресс ссылки.
 */
@Model
public class Link {
    private int id;
    private String name;
    private String address;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "Link{" +
                "name='" + name + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}