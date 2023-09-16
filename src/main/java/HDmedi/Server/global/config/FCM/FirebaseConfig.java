package HDmedi.Server.global.config.FCM;

import HDmedi.Server.global.exception.badrequest.FirebaseConfigException;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.*;

@Slf4j
@Component
public class FirebaseConfig {

    @Value("${fcm.account}")
    private String account;
    @PostConstruct
    public void init() {
        try {
//            BufferedReader br = new BufferedReader(
//                    new FileReader("src/main/resources/serviceAccountKey.json"));
//            while (true) {
//                String str = br.readLine();
//                if (str == null) break;
//                System.out.println(str);
//            } br.close();

            //ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(account.getBytes());
            FileInputStream serviceAccount = new FileInputStream("src/main/resources/serviceAccountKey.json");
            FirebaseOptions options = new FirebaseOptions.Builder()
                    .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                    .build();
            FirebaseApp.initializeApp(options);
            log.info("firebase application init complete");
        } catch (Exception e) {
            log.warn("[ 오류 ] firebase application init failed!!");
            throw new FirebaseConfigException();
        }
    }
}
