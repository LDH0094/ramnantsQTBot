package lee.ramnants.ramnants_qt_bot.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import lee.ramnants.ramnants_qt_bot.model.QTEntity;
import lee.ramnants.ramnants_qt_bot.model.Verse;
import lee.ramnants.ramnants_qt_bot.repository.QTRepository;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.jupiter.api.Test;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class QTServiceTest {

    @Test
    public void testGetQTOfTheDay() {
        QTService qtService = new QTService(new QTRepository());
        List<QTEntity> qtOfTheDay = qtService.getQTOfTheDay();

        // Check if the list is not null and not empty
        assertNotNull(qtOfTheDay);
        assertFalse(qtOfTheDay.isEmpty());


        // logic starts here
        StringBuilder result = new StringBuilder();

        QTEntity firstQtEntity = qtOfTheDay.get(0); // Assuming there's at least one element in the list
        result.append("오늘의 성경말씀 🙏\n").append(firstQtEntity.getQTTitle()).append("\n");
        result.append("성경 구절: ").append(firstQtEntity.getQTChapter()).append("\n\n");

        // Loop over each QTEntity in the list and append its contents to the result string
        for (QTEntity qtEntity : qtOfTheDay) {
            result.append(qtEntity.getTitle()).append("\n");
            for (Verse verse : qtEntity.getVerses()) {
                result
                        .append(verse.getNumber())
                        .append("   ")
                        .append(verse.getContent())
                        .append("\n");
            }
            result.append("\n"); // Add a newline between each QTEntity
        }
        result.append("#큐티 \n");
        result.append("큐티 출처: 두란노, 생명의 삶 ");
        System.out.println(result);
        AuthService authService = new AuthService(new RestTemplate());
        String accessToken = authService.login();
//        QTPostService qtPostService = new QTPostService(new RestTemplate(),  new ObjectMapper());
//        qtPostService.postQT(accessToken, result.toString());
        System.out.println("accessToken is: " + accessToken);
        // logic ends here
    }
}
