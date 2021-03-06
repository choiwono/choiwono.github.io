---
layout: post
title: xml, java config 설정법
date: 2019-02-05 12:46:32 +0900
description:
categories: Java
tags: xml java_config
---

# xml로 설정하기

## 1. data source bean을 설정한다

```xml

<!-- Connection Pool DataSource -->
<bean id="dataSource" class="org.apache.commons.dbcp.BasicDataSource">
     <property name="driverClassName" value="com.mysql.jdbc.Driver" />
     <property name="url" value="jdbc:mysql://localhost:3306/jpadb" />
     <property name="username" value="jpadb" />
     <property name="password" value="jpadb" />
</bean>

```

* applicationContext.xml 파일에서 dataSource를 정의한다.

## 2. Entity Manager factory bean을 설정한다

1) Entity 탐색 범위 설정
2) 하이버네이트 구현체 사용
3) 하이버네이트 상세 설정

## 3. transaction manager bean을 설정한다

## 4. enable annotation driven transaction management를 설정한다

* 트랜잭션 관리자 활성화 및 등록 합니다
  
## 5. Spring Data Jpa를 설정한다

```xml

<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
  xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
  xmlns:jpa="http://www.springframework.org/schema/data/jpa"
  xmlns:tx="http://www.springframework.org/schema/tx"
  xmlns:context="http://www.springframework.org/schema/context"
  xsi:schemaLocation="http://www.springframework.org/schema/beans 
    http://www.springframework.org/schema/beans/spring-beans.xsd
    http://www.springframework.org/schema/data/jpa 
    http://www.springframework.org/schema/data/jpa/spring-jpa-1.0.xsd
    http://www.springframework.org/schema/tx 
    http://www.springframework.org/schema/tx/spring-tx-3.1.xsd
    http://www.springframework.org/schema/context 
    http://www.springframework.org/schema/context/spring-context-3.1.xsd">

  <!-- data source bean 설정 -->
  <jee:jndi-lookup id="dataSource" jndi-name="java:comp/env/jdbc/CustomerSupport"/>

  <!-- configuration for Hibernate 하이버네이트 기본설정 -->
  <bean id="hibernateJpaVendorAdapter" 
    class="org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter"/>

  <!-- entity manager factory bean 설정 -->
  <bean id="entityManagerFactory" 
    class="org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean">
    <property name="dataSource" ref="dataSource"/>
    <property name="jpaVendorAdapter" ref="hibernateJpaVendorAdapter"/>
    <!-- Set JPA properties -->
    <property name="jpaProperties">
      <props>
        <prop key="hibernate.dialect">org.hibernate.dialect.MySQL5InnoDBDialect</prop>
        <prop key="javax.persistence.schema-generation.database.action">none</prop>
        <prop key="hibernate.ejb.use_class_enhancer">true</prop>
      </props>
    </property>
    <!-- 기본 entity 설정 -->
    <property name="packagesToScan" value="foo.bar.model"/>
    <!-- shareCaheMode 설정 -->
    <property name="sharedCacheMode" value="ENABLE_SELECTIVE"/>
    <!-- 유효성 검사 설정 -->
    <property name="validationMode" value="NONE"/>
  </bean>

  <!-- 트랜잭션 매니저 활성화 설정 -->
  <bean id="transactionManager" 
    class="org.springframework.orm.jpa.JpaTransactionManager">
    <property name="entityManagerFactory" ref="entityManagerFactory"/>
  </bean>

  <!-- 트랜잭션 관리자 사용가능하게 등록 -->
  <tx:annotation-driven/>

  <!-- 
    Spring Data JPA 설정 및 저장소 인터페이스의 기본 패키지 설정
  -->
  <jpa:repositories base-package="foo.bar.repository"/>
</beans>

```

[출처1] (https://victorydntmd.tistory.com/202)
[출처2] (https://stackoverflow.com/questions/25049778/how-to-configure-spring-data-jpa-using-xml)

# 2. java config로 설정하기

xml 과 설정해야 할 bean은 동일하다.

```java

@Configuration
@EnableTransactionManagement
public class PersistenceJPAConfig{
 
   @Bean
   public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
      LocalContainerEntityManagerFactoryBean em 
        = new LocalContainerEntityManagerFactoryBean();
      em.setDataSource(dataSource());
      em.setPackagesToScan(new String[] { "org.baeldung.persistence.model" });
 
      JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
      em.setJpaVendorAdapter(vendorAdapter);
      em.setJpaProperties(additionalProperties());
 
      return em;
   }
 
   @Bean
   public DataSource dataSource(){
      DriverManagerDataSource dataSource = new DriverManagerDataSource();
      dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
      dataSource.setUrl("jdbc:mysql://localhost:3306/spring_jpa");
      dataSource.setUsername( "tutorialuser" );
      dataSource.setPassword( "tutorialmy5ql" );
      return dataSource;
   }
 
   @Bean
   public PlatformTransactionManager transactionManager(
     EntityManagerFactory emf){
       JpaTransactionManager transactionManager = new JpaTransactionManager();
       transactionManager.setEntityManagerFactory(emf);
 
       return transactionManager;
   }
 
   @Bean
   public PersistenceExceptionTranslationPostProcessor exceptionTranslation(){
       return new PersistenceExceptionTranslationPostProcessor();
   }
 
   Properties additionalProperties() {
       Properties properties = new Properties();
       properties.setProperty("hibernate.hbm2ddl.auto", "create-drop");
       properties.setProperty(
         "hibernate.dialect", "org.hibernate.dialect.MySQL5Dialect");
        
       return properties;
   }
}

```