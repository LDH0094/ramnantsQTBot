package lee.ramnants.ramnants_qt_bot.repository;

import lee.ramnants.ramnants_qt_bot.model.QTEntity;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.stereotype.Repository;

import java.io.IOException;

@Repository
public class QTRepository {

    // Method to fetch QT of the day from the database
    public String fetchQTOfTheDay() {
        // Implement database query logic here
        // Return the fetched QT entity
        String url = "https://www.duranno.com/qt/view/bible.asp";
        try {
            Document doc = Jsoup.connect(url)
                    .timeout(5000)
                    .userAgent("Mozilla")
                    .ignoreHttpErrors(true)
                    .header("Accept-Encoding", "identity")
                    .get();

            // Select the div with class "bible"

            return doc.body().html();
        } catch (IOException e) {
            e.printStackTrace();
            return "Failed to fetch QT of the day: IOException";
        } catch (Exception e) {
            e.printStackTrace();
            return "Failed to fetch QT of the day: " + e.getMessage();
        }
    }
}
