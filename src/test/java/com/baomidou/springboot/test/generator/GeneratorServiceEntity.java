package com.baomidou.springboot.test.generator;

import org.junit.Test;

import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.rules.DbType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;

/**
 * <p>
 * 测试生成代码
 * </p>
 *
 * @author K神
 * @date 2017/12/18
 */
public class GeneratorServiceEntity {

    @Test
    public void generateCode() {
        String packageName = "com.zynn.service.module.school";
        boolean serviceNameStartWithI = false;//user -> UserService, 设置成true: user -> IUserService
//        generateByTables(serviceNameStartWithI, packageName, "school_be_invited_record","school_invite_record","school_member","school_popularity","school_popularity_inviter","school_popularity_like","school_popularity_list","school_subject");
        generateByTables(serviceNameStartWithI, packageName, "school_popularity_pk_comment","school_popularity_pk");
    }

    private void generateByTables(boolean serviceNameStartWithI, String packageName, String... tableNames) {
        GlobalConfig config = new GlobalConfig();
        String dbUrl = "jdbc:mysql://115.159.87.23:3306/yinian_school";
        DataSourceConfig dataSourceConfig = new DataSourceConfig();
        dataSourceConfig.setDbType(DbType.MYSQL)
                .setUrl(dbUrl)
                .setUsername("depuser")
                .setPassword("uibMmhQ%31")
                .setDriverName("com.mysql.jdbc.Driver");
        StrategyConfig strategyConfig = new StrategyConfig();
        strategyConfig
                //.setCapitalMode(true)
                .setSuperEntityClass("com.zynn.common.core.base.BaseEntity")
                .setSuperEntityColumns(new String[] { "id", "create_time", "update_time", "create_user", "update_user"})
                .setSuperMapperClass("com.zynn.common.core.base.BaseMapper")
                .setSuperServiceClass("com.zynn.common.core.service.BaseService")
                .setSuperServiceImplClass("com.zynn.common.core.service.impl.BaseServiceImpl")
                .setSuperControllerClass("com.zynn.common.core.base.BaseController")
                .setRestControllerStyle(true)//设置成restController
                .setEntityLombokModel(true)
                .setDbColumnUnderline(true)
                .setNaming(NamingStrategy.underline_to_camel)
                .setInclude(tableNames);//修改替换成你需要的表名，多个表名传数组

        config.setActiveRecord(false)
                .setAuthor("刘天元")
                .setOutputDir("d:\\codeGen")
                .setFileOverride(true)
                .setEnableCache(false);
        if (!serviceNameStartWithI) {
            config.setServiceName("%sService");
        }
        new AutoGenerator().setGlobalConfig(config)
                .setDataSource(dataSourceConfig)
                .setStrategy(strategyConfig)
                .setPackageInfo(
                        new PackageConfig()
                                .setParent(packageName)
                                .setController("controller")
                                .setEntity("entity")
                                .setMapper("dao")
                ).execute();
    }

    private void generateByTables(String packageName, String... tableNames) {
        generateByTables(true, packageName, tableNames);
    }
}
