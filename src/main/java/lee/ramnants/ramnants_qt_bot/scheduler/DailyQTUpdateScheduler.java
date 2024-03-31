package lee.ramnants.ramnants_qt_bot.scheduler;

import lee.ramnants.ramnants_qt_bot.model.QTEntity;
import lee.ramnants.ramnants_qt_bot.model.Verse;
import lee.ramnants.ramnants_qt_bot.service.AuthService;
import lee.ramnants.ramnants_qt_bot.service.QTPostService;
import lee.ramnants.ramnants_qt_bot.service.QTService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Component
public class DailyQTUpdateScheduler {

    private static final Logger log = LoggerFactory.getLogger(DailyQTUpdateScheduler.class);
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
    private final QTService qtService;
    private final AuthService authService;
    private final QTPostService qtPostService;
    @Autowired
    public DailyQTUpdateScheduler(QTService qtService, AuthService authService, QTPostService qtPostService) {
        this.qtService = qtService;
        this.authService = authService;
        this.qtPostService = qtPostService;
    }

    // Scheduled task to run daily at 4 AM
    @Scheduled(cron = "0 0 4 * * ?")
    public void updateQTOfTheDay() {
        // Make the API call to get the QT of the day
       List<QTEntity> qtOfTheDay =  qtService.getQTOfTheDay();
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
       // Use accessToken for bot.
        String accessToken = authService.login();
        qtPostService.postQT(accessToken, result.toString());


        // make a POST request to the website.

        // log here
        log.info("Made QT request on {}", dateFormat.format(new Date()));
    }
}
