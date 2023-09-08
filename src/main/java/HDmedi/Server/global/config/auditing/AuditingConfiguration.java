package HDmedi.Server.global.config.auditing;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@RequiredArgsConstructor
@Configuration
public class AuditingConfiguration {
}
