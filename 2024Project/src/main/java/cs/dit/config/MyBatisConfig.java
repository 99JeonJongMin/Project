package cs.dit.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import javax.sql.DataSource;

@Configuration
@MapperScan("cs.dit.mapper") // MyBatis 매퍼 스캔
@ComponentScan(basePackages = {"cs.dit.domain", "cs.dit.service", "cs.dit.controller"}) // 기존 XML의 context:component-scan 대체
public class MyBatisConfig {
    
    private final Environment env;

    public MyBatisConfig(Environment env) {
        this.env = env;
    }

    @Bean
    public DataSource dataSource() {
        HikariConfig config = new HikariConfig();

        // 환경 변수에서 DB 설정값 불러오기
        String host = env.getProperty("MYSQLHOST", "mysql.railway.internal");
        String port = env.getProperty("MYSQLPORT", "3306");
        String database = env.getProperty("MYSQLDATABASE", "railway");
        String username = env.getProperty("MYSQLUSER", "root");
        String password = env.getProperty("MYSQLPASSWORD", "password");
        
        System.out.println("MYSQLHOST: " + host);
        System.out.println("MYSQLPORT: " + port);
        System.out.println("MYSQLDATABASE: " + database);
        System.out.println("MYSQLUSER: " + username);
        System.out.println("MYSQLPASSWORD: " + password);

        // JDBC URL 설정
        String jdbcUrl = String.format("jdbc:mysql://%s:%s/%s?serverTimezone=UTC&characterEncoding=UTF-8", host, port, database);
        System.out.println("JDBC URL: " + jdbcUrl);

        config.setJdbcUrl(jdbcUrl);
        config.setUsername(username);
        config.setPassword(password);
        config.setDriverClassName("com.mysql.cj.jdbc.Driver");
        
        return new HikariDataSource(config);
        
        
    }

    @Bean
    public SqlSessionFactory sqlSessionFactory(DataSource dataSource) throws Exception {
        SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
        factoryBean.setDataSource(dataSource);
        return factoryBean.getObject();
    }

    @Bean
    public SqlSessionTemplate sqlSessionTemplate(SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }
}
