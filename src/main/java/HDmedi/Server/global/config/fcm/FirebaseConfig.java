package HDmedi.Server.global.config.fcm;

import HDmedi.Server.global.exception.badrequest.FirebaseConfigException;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import com.google.gson.JsonParser;
import com.google.gson.stream.JsonReader;
import javax.annotation.PostConstruct;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;


@Slf4j
@Service
public class FirebaseConfig {
    @PostConstruct
    public void initialize() throws IOException {
        ClassPathResource resource = new ClassPathResource("serviceAccountKey.json");

        try (InputStream is = resource.getInputStream()) {
            // InputStream에서 데이터를 읽음
            byte[] jsonData = IOUtils.toByteArray(is);

            // 읽은 데이터로 GoogleCredentials 생성
            GoogleCredentials credentials = GoogleCredentials.fromStream(new ByteArrayInputStream(jsonData));


            // FirebaseOptions 빌더를 사용하여 FirebaseOptions 생성
            FirebaseOptions options = FirebaseOptions.builder()
                    .setCredentials(credentials)
                    .build();

            if (FirebaseApp.getApps().isEmpty()) {
                FirebaseApp.initializeApp(options);
                log.info("FirebaseApp initialization complete");
            }
        }
    }
}



//    @Value("${SERVICE_ACCOUNT_KEY_PATH}")
//    private String serviceAccountKeyPath;
//
//    @PostConstruct
//    public void init() throws IOException {
// //       try {
//            FileInputStream resource = new FileInputStream(serviceAccountKeyPath);
//            FirebaseOptions options = new FirebaseOptions.Builder()
//                    .setCredentials(
//                            GoogleCredentials.fromStream(
//                                    resource
//                            ))
//                    .build();
//            FirebaseApp.initializeApp(options);
//            log.info("firebase application init complete");
//    //    } catch (Exception e) {
//            log.warn("[ 오류 ] firebase application init failed!!");
//   //         throw new FirebaseConfigException();
//     //   }
//    }


