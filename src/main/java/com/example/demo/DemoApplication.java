package com.example.demo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.core.env.Environment;

//@EnableConfigurationProperties({TestConfigBean.class, ConfigBean.class})//此注解用来指定用TestConfigBean，ConfigBean实体类来装载配置信息
//若不指定，可在ConfigBean或TestConfigBean等配置文件中添加注解@Configuration
@SpringBootApplication  //@SpringBootApplication 相当于 @Configuration、@EnableAutoConfiguration 、 @ComponentScan 三个的作用
//如果不使用@ComponentScan指明对象扫描范围，默认指扫描当前启动类所在的包里的对象
//@ComponentScan(basePackages = {"com.example.demo.dao"})
@MapperScan("com.example.demo.dao") //@MapperScan与各mapper上添加@Mapper效果相同,但与@ComponentScan不同
public class DemoApplication {
    private static ApplicationContext context;
    private static Environment environment;

    public static void main(String[] args) {
        /*1.默认写法*/
        context = SpringApplication.run(DemoApplication.class, args);
        /*2.通过SpringApplicationBuilder链式调用*/
//        context = new SpringApplicationBuilder()
//                .sources(DemoApplication.class)  //等同于new SpringApplicationBuilder(DemoApplication.class)
//                .profiles("pro")
//                .properties("spring.application.name=demo").properties("server.port=8080")//与在application.properties里配置作用等同，优先级低于application.properties
//                .run(args);
        //可以通过.set方式添加配置。但建议用Builder设计模式
//        SpringApplication springApplication=new SpringApplication();
//        springApplication.setSources(Collections.singleton(DemoApplication.class.getName()));
//        springApplication.setAdditionalProfiles("pro");
//        springApplication.run(args);

        /*3.提供兼容之前的版本的方法*/
        /**
         * The sources that will be used to create an ApplicationContext. A valid source * is one of: a class, class name, package, package name, or an XML resource
         * location.
         */
//        SpringApplication springApplication = new SpringApplication();
//        springApplication.setAdditionalProfiles("pro");
//        Set<String> sources = new TreeSet<>();
//        sources.add(DemoApplication.class.getName());
//        springApplication.setSources(sources);
//        context = springApplication.run(args);





        environment = context.getEnvironment();
        //可以通过environment.getProperty("")获取application.properties相应配置
        System.out.println(environment.getProperty("spring.datasource.maxActive"));
        System.out.println(environment.getProperty("spring.datasource.maxWait"));
        System.out.println(System.getProperty("java.awt.headless"));
        String property=System.setProperty("java.awt.headless", System.getProperty("java.awt.headless", Boolean.toString(true)));
        System.out.println(property);
    }

    public static ApplicationContext getContext() {
        return context;
    }

    public static Environment getEnvironment() {
        return environment;
    }

    @Autowired
    private Environment env;
    //destroy-method="close"的作用是当数据库连接不使用的时候,就把该连接重新放到数据池中,方便下次使用调用.
//    @Bean(destroyMethod = "close")
    //自己配置一个dataSource,springboot会智能选择这个dataSource
    //当运行测试方法时，首先会进入此方法，若是将此方法注释掉，也无影响
//    public DruidDataSource dataSource() {
//        DruidDataSource dataSource = new DruidDataSource();
//        dataSource.setUrl(env.getProperty("spring.datasource.url"));
//        dataSource.setUsername(env.getProperty("spring.datasource.username"));//用户名
//        dataSource.setPassword(env.getProperty("spring.datasource.password"));//密码
//        dataSource.setDriverClassName(env.getProperty("spring.datasource.driver-class-name"));
//        dataSource.setInitialSize(2);//初始化时建立物理连接的个数
//        dataSource.setMaxActive(20);//最大连接池数量
//        dataSource.setMinIdle(0);//最小连接池数量
//        dataSource.setMaxWait(60000);//获取连接时最大等待时间，单位毫秒。
//        dataSource.setValidationQuery("SELECT 1");//用来检测连接是否有效的sql
//        dataSource.setTestOnBorrow(false);//申请连接时执行validationQuery检测连接是否有效
//        dataSource.setTestWhileIdle(true);//建议配置为true，不影响性能，并且保证安全性。
//        dataSource.setPoolPreparedStatements(false);//是否缓存preparedStatement，也就是PSCache
//        return dataSource;
//    }

}

