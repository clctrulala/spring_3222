package web.config;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.*;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import web.model.Car;
import web.service.CarService;
import web.service.CarServiceImpl;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@PropertySource("./WEB-INF/classes/db.properties")
@EnableTransactionManagement
@ComponentScan("web")
public class AppConfig {

    @Autowired
    private Environment env;

    @Bean
    public DataSource getDataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(env.getProperty("db.driver"));
        dataSource.setUrl(env.getProperty("db.url"));
        dataSource.setUsername(env.getProperty("db.username"));
        dataSource.setPassword(env.getProperty("db.password"));
        return dataSource;
    }

    @Bean
    public LocalSessionFactoryBean getSessionFactory() {
        LocalSessionFactoryBean factoryBean = new LocalSessionFactoryBean();
        factoryBean.setDataSource(getDataSource());

        Properties props=new Properties();
        props.put("hibernate.show_sql", env.getProperty("hibernate.show_sql"));
        props.put("hibernate.hbm2ddl.auto", env.getProperty("hibernate.hbm2ddl.auto"));

        factoryBean.setHibernateProperties(props);
        factoryBean.setAnnotatedClasses(Car.class);
        return factoryBean;
    }

    @Bean
    public HibernateTransactionManager getTransactionManager() {
        HibernateTransactionManager transactionManager = new HibernateTransactionManager();
        transactionManager.setSessionFactory(getSessionFactory().getObject());

        // заполнение базы
        Session session = transactionManager.getSessionFactory().openSession();
        Transaction transaction = session.getTransaction();
        String sql = "insert into Car(color, brand, number) values ('color_a', 'Brand_A', 111),('color_b', 'Brand_B', 222),('color_c', 'Brand_C', 333),('color_d', 'Brand_D', 444),('color_e', 'Brand_E', 555);";
        transaction.begin();
        session.createSQLQuery(sql).executeUpdate();
        transaction.commit();
        session.close();

        return transactionManager;
    }
}
