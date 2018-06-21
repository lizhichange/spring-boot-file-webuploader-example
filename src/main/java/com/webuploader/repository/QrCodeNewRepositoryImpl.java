package com.webuploader.repository;


import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.util.Bytes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.hadoop.hbase.HbaseTemplate;
import org.springframework.stereotype.Component;


/**
 * Created by IntelliJ IDEA.
 * User:   lizhi
 * Date: 2018-6-12
 * Time: 13:54
 */
@Component
public class QrCodeNewRepositoryImpl implements QrCodeNewRepository {
    @Autowired
    HbaseTemplate hbaseTemplate;

    @Override
    public String put(String rowKey, String tableName, String family, String[] column, String[] value) {
        return hbaseTemplate.execute(tableName, table -> {
            // 设置
            // rowkey
            Put put = new Put(Bytes.toBytes(rowKey));
            for (int j = 0; j < column.length; j++) {
                put.add(Bytes.toBytes(family), Bytes.toBytes(column[j]), Bytes.toBytes(value[j]));
            }
            table.put(put);
            return "ok";
        });
    }
}
