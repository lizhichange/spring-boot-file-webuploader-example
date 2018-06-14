package com.webuploader.controller;


import org.apache.hadoop.hbase.HBaseConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.hadoop.hbase.HbaseTemplate;


@Configuration
public class HbaseTemplateConfig {

    private static final String HBASE_QUORUM = "hbase.zookeeper.quorum";


    private static final String HBASE_ZK_CLIENT_PORT = "hbase.zookeeper.property.clientPort";

    @Autowired
    private HbaseProperties hbaseProperties;

    @Bean
    @ConditionalOnMissingBean(HbaseTemplate.class)
    public HbaseTemplate getHbaseTemplate() {


        HbaseTemplate hbaseTemplate = new HbaseTemplate();

        org.apache.hadoop.conf.Configuration configuration = HBaseConfiguration.create();
        configuration.set(HBASE_QUORUM, this.hbaseProperties.getQuorum());
        configuration.set(HBASE_ZK_CLIENT_PORT, hbaseProperties.getZkClientPort());
        hbaseTemplate.setConfiguration(configuration);

        return hbaseTemplate;
    }


}
