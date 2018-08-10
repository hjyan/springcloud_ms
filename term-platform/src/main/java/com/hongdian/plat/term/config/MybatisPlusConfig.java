package com.hongdian.plat.term.config;

import java.sql.SQLException;
import java.util.Properties;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.apache.ibatis.mapping.DatabaseIdProvider;
import org.apache.ibatis.mapping.VendorDatabaseIdProvider;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.baomidou.mybatisplus.plugins.PaginationInterceptor;

@Configuration
@MapperScan("com.hongdian.plat.term.mapper")
public class MybatisPlusConfig {

    @Resource
    DataSource dataSource;

    /*
	 * 分页插件，自动识别数据库类型 多租户，请参考官网【插件扩展】
     */
    @Bean
    public PaginationInterceptor paginationInterceptor() throws SQLException {
        PaginationInterceptor interceptor = new PaginationInterceptor();
        interceptor.setDialectType(getDatabaseIdProvider().getDatabaseId(dataSource));
        return interceptor;
    }

    @Bean
    public DatabaseIdProvider getDatabaseIdProvider() {
        DatabaseIdProvider databaseIdProvider = new VendorDatabaseIdProvider();
        Properties p = new Properties();
        p.setProperty("Oracle", "oracle");
        p.setProperty("SQL Server", "sqlserver");
        p.setProperty("MySQL", "mysql");
        databaseIdProvider.setProperties(p);
        return databaseIdProvider;
    }

    /*
	 * oracle数据库配置JdbcTypeForNull
	 * 参考：https://gitee.com/baomidou/mybatisplus-boot-starter/issues/IHS8X
	 * 不需要这样配置了，参考 yml: mybatis-plus: confuguration dbc-type-for-null: 'null'
	 * 
	 * @Bean public ConfigurationCustomizer configurationCustomizer(){ return new
	 * MybatisPlusCustomizers(); }
	 * 
	 * class MybatisPlusCustomizers implements ConfigurationCustomizer {
	 * 
	 * @Override public void customize(org.apache.ibatis.session.Configuration
	 * configuration) { configuration.setJdbcTypeForNull(JdbcType.NULL); } }
     */
}
