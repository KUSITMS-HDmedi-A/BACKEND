package HDmedi.Server.global.config.FCM;

import HDmedi.Server.global.exception.badrequest.FirebaseConfigException;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.util.List;

//@Slf4j
//@Service
//public class FirebaseConfig {
//
//
//    @PostConstruct
//    public void init() {
//        try {
//            FileInputStream serviceAccount = new FileInputStream("./src/main/resources/serviceAccountKey.json");
//            FirebaseOptions options = new FirebaseOptions.Builder()
//                    .setCredentials(
//                            GoogleCredentials.fromStream(
//                                    serviceAccount
//                            ))
//                    .build();
//            FirebaseApp.initializeApp(options);
//            log.info("firebase application init complete");
//        } catch (Exception e) {
//            log.warn("[ 오류 ] firebase application init failed!!");
//            throw new FirebaseConfigException();
//        }
//    }
//}