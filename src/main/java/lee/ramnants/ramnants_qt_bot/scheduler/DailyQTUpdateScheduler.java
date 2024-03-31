package lee.ramnants.ramnants_qt_bot.scheduler;

import lee.ramnants.ramnants_qt_bot.model.QTEntity;
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

    @Autowired
    public DailyQTUpdateScheduler(QTService qtService) {
        this.qtService = qtService;
    }

    // Scheduled task to run daily at 4 AM
    @Scheduled(cron = "0 0 4 * * ?")
    public void updateQTOfTheDay() {
        // Make the API call to get the QT of the day
       List<QTEntity> qtEntityList =  qtService.getQTOfTheDay();

       // Use accessToken for bot.

        // make a POST request to the website.

        // log here
        log.info("Made QT request on {}", dateFormat.format(new Date()));
    }
}
