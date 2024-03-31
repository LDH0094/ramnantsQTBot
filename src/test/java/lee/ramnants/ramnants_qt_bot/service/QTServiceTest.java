package lee.ramnants.ramnants_qt_bot.service;

import lee.ramnants.ramnants_qt_bot.model.QTEntity;
import lee.ramnants.ramnants_qt_bot.model.Verse;
import lee.ramnants.ramnants_qt_bot.repository.QTRepository;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class QTServiceTest {

//    @Test
//    public void testGetQTOfTheDay() {
//        QTService qtService = new QTService(new QTRepository());
//        List<QTEntity> qtOfTheDay = qtService.getQTOfTheDay();
//
//        // Check if the list is not null and not empty
//        assertNotNull(qtOfTheDay);
//        assertFalse(qtOfTheDay.isEmpty());
//
//        // Loop over each QTEntity in the list and print its contents
//        for (QTEntity qtEntity : qtOfTheDay) {
//            System.out.println("QT Title: " + qtEntity.getTitle());
//            System.out.println("Verses:");
//            for (Verse verse : qtEntity.getVerses()) {
//                System.out.println("Verse Number: " + verse.getNumber());
//                System.out.println("Verse Content: " + verse.getContent());
//            }
//        }
//    }
}
