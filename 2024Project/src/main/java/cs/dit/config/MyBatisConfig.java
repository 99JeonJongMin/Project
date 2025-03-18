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

        // ✅ 환경 변수에서 DB 정보 가져오기 (Railway 또는 로컬 환경 대응)
        String host = System.getenv().getOrDefault("MYSQLHOST", "localhost");
        String port = System.getenv().getOrDefault("MYSQLPORT", "3306");
        String database = System.getenv().getOrDefault("MYSQLDATABASE", "jmaxdb");
        String username = System.getenv().getOrDefault("MYSQLUSER", "jmax");
        String password = System.getenv().getOrDefault("MYSQLPASSWORD", "1111");

        // ✅ JDBC URL 설정 (MySQL 또는 MariaDB 사용 가능)
        String jdbcUrl = String.format(
            "jdbc:mysql://%s:%s/%s?serverTimezone=UTC&characterEncoding=UTF-8",
            host, port, database
        );

        // ✅ 환경 변수 정보 출력 (보안상 비밀번호는 제외)
        System.out.println("✅ 데이터베이스 연결 정보:");
        System.out.println("MYSQLHOST: " + host);
        System.out.println("MYSQLPORT: " + port);
        System.out.println("MYSQLDATABASE: " + database);
        System.out.println("MYSQLUSER: " + username);
        System.out.println("MYSQLPASSWORD: ****"); // 보안상 비밀번호 출력 X

        // ✅ HikariCP 설정
        config.setJdbcUrl(jdbcUrl);
        config.setUsername(username);
        config.setPassword(password);
        config.setDriverClassName("com.mysql.cj.jdbc.Driver"); // MySQL 드라이버 적용

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
