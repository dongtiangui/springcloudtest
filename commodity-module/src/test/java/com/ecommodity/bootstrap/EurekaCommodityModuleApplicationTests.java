package com.ecommodity.bootstrap;

import com.alibaba.excel.ExcelReader;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.excel.support.ExcelTypeEnum;
import com.ecommodity.bootstrap.common.ExcelCommon;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class EurekaCommodityModuleApplicationTests {

    @Test
    public void contextLoads() {
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
            log.info("当前行"+analysisContext.getCurrentRowNum());
        }
        //     读完执行
        @Override
        public void doAfterAllAnalysed(AnalysisContext analysisContext) {
            data.clear();
        }
    }

}
