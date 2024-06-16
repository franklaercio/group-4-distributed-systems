package br.ufrn.schedule;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.ApplicationContext;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Application tests")
@SpringBootTest
class ApplicationTests {

	@MockBean
	private ApplicationContext context;

	@Test
	@DisplayName("Context loads")
	void contextLoads() {
		assertThat(context).isNotNull();
	}

	@Test
	@DisplayName("Execute main method")
	void execute() {
		Application.main(new String[] {});
	}

}
