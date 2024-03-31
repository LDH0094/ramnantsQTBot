package lee.ramnants.ramnants_qt_bot.service;

import lee.ramnants.ramnants_qt_bot.model.QTEntity;
import lee.ramnants.ramnants_qt_bot.model.Verse;
import lee.ramnants.ramnants_qt_bot.repository.QTRepository;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
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
            Element bibleDiv = doc.selectFirst("div.bible");
            if (bibleDiv != null) {
                for (Element p : bibleDiv.select("p")) {
                    QTEntity qtEntity = new QTEntity();
                    qtEntity.setTitle(p.text());

                    List<Verse> verses = new ArrayList<>();
                    for (Element table : bibleDiv.select("table")) {
                        Element th = table.selectFirst("th");
                        Element td = table.selectFirst("td");
                        if (th != null && td != null) {
                            Verse verse = new Verse();
                            verse.setNumber(parseInt(th.text()));
                            verse.setContent(td.text());
                            verses.add(verse);
                        }
                    }
                    qtEntity.setVerses(verses);
                    qtEntities.add(qtEntity);
                }
            } else {
                // If no QT content found, you may choose to throw an exception or return an empty list
                // throw new IllegalStateException("Failed to find QT content");
                // Or return an empty list
                 return Collections.emptyList();
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
