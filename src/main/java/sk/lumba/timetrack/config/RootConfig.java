package sk.lumba.timetrack.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.Profile;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import sk.lumba.timetrack.dao.LegacyAppRecordDAOImpl;
import sk.lumba.timetrack.dao.LocalRecordDAOImpl;
import sk.lumba.timetrack.dao.RecordDAO;

@Configuration
@ComponentScan(basePackages={"sk.lumba.timetrack"},
	excludeFilters={
			@Filter(type=FilterType.ANNOTATION, value=EnableWebMvc.class)
})
public class RootConfig {

	@Bean
	@Profile("default")
	RecordDAO legacyAppRecordDAO() {
		return new LegacyAppRecordDAOImpl(System.getenv("TIMETRACK_URL"));
	}
	
	@Bean
	@Profile("local")
	RecordDAO localRecordDAO() {
		return new LocalRecordDAOImpl();
	}
}
