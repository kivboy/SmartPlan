package by.vadarod.smartplan.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@EnableTransactionManagement
@PropertySource("classpath:db.properties")
@EnableJpaRepositories("by.vadarod.smartplan.repository")
public class HibernateConfiguration {

    private final static String ENTITY_PACKAGES = "by.vadarod.smartplan.entity";

    @Value("${jdbc.driver}")
    private String driverName;

    @Value("${jdbc.url}")
    private String jdbcUrl;

    @Value("${jdbc.login}")
    private String jdbcLogin;

    @Value("${jdbc.password}")
    private String jdbcPassword;

    @Value("${hibernate.show_sql}")
    private String showSQL;
    @Value("${hibernate.format_sql}")
    private String formatSQL;
    @Value("${hibernate.hbm2ddl.auto}")
    private String hbm2ddl;

    @Bean
    public DataSource dataSource() {

        System.out.println("Driver: " + driverName);
        System.out.println("URL: " + jdbcUrl);
        System.out.println("User: " + jdbcLogin);
        System.out.println("Password: " + jdbcPassword);

        HikariConfig hikariConfig = new HikariConfig();
        hikariConfig.setDriverClassName(driverName);
        hikariConfig.setJdbcUrl(jdbcUrl);
        hikariConfig.setUsername(jdbcLogin);
        hikariConfig.setPassword(jdbcPassword);

        return new HikariDataSource(hikariConfig);
    }

    @Bean
    public Properties properties() {
        System.out.println("Конфигурируем параметры hibernate...");

        Properties properties = new Properties();
        properties.put("hibernate.show_sql", showSQL);
        properties.put("hibernate.format_sql", formatSQL);
        properties.put("hibernate.hbm2ddl.auto",hbm2ddl);

        return properties;
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
        System.out.println("Подключаем datasource " + dataSource());
        entityManagerFactoryBean.setDataSource(dataSource());
        entityManagerFactoryBean.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
        entityManagerFactoryBean.setPackagesToScan(ENTITY_PACKAGES);
        entityManagerFactoryBean.setJpaProperties(properties());

        return entityManagerFactoryBean;
    }

    @Bean
    public PlatformTransactionManager transactionManager() {
        JpaTransactionManager transactionManagerJpa = new JpaTransactionManager();
        transactionManagerJpa.setEntityManagerFactory(entityManagerFactory().getObject());
        return transactionManagerJpa;
    }

}
