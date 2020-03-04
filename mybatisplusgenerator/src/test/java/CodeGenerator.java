import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;

import java.util.ArrayList;
import java.util.List;

/**
 * CodeGenerator
 *
 * @author Sonic
 * @since 2020/3/4
 */
public class CodeGenerator {

    public static void main(String[] args) {
        // 代码生成器
        AutoGenerator mpg = new AutoGenerator();

        // 全局配置
        GlobalConfig gc = new GlobalConfig();
        String projectPath = System.getProperty("user.dir") + "/mybatisplusgenerator";
        gc.setOutputDir(projectPath + "/src/main/java");
        gc.setAuthor("Sonic"); //设置作者
        gc.setOpen(false); // 生成好后是否打开资源管理器
        gc.setFileOverride(true); //重新生成文件时候是否覆盖
        gc.setIdType(IdType.AUTO); //设置主键策略
        gc.setServiceName("%sService"); //设置Service接口名字
        gc.setDateType(DateType.ONLY_DATE); //设置默认日期类型
        // gc.setSwagger2(true); 实体属性 Swagger2 注解
        mpg.setGlobalConfig(gc);

        // 数据源配置
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setUrl("jdbc:mysql://192.168.1.151:3306/mybatis_plus?useSSL=false&allowMultiQueries=true");
        // dsc.setSchemaName("public");
        dsc.setDriverName("com.mysql.cj.jdbc.Driver");
        dsc.setUsername("root");
        dsc.setPassword("Citi2013");
//        dsc.setDbType(DbType.MYSQL);
        mpg.setDataSource(dsc);

        // 包配置
        PackageConfig pc = new PackageConfig();
//        pc.setModuleName("mp"); //设置模块名
        pc.setParent("com.sonic.mp"); // 设置包名
        pc.setEntity("domain"); //设置entity，controller，mapper 文件夹名字
        mpg.setPackageInfo(pc);

        // 自定义配置
        InjectionConfig cfg = new InjectionConfig() {
            @Override
            public void initMap() {
                // to do nothing
            }
        };

        // 如果模板引擎是 velocity
        String templatePath = "/templates/mapper.xml.vm";

        // 自定义输出配置
        List<FileOutConfig> focList = new ArrayList<>();
        // 自定义配置会被优先输出
        focList.add(new FileOutConfig(templatePath) {
            @Override
            public String outputFile(TableInfo tableInfo) {
                // 自定义输出文件名 ， 如果你 Entity 设置了前后缀、此处注意 xml 的名称会跟着发生变化！！
                return projectPath + "/src/main/resources/mapper/" //+ pc.getModuleName()
                        + "/" + tableInfo.getEntityName() + "Mapper" + StringPool.DOT_XML;
            }
        });

        cfg.setFileOutConfigList(focList);
        mpg.setCfg(cfg);

        // 配置模板
        TemplateConfig templateConfig = new TemplateConfig();

        // 配置自定义输出模板
        //指定自定义模板路径，注意不要带上.ftl/.vm, 会根据使用的模板引擎自动识别
        // templateConfig.setEntity("templates/entity2.java");
        // templateConfig.setService();
        // templateConfig.setController();

        templateConfig.setXml(null);
        mpg.setTemplate(templateConfig);

        // 策略配置
        StrategyConfig strategy = new StrategyConfig();
        strategy.setNaming(NamingStrategy.underline_to_camel); //数据库名映射到实体类名称
//        strategy.setTablePrefix(""); //设置不生成表的前缀
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);
//        strategy.setSuperEntityClass("你自己的父类实体,没有就不用设置!");
//        strategy.setEntityLombokModel(true); //使用lombok注解
//        strategy.setLogicDeleteFieldName("is_deleted"); //添加逻辑删除字段
//        strategy.setEntityBooleanColumnRemoveIsPrefix(true);//移除boolean类型的is前缀
        strategy.setRestControllerStyle(true);//添加@RestController注解
//        // 公共父类
//        strategy.setSuperControllerClass("你自己的父类控制器,没有就不用设置!");
//        // 写于父类中的公共字段
//        strategy.setSuperEntityColumns("id");

//        TableFill createdTime = new TableFill("create_time", FieldFill.INSERT); //数据库中字段名
//        TableFill updatedTime = new TableFill("update_time", FieldFill.INSERT_UPDATE); //数据库中字段名
//        strategy.setTableFillList(Arrays.asList(createdTime, updatedTime));
//        strategy.setVersionFieldName("version"); //数据库中字段名

        strategy.setInclude(new String[]{"user"}); //表名，支持正则表达式
        strategy.setControllerMappingHyphenStyle(true); //驼峰名字连接
//        strategy.setTablePrefix(pc.getModuleName() + "_");
        mpg.setStrategy(strategy);
        mpg.execute();
    }
}
