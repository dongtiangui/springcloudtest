package com.oauth2.oauthsecurity;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OauthSecurityApplicationTests {

    @Test
    public void contextLoads() {

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        String admin = encoder.encode("dong");
        System.out.println(admin);
    }

}
