package cs.dit.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import javax.sql.DataSource;

@Configuration
@MapperScan("cs.dit.mapper") // MyBatis 매퍼 스캔
@ComponentScan(basePackages = {"cs.dit.domain", "cs.dit.service", "cs.dit.controller"}) // 기존 XML의 context:component-scan 대체
public class MyBatisConfig {
	
	 	@Value("${spring.datasource.url}")
	    private String url;

	    @Value("${spring.datasource.username}")
	    private String username;

	    @Value("${spring.datasource.password}")
	    private String password;
	    
	@Bean
	public DataSource dataSource() {
		  HikariConfig config = new HikariConfig();
	        config.setJdbcUrl(url);
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

