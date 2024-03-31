package lee.ramnants.ramnants_qt_bot.service;

import lee.ramnants.ramnants_qt_bot.model.QTEntity;
import lee.ramnants.ramnants_qt_bot.model.Verse;
import lee.ramnants.ramnants_qt_bot.repository.QTRepository;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static java.lang.Integer.parseInt;

@Service
public class QTService {
    private final QTRepository qtRepository;

    @Autowired
    public QTService(QTRepository qtRepository) {
        this.qtRepository = qtRepository;
    }

    public List<QTEntity> getQTOfTheDay() {
        List<QTEntity> qtEntities = new ArrayList<>();
        try {
            String qtHtml = qtRepository.fetchQTOfTheDay();
            Document doc = Jsoup.parse(qtHtml);

            // Select the div element with class "font-size" to get QT header and chapter
            Element headerDivElement = doc.selectFirst("div.font-size");
            if (headerDivElement != null) {
                String header = headerDivElement.selectFirst("span").text();
                String chapter = headerDivElement.selectFirst("em").text();

                // Select all <div class="bible"> elements
                Elements bibleDivs = doc.select("div.bible");
                for (Element bibleDiv : bibleDivs) {
                    // Get the title of each section within the <div class="bible">
                    Elements sectionTitles = bibleDiv.select("p.title");
                    for (Element sectionTitle : sectionTitles) {
                        // Create a new QTEntity for each section title
                        QTEntity qtEntity = new QTEntity();
                        qtEntity.setQTTitle(header);
                        qtEntity.setQTChapter(chapter);
                        qtEntity.setTitle(sectionTitle.text());

                        // Loop over all <table> elements after the section title
                        Element nextSibling = sectionTitle.nextElementSibling();
                        List<Verse> verses = new ArrayList<>();
                        while (nextSibling != null && nextSibling.tagName().equals("table")) {
                            Element th = nextSibling.selectFirst("th");
                            Element td = nextSibling.selectFirst("td");
                            if (th != null && td != null) {
                                Verse verse = new Verse();
                                verse.setNumber(Integer.parseInt(th.text()));
                                verse.setContent(td.text());
                                verses.add(verse);
                            }
                            nextSibling = nextSibling.nextElementSibling();
                        }
                        qtEntity.setVerses(verses);
                        qtEntities.add(qtEntity);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            // Log the error or handle it accordingly
            // You may choose to throw an exception or return an empty list
            // throw new RuntimeException("Failed to fetch QT of the day", e);
            // Or return an empty list
            // return Collections.emptyList();
        }
        return qtEntities;
    }

}
