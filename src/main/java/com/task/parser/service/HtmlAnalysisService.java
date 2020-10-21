package com.task.parser.service;

import com.task.parser.model.Link;
import org.apache.commons.validator.routines.UrlValidator;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import javax.inject.Named;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Сервис парсинга html страницы.
 */
@Named
public class HtmlAnalysisService {

    /**
     * Принимает строку с url аддресом, переходит по адресу, ищет все html элементы с тэгом <b>a</b>,
     * считывает строку из атрибута <b>href</b> у каждого полученного элемента.
     * Далее валидирует элемент на предмет соответсвия ссылке и
     * в случаеу успешного прохождения валидации создает сущность <b>Link</b> с заголовком элемента,
     * его адресом и помещает элемент в список ссылок.
     * В случае непрохождения проверки переходит к следующему элементу.
     * При возникновении ошибок, выводит stack trace ошибки в стандартный поток вывода.
     * @param link анализируемый url
     * @return foundedLinks список найденных ссылок
     */
    public List<Link> analysisLink(String link) {
        UrlValidator validator = new UrlValidator();
        List<Link> foundedLinks = new ArrayList<>();
        try {
            Document doc = Jsoup.connect(link).maxBodySize(0).get();
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