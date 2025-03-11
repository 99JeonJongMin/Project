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
import javax.sql.DataSource;

@Configuration
@MapperScan("cs.dit.mapper") // MyBatis 매퍼 스캔
@ComponentScan(basePackages = {"cs.dit.domain", "cs.dit.service", "cs.dit.controller"}) // 기존 XML의 context:component-scan 대체
public class MyBatisConfig {

	@Bean
	public DataSource dataSource() {
	    HikariConfig hikariConfig = new HikariConfig();
	    hikariConfig.setDriverClassName("com.mysql.cj.jdbc.Driver");
	    // 환경 변수에서 직접 읽기
	    String host = System.getenv("MYSQL_HOST");
	    String port = System.getenv("MYSQL_PORT");
	    String database = System.getenv("MYSQL_DATABASE");
	    String username = System.getenv("MYSQL_USER");
	    String password = System.getenv("MYSQL_PASSWORD");

	    hikariConfig.setJdbcUrl("jdbc:mysql://" + host + ":" + port + "/" + database);
	    hikariConfig.setUsername(username);
	    hikariConfig.setPassword(password);

	    return new HikariDataSource(hikariConfig);
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

