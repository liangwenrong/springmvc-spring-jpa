# springmvc-spring-jpa
spring整合jpa
 - ide是eclipse
 - 使用JDK8
 - spring.version=5.1.8.RELEASE
 - 引入依赖：
 
 ```xml
         <!-- jpa规范 -->
        <dependency>
            <groupId>org.springframework.data</groupId>
            <artifactId>spring-data-jpa</artifactId>
            <version>2.1.4.RELEASE</version>
        </dependency>
        <!-- jpa的hibernate实现，当前版本已经被移动到hibernate-core，这里是个空包 -->
        <dependency>
            <groupId>org.hibernate</groupId>
            <artifactId>hibernate-entitymanager</artifactId>
            <version>${hibernate.version}</version>
        </dependency>
 ```
 - spring整合配置
```xml
     <!-- 1、生成jpa-bean工厂 -->
    <bean id="entityManagerFactory"
        class="org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean">
        <!-- 数据源 -->
        <property name="dataSource" ref="dataSource" />
        <!-- jpa适配器，这里使用hibernate的实现 -->
        <property name="jpaVendorAdapter">
            <bean
                class="org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter">
                <!-- 支持的数据库类型 -->
                <property name="Database" value="MYSQL" />
                <!-- 正向工程 -->
                <!-- <property name="generateDdl" value="false" /> -->
                <!-- 控制台输出sql -->
                <property name="showSql" value="true" />
            </bean>
        </property>
        <!-- 实体类的包路径扫描 -->
        <property name="packagesToScan">
            <list>
                <value>com.lwr.entity</value>
            </list>
        </property>
    </bean>
    <!-- 2、jpa的dao类的扫描路径，扫描自动生成bean交由spring管理 -->
    <jpa:repositories base-package="com.lwr.dao" 
        entity-manager-factory-ref="entityManagerFactory" 
        transaction-manager-ref="transactionManager"/>

    <!-- 3、配置jpa的事务管理器 -->
    <bean id="transactionManager"
        class="org.springframework.orm.jpa.JpaTransactionManager">
        <property name="entityManagerFactory"
            ref="entityManagerFactory"></property>
    </bean>
```
- 实体类
```java
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="user1")
public class User {
    @Id
    String id;
    
    @Column
    String name;
}
```
- dao层接口类

```java
import org.springframework.data.repository.Repository;
import com.lwr.entity.User;

public interface UserDao extends Repository<User, String> {
    //根据jpa-api规范，get[]By[字段名]
    public User getUserById(String id);
}

```

-------

- 以上是基本的配置和实现，更多扩展应该关注`org.springframework.data.repository.Repository`这个接口的子接口和子类，关注jpa的API规范写法
