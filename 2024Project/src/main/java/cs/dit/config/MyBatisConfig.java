package cs.dit.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
@ComponentScan(basePackages = {"cs.dit.domain", "cs.dit.service", "cs.dit.controller"}) // 기존 XML의 context:component-scan 대체
public class MyBatisConfig {

    @Bean
    public DataSource dataSource() {
        HikariConfig config = new HikariConfig();

        // ✅ [로컬 개발 환경] DB 정보
        String host = "localhost"; // 로컬 MySQL 서버
        String port = "3306";
        String database = "jmaxdb";
        String username = "jmax";
        String password = "1111"; // ⚠ 보안상 환경변수로 관리하는 것이 좋음

        // ✅ JDBC URL 설정 (log4jdbc 사용하여 SQL 로그 출력 가능)
        String jdbcUrl = String.format(
            "jdbc:mariadb://localhost:3306/jmaxdb?serverTimezone=UTC&characterEncoding=UTF-8",
            host, port, database
        );

        System.out.println("✅ [로컬 개발] 데이터베이스 연결 정보:");
        System.out.println("MYSQLHOST: " + host);
        System.out.println("MYSQLPORT: " + port);
        System.out.println("MYSQLDATABASE: " + database);
        System.out.println("MYSQLUSER: " + username);
        System.out.println("MYSQLPASSWORD: ****"); // 보안상 비밀번호 출력 X

       
        config.setJdbcUrl(jdbcUrl);
        config.setUsername(username);
        config.setPassword(password);
        config.setDriverClassName("org.mariadb.jdbc.Driver"); 

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
