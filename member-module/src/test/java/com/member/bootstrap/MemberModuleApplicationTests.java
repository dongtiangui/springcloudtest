package com.member.bootstrap;

import com.member.bootstrap.model.system.SessionModel;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.servlet.http.HttpSession;
import java.io.*;
import java.text.MessageFormat;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.UUID;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MemberModuleApplicationTests {

    @Test
    public void contextLoads() throws IOException {

//        SessionModel model = new SessionModel();
//        model.setSessionId(UUID.randomUUID().getMostSignificantBits());
//        MemberModuleApplicationTests.Serializable(model);
//
        SessionModel serializable = (SessionModel) MemberModuleApplicationTests.unSerializable();
        assert serializable != null;
        System.out.println(serializable.toString());

        Map<String ,Object> map = new LinkedHashMap<>();
        map.put("1",1);
        for (Map.Entry<String, Object> stringObjectEntry : map.entrySet()) {
//            移动游标
            Map.Entry entry = stringObjectEntry;
            System.out.println(MessageFormat.format("map :{0},val:{1}", entry.getKey(), entry.getValue()));
        }

    }

    private static <T> void Serializable(T obj) throws IOException {
//        创建objectinputstream
        try (ObjectOutputStream objectInputStream = new ObjectOutputStream
                (new BufferedOutputStream(new FileOutputStream("/Users/apple/Desktop/session.bat")))) {
            objectInputStream.writeObject(obj);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static Object unSerializable(){
        try {
            ObjectInputStream objectInputStream = new ObjectInputStream(new BufferedInputStream(new FileInputStream("/Users/apple/Desktop/session.bat")));
            return objectInputStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}
