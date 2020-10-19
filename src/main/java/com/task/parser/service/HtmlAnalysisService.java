package com.task.parser.service;

import com.task.parser.model.Link;
import org.apache.commons.validator.routines.UrlValidator;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import javax.annotation.PostConstruct;
import javax.inject.Named;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Named
public class HtmlAnalysisService {

    private UrlValidator validator;

    @PostConstruct
    public void init() {
        validator = new UrlValidator();
    }

    public List<Link> analysisLink(String link) {
        List<Link> foundedLinks = new ArrayList<>();
        if (!validator.isValid(link)) {
            System.out.println("incorrect url");
            return null;
        }
        try {
            Document doc = Jsoup.connect(link).get();
            Element body = doc.body();
            Elements links = body.getElementsByTag("a");
            int count = 0;
            for (Element el : links) {
                String mayBeLink = el.attr("href");
                if (validator.isValid(mayBeLink)) {
                    Document document = Jsoup.connect(mayBeLink).get();
                    String title = document.title();
                    Link foundedLink = new Link();
                    foundedLink.setId(++count);
                    foundedLink.setName(title);
                    foundedLink.setAddress(mayBeLink);
                    foundedLinks.add(foundedLink);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return foundedLinks;
    }
}