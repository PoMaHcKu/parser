package com.task.parser.service;

import com.task.parser.model.Link;
import org.apache.commons.validator.routines.UrlValidator;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
@SessionScope
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
                if (validator.isValid(el.attr("href"))) {
                    Document document = Jsoup.connect(el.attr("href")).get();
                    String title = document.title();
                    Link foundedLink = new Link();
                    foundedLink.setId(++count);
                    foundedLink.setName(title);
                    foundedLink.setAddress(el.attr("href"));
                    foundedLinks.add(foundedLink);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return foundedLinks;
    }
}