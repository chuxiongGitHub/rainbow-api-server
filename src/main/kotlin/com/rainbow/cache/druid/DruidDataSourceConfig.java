package com.rainbow.cache.druid;

import com.alibaba.druid.pool.DruidDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.bind.RelaxedPropertyResolver;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.sql.SQLException;

/**
 * Created by Administrator on 2017/6/17.
 */
@Configuration
@EnableTransactionManagement
public class DruidDataSourceConfig implements EnvironmentAware {

    private static final Logger logger = LoggerFactory.getLogger(DruidDataSourceConfig.class);

    private RelaxedPropertyResolver propertyResolver;

    @Override
    public void setEnvironment(Environment environment) {
        this.propertyResolver = new RelaxedPropertyResolver(environment, "spring.datasource");
    }

    @Bean
    public DataSource dataSource() {
        logger.info("开始注入druid");
        DruidDataSource dataSource = new DruidDataSource();

        dataSource.setValidationQuery(propertyResolver.getProperty("validationQuery"));
        dataSource.setPassword(propertyResolver.getProperty("password"));
        dataSource.setUsername(propertyResolver.getProperty("username"));
        dataSource.setUrl(propertyResolver.getProperty("url"));
        dataSource.setDriverClassName(propertyResolver.getProperty("driver-class-name"));
        try {
            dataSource.setFilters("stat,wall");
        } catch (SQLException e) {
            logger.error(e.getMessage());
        }
        return dataSource;
    }
}
