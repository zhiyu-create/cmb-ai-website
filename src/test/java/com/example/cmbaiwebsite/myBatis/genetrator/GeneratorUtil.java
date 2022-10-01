package com.example.cmbaiwebsite.myBatis.genetrator;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;

import java.io.File;

import java.io.IOException;
/**
 * 根据表生成dao mapper
 */
public class GeneratorUtil {

    private static String[] TABLENAME  = {""};

    //作者信息，会自动添加到文件内
    private static String AUTHOR = "author";

    //数据库连接
    private static String DRIVENAME = "com.mysql.cj.jdbc.Driver";                   //驱动，如果版本低于8.0，首先需要更换jar包，此处使用com.mysql.jdbc.Driver
    private static String DATABASE = "jdbc:mysql://localhost:3306/blog";       //数据库连接url
    private static String USERNAME = "root";                                        //账号
    private static String PASSWORD = "123456";                                        //密码


    //对应的包名，第一个为父级包名，其余为子级包名，可以根据需求更改。
    //如下配置会生成 com.shixin.mapper  com.shixin.service  com.shixin.controller ...
    //子包也可以为  xx.xx 格式来代表生成多层文件夹
    private static String PARENT_PACKAGE = "com.example.cmbaiwebsite";            //基础包
    private static String CONTROLLER_FOLERNAME = "controller";      //controller
    private static String MAPPER_FOLERNAME = "mapper";              //Mapper类Java文件 ，也可以叫做dao层文件
    private static String XML_FOLERNAME = "mapperxml";              //XML文件
    private static String ENTITY_FOLERNAME = "entity";                //pojo类文件
    private static String SERVICE_FOLERNAME = "service";            //service

    public static void main(String[] args) throws IOException {

        File directory = new File("");//参数为空
        String courseFile = directory.getCanonicalPath();

        //1. 全局配置
        GlobalConfig config = new GlobalConfig();

        config.setActiveRecord(true) // 是否支持AR模式
                .setAuthor(AUTHOR) // 作者
                .setOutputDir(courseFile+"\\src\\main\\java") // 生成路径
                .setFileOverride(true)  // 文件覆盖
                .setIdType(IdType.AUTO) // 主键策略
                .setServiceName("%sService")  // 设置生成的service接口的名字的首字母是否为I
                // IEmployeeService
                .setBaseResultMap(true)//生成基本的resultMap
                .setBaseColumnList(true);//生成基本的SQL片段

        //2. 数据源配置
        DataSourceConfig dsConfig = new DataSourceConfig();
        dsConfig.setDbType(DbType.MYSQL)  // 设置数据库类型
                .setDriverName(DRIVENAME)
                .setUrl(DATABASE)
                .setUsername(USERNAME)
                .setPassword(PASSWORD);


        //3. 策略配置globalConfiguration中
        StrategyConfig stConfig = new StrategyConfig();
        stConfig.setCapitalMode(true) //全局大写命名
                .setNaming(NamingStrategy.underline_to_camel) // 数据库表映射到实体的命名策略：这里使用的是驼峰转换
                .setInclude(TABLENAME);  // 生成的表

        //4. 包名策略配置
        PackageConfig pkConfig = new PackageConfig();
        pkConfig.setParent(PARENT_PACKAGE)
                .setMapper(MAPPER_FOLERNAME)//dao
                .setService(SERVICE_FOLERNAME)//servcie
                .setController(CONTROLLER_FOLERNAME)//controller
                .setEntity(ENTITY_FOLERNAME)
                .setXml(XML_FOLERNAME);//mapper.xml

        //5. 整合配置
        AutoGenerator ag = new AutoGenerator();
        ag.setGlobalConfig(config)
                .setDataSource(dsConfig)
                .setStrategy(stConfig)
                .setPackageInfo(pkConfig);

        //6. 执行
        ag.execute();
    }
}
