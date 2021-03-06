package backend.configuration;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

import static org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType.H2;

/**
 * Created by simon on 8/1/15.
 */

@Configuration
@EnableTransactionManagement
@MapperScan(basePackages = "backend.persistence")
public class DatabaseConfig {

    @Bean
    public DataSource dataSource() {
        //String resource = "backend/configuration/database.properties";
        final DriverManagerDataSource ds = new DriverManagerDataSource();
        ds.setDriverClassName("org.postgresql.Driver");
        ds.setUrl("jdbc:postgresql://localhost:15432");
        ds.setUsername("myapp");
        ds.setPassword("dbpass");
        return ds;
    }
    //public DataSource dataSource() { return new EmbeddedDatabaseBuilder().setType(H2).build(); }

    @Bean
    public SqlSessionFactory sqlSessonFactory(DataSource dataSource) throws Exception {
        final SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setDataSource(dataSource);
        sessionFactory.setTypeHandlersPackage("com.github.javaplugs.mybatis");
        return sessionFactory.getObject();
    }

    @Bean
    public DataSourceTransactionManager transactionManager(){
        return new DataSourceTransactionManager(dataSource());
    }

}
