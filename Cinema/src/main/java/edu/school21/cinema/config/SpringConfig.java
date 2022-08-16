package edu.school21.cinema.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;

@Configuration
@PropertySource("classpath:../application.properties")
@EnableTransactionManagement
@EnableJpaRepositories("edu.school21.cinema.repositories")
public class SpringConfig {

    @Autowired
    private Environment env;

    @Bean
    public DataSource dataSource() throws IOException {
        Properties props = new Properties();
        props.setProperty("dataSource.databaseName", env.getProperty("dataSource.databaseName"));
        props.setProperty("dataSource.user", env.getProperty("dataSource.user"));
        props.setProperty("dataSource.password", env.getProperty("dataSource.password"));
        props.setProperty("dataSourceClassName", env.getProperty("dataSourceClassName"));
        props.setProperty("dataSource.portNumber", env.getProperty("dataSource.portNumber"));
        props.setProperty("dataSource.serverName", env.getProperty("dataSource.serverName"));
        props.put("dataSource.logWriter", new PrintWriter(System.out));
        HikariConfig config = new HikariConfig(props);
        HikariDataSource ds = new HikariDataSource(config);
        JdbcTemplate jdbcTemplate = new JdbcTemplate(ds);
        String schema = new String(Files.readAllBytes(Paths.get("src/main/resources/sql/schema.sql")), StandardCharsets.UTF_8);
//        String data = new String(Files.readAllBytes(Paths.get("src/main/resources/sql/data.sql")), StandardCharsets.UTF_8);
        jdbcTemplate.execute(schema);
//        jdbcTemplate.execute(data);
        return ds;
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() throws IOException {
        LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
        factory.setDataSource(dataSource());
        factory.setPackagesToScan("edu.school21.cinema.models");
        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        factory.setJpaVendorAdapter(vendorAdapter);
        Properties properties = new Properties();
        properties.setProperty("hibernate.dialect", env.getProperty("hibernate.dialect"));
        properties.setProperty("hibernate.show_sql", env.getProperty("hibernate.show_sql"));
        factory.setJpaProperties(properties);
        return factory;
    }

    @Bean
    public PlatformTransactionManager transactionManager() throws IOException {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactory().getObject());
        return transactionManager;
    }

    @Bean
    public PersistenceExceptionTranslationPostProcessor exceptionTranslation(){
        return new PersistenceExceptionTranslationPostProcessor();
    }
}
