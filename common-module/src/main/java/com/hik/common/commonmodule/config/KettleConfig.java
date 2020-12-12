package com.hik.common.commonmodule.config;
import lombok.extern.slf4j.Slf4j;
import org.pentaho.di.core.Const;
import org.pentaho.di.core.KettleEnvironment;
import org.pentaho.di.core.database.DatabaseMeta;
import org.pentaho.di.core.exception.KettleException;
import org.pentaho.di.core.plugins.*;
import org.pentaho.di.repository.kdr.KettleDatabaseRepository;
import org.pentaho.di.repository.kdr.KettleDatabaseRepositoryMeta;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.io.File;
import java.util.List;

/**
 * kettle资源库
 */
@Configuration
@Slf4j
public class KettleConfig {
    @Value("${kettle.plugin.consumer.path}")
    private String plugin_consumer_path;
    @Value("${kettle.plugin.producer.path}")
    private String plugin_producer_path;
    @Value("${kettle.jndi.path}")
    private String jndi_path;
    @Value("${kettle.user}")
    private String kettle_user;
    @Value("${kettle.pass}")
    private String kettle_pass;
    @Value("${kettle.db.host}")
    private String re_db_host;
    @Value("${kettle.db.name}")
    private String re_db_name;
    @Value("${kettle.db.type}")
    private String re_db_type;
    @Value("${kettle.db.access}")
    private String re_db_access;
    @Value("${kettle.db.dbname}")
    private String re_db_dbname;
    @Value("${kettle.db.dbport}")
    private String re_db_dbport;
    @Value("${kettle.db.dbuser}")
    private String re_db_dbuser;
    @Value("${kettle.db.dbpass}")
    private String re_db_dbpass;
    /**
     * 记载资源库
     * @return
     * @throws KettleException
     */
    @Bean
    public KettleDatabaseRepository RepositoryCon() throws KettleException {
        //加载kafka插件
        PluginFolder folder_consumer = new
                PluginFolder(plugin_consumer_path,
                true, true);
        PluginFolder folder_pruducer = new PluginFolder(plugin_producer_path,true,true);
        List<PluginFolderInterface> folders = StepPluginType.getInstance().getPluginFolders();
        folders.add(folder_consumer);
        folders.add(folder_pruducer);
        //加载jndi数据源
        File jndiFile = new File(jndi_path);
        Const.JNDI_DIRECTORY = jndiFile.getPath();

        // 初始化
        KettleEnvironment.init();
        // 数据库形式的资源库对象
        KettleDatabaseRepository repository = new KettleDatabaseRepository();
    DatabaseMeta databaseMeta =
        new DatabaseMeta(re_db_name, re_db_type, re_db_access, re_db_host, re_db_dbname, re_db_dbport, re_db_dbuser, re_db_dbpass);
        // 数据库形式的资源库元对象
        KettleDatabaseRepositoryMeta kettleDatabaseMeta =
                new KettleDatabaseRepositoryMeta("hik", "hikvision", "king description",databaseMeta);
        // 用资源库元对象初始化资源库对象
        repository.init(kettleDatabaseMeta);
        // 连接到资源库
        repository.connect(kettle_user, kettle_pass); // 默认的连接资源库的用户名和密码
        //创建ktr元对象
        System.out.println(repository.isConnected());
        if (repository.isConnected()) {
            log.info("连接成功");
            return repository;
        } else {
            log.error("连接失败");
            return null;
        }
    }
}
