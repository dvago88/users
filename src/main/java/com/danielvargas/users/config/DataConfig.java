/*
 * Copyright (c) 2019. Daniel Vargas Gómez, All Rights Reserved.
 */

package com.danielvargas.users.config;

import java.util.Objects;
import java.util.Properties;
import javax.sql.DataSource;
import org.apache.tomcat.dbcp.dbcp2.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;


/**
 * Class use for the configuration with data base and all realated data beans.
 *
 * @author Daniel dvago1988@gmail.com
 */
@Configuration
@EnableJpaRepositories(basePackages = "com.danielvargas.users.repository")
@PropertySource("application.properties")
@EnableTransactionManagement
public class DataConfig {

  /**
   * Field representing the environment in which the current application is running.
   */
  private final Environment enviroment;

  /**
   * Constructor use to autowired beans.
   *
   * @param enviroment representing the environment in which the current application is running.
   */
  @Autowired
  public DataConfig(Environment enviroment) {
    this.enviroment = enviroment;
  }

  /**
   * Hibernate needs a bean named entityManagerFactory in order to  manage all the entities, this methods configures that.
   *
   * @return A factory bean of enteties.
   */
  @Bean
  public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
    LocalContainerEntityManagerFactoryBean factoryBean = new LocalContainerEntityManagerFactoryBean();
    HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
    factoryBean.setDataSource(dataSource());
    factoryBean.setJpaVendorAdapter(vendorAdapter);
    factoryBean.setPackagesToScan(enviroment.getProperty("ciclobosque.entity.package"));
    factoryBean.setJpaProperties(getHibernateProperties());
    return factoryBean;
  }

  /**
   * Get hibernate properties for the enviroment. This can be done or we can create a hibernate.cfg.xml file (or hibernate.properties) with the configurations.
   *
   * @return hibernate properties.
   */
  private Properties getHibernateProperties() {
    Properties properties = new Properties();
    properties.put("hibernate.dialect", Objects.requireNonNull(enviroment.getProperty("hibernate.dialect")));
    properties.put("hibernate.implicit_naming_strategy", Objects.requireNonNull(enviroment.getProperty("hibernate.implicit_naming_strategy")));
    properties.put("hibernate.format_sql", Objects.requireNonNull(enviroment.getProperty("hibernate.format_sql")));
    properties.put("hibernate.show_sql", Objects.requireNonNull(enviroment.getProperty("hibernate.show_sql")));
    properties.put("hibernate.hbm2ddl.auto", Objects.requireNonNull(enviroment.getProperty("hibernate.hbm2ddl.auto")));
    properties.put("hibernate.jdbc.lob.non_contextual_creation", Objects.requireNonNull(enviroment.getProperty("hibernate.jdbc.lob.non_contextual_creation")));
    return properties;
  }

  /**
   * Set the data source of the proyect.
   *
   * @return data source object.
   */
  @Bean
  public DataSource dataSource() {
    BasicDataSource dataSource = new BasicDataSource();
    dataSource.setDriverClassName(enviroment.getProperty("ciclobosque.ds.driver"));
    dataSource.setUrl(enviroment.getProperty("ciclobosque.datasource.url"));
    dataSource.setUsername(enviroment.getProperty("ciclobosque.datasource.username"));
    dataSource.setPassword(enviroment.getProperty("ciclobosque.datasource.password"));
    return dataSource;
  }
}
