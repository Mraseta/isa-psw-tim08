package TIM8.medicalcenter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableScheduling
@EnableTransactionManagement
@EnableJpaRepositories
public class MedicalcenterApplication {

	public static void main(String[] args) {
		SpringApplication.run(MedicalcenterApplication.class, args);
	}

}
