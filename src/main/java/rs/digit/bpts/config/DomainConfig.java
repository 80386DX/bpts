package rs.digit.bpts.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;


@Configuration
@EntityScan("rs.digit.bpts.domain")
@EnableJpaRepositories("rs.digit.bpts.repos")
@EnableTransactionManagement
public class DomainConfig {
}
