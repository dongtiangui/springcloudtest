package com.hik.consul.hikconsul;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.ldap.core.AttributesMapper;
//import org.springframework.ldap.core.LdapTemplate;
import org.springframework.test.context.junit4.SpringRunner;

//import static org.springframework.ldap.query.LdapQueryBuilder.query;

@RunWith(SpringRunner.class)
@SpringBootTest
public class HikConsulApplicationTests {

//  @Autowired
//  private LdapTemplate ldapTemplate;

  @Test
  public void contextLoads() {

//    ldapTemplate.search(
//            query().where("objectclass").is("person"), (AttributesMapper<String>) attrs -> (String) attrs.get("cn").get());
  }
}
