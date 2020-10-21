package com.task.parser.controller;

import com.task.parser.model.Link;
import com.task.parser.service.HtmlAnalysisService;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.ArrayList;
import java.util.List;

/**
 * Принимает команды управления данными от пользовательского интерфейса.
 */
@Named("linkController")
@ViewScoped
public class LinkController {

    @Inject
    private HtmlAnalysisService analysisService;
    private String link;
    private List<Link> foundedLinks;

    /**
     * Инициализирует поля класса после постройки бина и перед его использованием
     */
    @PostConstruct
    public void init() {
        link = "";
        foundedLinks = new ArrayList<>();
    }

    /**
     * Ищет ссылки, находящиеся на странице представленной в поле <b>link</b>
     * заполняет найденными ссылками список <b>foundedLinks</b>
     */
    public void analysisLink() {
        if (link.isEmpty()) {
            return;
        }
        foundedLinks = analysisService.analysisLink(link);
    }

    /**
     *Очищает поле <b>link</b> и список ссылок <b>foundedLinks</b>
     */
    public void clearFoundedLinks() {
        link = "";
        foundedLinks.clear();
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
}