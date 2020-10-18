package com.task.parser.controller;

import com.task.parser.model.Link;
import com.task.parser.service.HtmlAnalysisService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Named("index")
@ViewScoped
public class IndexController implements Serializable {

    private String link;
    private List<Link> foundedLinks;
    @Autowired
    private HtmlAnalysisService analysisService;

    @PostConstruct
    public void init() {
        link = "";
        foundedLinks = new ArrayList<>();
    }

    public void analysisLink() {
        if (link.isEmpty()) {
            return;
        }
        foundedLinks = analysisService.analysisLink(link);
    }

    public List<Link> getFoundedLinks() {
        return foundedLinks;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        foundedLinks.clear();
        this.link = link;
    }

    public void clearFoundedLinks() {
        link = "";
        foundedLinks.clear();
    }
}