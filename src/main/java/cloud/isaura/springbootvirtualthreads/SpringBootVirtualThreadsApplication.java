package cloud.isaura.springbootvirtualthreads;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition
public class SpringBootVirtualThreadsApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootVirtualThreadsApplication.class, args);
	}

}
