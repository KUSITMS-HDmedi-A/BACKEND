package HDmedi.Server.global.config.fcm;

import HDmedi.Server.global.exception.badrequest.FirebaseConfigException;
import HDmedi.Server.global.exception.badrequest.SDKNotFoundException;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.nio.file.Path;

@Slf4j
@Service
public class FirebaseConfig {

    @PostConstruct
    public void init() {
        try {
            String path = "./src/main/resources/serviceAccountKey.json";

            FileInputStream resource = new FileInputStream(path);
            BufferedReader br = new BufferedReader(new InputStreamReader(resource));
            FirebaseOptions options = new FirebaseOptions.Builder()
                    .setCredentials(
                            GoogleCredentials.fromStream(
                                    resource
                            ))
                    .build();
            FirebaseApp.initializeApp(options);
            log.info("firebase application init complete");
        } catch (FileNotFoundException e) {
            log.warn("[ 오류 ] firebase SDK json file is not found!! check your path....");
            throw new SDKNotFoundException();
        } catch (Exception e) {
            log.warn("[ 오류 ] firebase application init failed!!");
            throw new FirebaseConfigException();
        }
    }
}