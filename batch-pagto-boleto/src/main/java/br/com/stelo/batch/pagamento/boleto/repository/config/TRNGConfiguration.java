package br.com.stelo.batch.pagamento.boleto.repository.config;

import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import oracle.jdbc.pool.OracleDataSource;

@Configuration
@Primary
@ConfigurationProperties("oracle.trng")
public class TRNGConfiguration {

	private String username;

    private String password;

    private String url;

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Primary
    @Bean(name="TRNGDS", destroyMethod = "close")
    DataSource dataSourceTrng() throws SQLException {

        OracleDataSource dataSource = new OracleDataSource();
        
        dataSource.setUser(username);
        dataSource.setPassword(password);
        dataSource.setURL(url);
        
        dataSource.setImplicitCachingEnabled(true);
        dataSource.setFastConnectionFailoverEnabled(false);
        return dataSource;
    }
}
