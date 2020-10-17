package com.task.parser.controller;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

@Named("index")
@ViewScoped
public class IndexController {

    private String str = "Hello";

    public String getStr() {
        return str;
    }

    public void setStr(String str) {
        this.str = str;
    }
}