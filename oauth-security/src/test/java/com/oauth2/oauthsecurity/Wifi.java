package com.oauth2.oauthsecurity;

import com.alibaba.excel.ExcelReader;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.excel.support.ExcelTypeEnum;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
@Slf4j
public class Wifi {
    public static void run() throws IOException {
        File file = new File("/Users/apple/Desktop/123.xlsx");
        //        拿到流
//        获取excel管理器
        try (InputStream inputStream = new FileInputStream(file)) {
            listener listener = new listener();
            ExcelReader reader = new ExcelReader(inputStream, ExcelTypeEnum.XLSX,null,listener);
            reader.read();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test(){
//        try {
//            run();
//        } catch (IOException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        String admin = encoder.encode("dong");
        System.out.println(admin);
    }
    private static class listener extends AnalysisEventListener {

        private List<Object> data = new ArrayList<>();

        public List<Object> getData() {
            return data;
        }

        public void setData(List<Object> data) {
            this.data = data;
        }

        //        没读一行执行一次invoke
        @Override
        public void invoke(Object o, AnalysisContext analysisContext) {

            data.add(o);
            String s = o.toString();
            StringBuilder builder = new StringBuilder(s);
            builder.deleteCharAt(s.length()-1);
            String mid = StringUtils.mid(String.valueOf(builder), 1, s.length()-1);
            String[] split = mid.split(",");
            for (String s1 : split) {
                System.out.println(s1);
            }
            log.info("当前行"+analysisContext.getCurrentRowNum()+"数据:"+o.toString());
        }
        //     读完执行
        @Override
        public void doAfterAllAnalysed(AnalysisContext analysisContext) {
            data.clear();
        }
    }
}
