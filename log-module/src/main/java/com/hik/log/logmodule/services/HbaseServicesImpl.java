package com.hik.log.logmodule.services;

import lombok.extern.slf4j.Slf4j;
import org.apache.hadoop.hbase.*;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.filter.FilterList;
import org.apache.hadoop.hbase.filter.SingleColumnValueFilter;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.PropertyAccessorFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.hadoop.hbase.HbaseTemplate;
import org.springframework.data.hadoop.hbase.RowMapper;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class HbaseServicesImpl implements HbaseServices {

    @Autowired
    private HbaseTemplate hbaseTemplate;
    @Override
    public boolean createTable(String tableName, String... family) {
        Admin admin;
        HTableDescriptor hTableDescriptor;
        try {
            Connection connection = ConnectionFactory.createConnection(hbaseTemplate.getConfiguration());
            admin = connection.getAdmin();
            TableName table = TableName.valueOf(tableName);
            hTableDescriptor = new HTableDescriptor(table);
            for (String s : family) {
                hTableDescriptor.addFamily(new HColumnDescriptor(s));
            }
            admin.createTable(hTableDescriptor);
            return admin.tableExists(table);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public <T> List<T> searchAll(String tableName, Class<T> c) {
       return hbaseTemplate.find(tableName, new Scan(), (result, rowNum) -> {
            T instance = c.newInstance();
            /**
             * 属性访问器
             */
            BeanWrapper beanWrapper = PropertyAccessorFactory.forBeanPropertyAccess(instance);
            List<Cell> cellList = result.listCells();
            for (Cell cellItem : cellList) {
                String cellName = new String(CellUtil.cloneQualifier(cellItem));
                if (!"class".equals(cellName)){
                    beanWrapper.setPropertyValue(cellName,new String(CellUtil.cloneValue(cellItem)));
                }

            }
            return instance;
        });
    }
    @Override
    public Object createPro(Object object, String tableName, String family, String rowkey) {
        return null;
    }

    @Override
    public <T> T getOneToClass(Class<T> c, String tableName, String rowkey) {
        return null;
    }

    @Override
    public <T> List<T> getListByCondition(Class<T> c, String tableName, FilterList filterList) {
        return null;
    }

    @Override
    public Map<String, Object> getOneToMap(String tableName, String rowName) {
        return null;
    }

    @Override
    public String getColumn(String tableName, String rowkey, String family, String column) {
        return null;
    }

    @Override
    public <T> List<T> findByRowRange(Class<T> c, String tableName, String startRow, String endRow) {
        return null;
    }

    @Override
    public <T> List<T> searchAllByFilter(Class<T> clazz, String tableName, SingleColumnValueFilter scvf) {
        return null;
    }
}
