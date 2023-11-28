package in.co.vwits.ems;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@ComponentScan(basePackages = "in.co.vwits.ems")
@EnableTransactionManagement//This annotation enables transaction features of spring
public class ApplicationConfiguration {
	
	@Bean//this annotation also helps us to register instance of class as spring bean
	public LocalEntityManagerFactoryBean get() {
		LocalEntityManagerFactoryBean factory = new LocalEntityManagerFactoryBean();
		factory.setPersistenceUnitName("emsapp");
		return factory;
	}
	//Following bean is responsible for managing transactions using spring framework
	@Bean
	public PlatformTransactionManager transactionManager() {
		JpaTransactionManager tx = new JpaTransactionManager();
		tx.setEntityManagerFactory(get().getObject());
		return tx;
	}
}
