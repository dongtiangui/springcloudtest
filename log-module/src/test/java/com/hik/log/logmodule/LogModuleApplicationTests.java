package com.hik.log.logmodule;

import com.hik.log.logmodule.dao.BaseElasticService;
import com.hik.log.logmodule.util.RedisUtil;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class LogModuleApplicationTests {

  @Autowired
  private RedisUtil redisUtil;

  @Autowired
  private BaseElasticService baseElasticService;

  @BeforeClass
  public static void set(){
    System.setProperty("es.set.netty.runtime.available.processors", "false");
  }
  @Test
  public void contextLoads() {
    try {
      baseElasticService.createIndex("document");
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }
  }
}
