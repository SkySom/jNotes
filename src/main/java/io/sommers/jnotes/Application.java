package io.sommers.jnotes;

import io.sommers.jnotes.filters.SecurityFilter;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.metadata.ClassMetadata;
import org.hibernate.service.ServiceRegistry;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.embedded.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

import java.util.ArrayList;
import java.util.Map;

/**
 * Created by Skylar on 9/12/2014.
 */

@ComponentScan
@EnableAutoConfiguration
public class Application {
	private static final SessionFactory ourSessionFactory;
	private static final ServiceRegistry serviceRegistry;

	public static void main(String[] args) {
		final Session session = getSession();
		try {
			final Map metadataMap = session.getSessionFactory().getAllClassMetadata();
			for (Object key : metadataMap.keySet()) {
				final ClassMetadata classMetadata = (ClassMetadata) metadataMap.get(key);
				final String entityName = classMetadata.getEntityName();
				final Query query = session.createQuery("from " + entityName);
			}
		} finally {
			session.close();
		}

		SpringApplication.run(Application.class, args);
    }

	public static Session getSession() throws HibernateException {
		return ourSessionFactory.openSession();
	}

	@Bean
	public FilterRegistrationBean filterRegistrationBean () {

		SecurityFilter securityFilter = new SecurityFilter();
		FilterRegistrationBean registrationBean = new FilterRegistrationBean();

		ArrayList<String> arrayList = new ArrayList<>();
		arrayList.add("/notes/*");
		arrayList.add("/users/*");

		registrationBean.setUrlPatterns(arrayList);
		registrationBean.setFilter(securityFilter);

		return registrationBean;
	}

	static {
		try {
			Configuration configuration = new Configuration();
			configuration.configure("hibernate.cfg.xml");

			serviceRegistry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
			ourSessionFactory = configuration.buildSessionFactory(serviceRegistry);
		} catch (Throwable ex) {
			throw new ExceptionInInitializerError(ex);
		}
	}
}
