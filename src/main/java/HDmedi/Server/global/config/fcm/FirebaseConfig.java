package HDmedi.Server.global.config.fcm;

import HDmedi.Server.global.exception.badrequest.FirebaseConfigException;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

@Slf4j
@Service
public class FirebaseConfig {

    @PostConstruct
    public void init() throws IOException {
 //       try {
            FileInputStream resource = new FileInputStream("./src/main/resources/serviceAccountKey.json");
            FirebaseOptions options = new FirebaseOptions.Builder()
                    .setCredentials(
                            GoogleCredentials.fromStream(
                                    resource
                            ))
                    .build();
            FirebaseApp.initializeApp(options);
            log.info("firebase application init complete");
    //    } catch (Exception e) {
            log.warn("[ 오류 ] firebase application init failed!!");
   //         throw new FirebaseConfigException();
     //   }
    }
}