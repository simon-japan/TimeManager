package backend;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;

import javax.sql.DataSource;

import static org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType.H2;

/**
 * Created by simon on 8/1/15.
 */

@Configuration
@MapperScan(basePackages = "backend.persistence")
public class DatabaseConfig {

    @Bean
    public DataSource dataSource() { return new EmbeddedDatabaseBuilder().setType(H2).build(); }

    @Bean
    public SqlSessionFactory sqlSessonFactory(DataSource dataSource) throws Exception {
        final SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setDataSource(dataSource);
        return sessionFactory.getObject();
    }

}
