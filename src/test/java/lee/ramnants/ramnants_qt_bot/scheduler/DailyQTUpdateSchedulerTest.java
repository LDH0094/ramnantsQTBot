package lee.ramnants.ramnants_qt_bot.scheduler;

import lee.ramnants.ramnants_qt_bot.model.QTEntity;
import lee.ramnants.ramnants_qt_bot.service.AuthService;
import lee.ramnants.ramnants_qt_bot.service.QTPostService;
import lee.ramnants.ramnants_qt_bot.service.QTService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

public class DailyQTUpdateSchedulerTest {

    @InjectMocks
    private DailyQTUpdateScheduler scheduler;

    @Mock
    private QTService qtService;

    @Mock
    private AuthService authService;

    @Mock
    private QTPostService qtPostService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testUpdateQTOfTheDay() {

        // Call the method to test
        scheduler.updateQTOfTheDay();

        // Add more verifications as needed
    }
}
