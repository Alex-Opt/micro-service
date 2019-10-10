package com.ly.mt.payment.server.base.config;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.TransactionManagementConfigurer;

import javax.annotation.Resource;
import javax.sql.DataSource;

@Configuration
@EnableTransactionManagement
public class SqlSessionFactoryConfig implements TransactionManagementConfigurer {
    private final static String MAPPER_LOCATIONS = "classpath:mapper/**/*.xml";
    private final static String TYPE_ALIASES_PACKAGE =
            "com.ly.mt.core.base.entity.activity," +
                    "com.ly.mt.core.base.entity.basic," +
                    "com.ly.mt.core.base.entity.border," +
                    "com.ly.mt.core.base.entity.buycar," +
                    "com.ly.mt.core.base.entity.coupon," +
                    "com.ly.mt.core.base.entity.delivery," +
                    "com.ly.mt.core.base.entity.goods," +
                    "com.ly.mt.core.base.entity.marketing," +
                    "com.ly.mt.core.base.entity.payment," +
                    "com.ly.mt.core.base.entity.point," +
                    "com.ly.mt.core.base.entity.punchcard," +
                    "com.ly.mt.core.base.entity.punchase," +
                    "com.ly.mt.core.base.entity.shop," +
                    "com.ly.mt.core.base.entity.trade," +
                    "com.ly.mt.core.base.entity.marketing," +
                    "com.ly.mt.core.base.entity.gzg.b," +
                    "com.ly.mt.core.base.entity.user,"+
                    "com.ly.mt.core.base.entity.hd";
    @Resource
    private DataSource dataSource;

    @Bean(name = "sqlSessionFactory")
    public SqlSessionFactory createSqlSessionFactoryBean() throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        bean.setTypeAliasesPackage(TYPE_ALIASES_PACKAGE);
        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        bean.setMapperLocations(resolver.getResources(MAPPER_LOCATIONS));
        bean.getObject().getConfiguration().setMapUnderscoreToCamelCase(true);
        return bean.getObject();
    }

    @Bean
    public SqlSessionTemplate sqlSessionTemplate(SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }

    @Bean
    @Override
    public PlatformTransactionManager annotationDrivenTransactionManager() {
        return new DataSourceTransactionManager(dataSource);
    }
}