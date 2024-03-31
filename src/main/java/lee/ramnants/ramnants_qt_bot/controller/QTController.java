//package lee.ramnants.ramnants_qt_bot.controller;
//
//
//import lee.ramnants.ramnants_qt_bot.model.QTEntity;
//import lee.ramnants.ramnants_qt_bot.service.QTService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.util.List;
//
//@RestController
//@RequestMapping("/api/qt")
//public class QTController {
//
//    private final QTService qtService;
//
//    @Autowired
//    public QTController(QTService qtService) {
//        this.qtService = qtService;
//    }
//
//    @GetMapping("/today")
//    public ResponseEntity<List<QTEntity>> getQTOfTheDay() {
//        List<QTEntity> qtOfTheDay = qtService.getQTOfTheDay();
//        if (qtOfTheDay != null && !qtOfTheDay.isEmpty()) {
//            return new ResponseEntity<>(qtOfTheDay, HttpStatus.OK);
//        } else {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//    }
//
//}
