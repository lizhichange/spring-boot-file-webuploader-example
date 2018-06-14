package com.webuploader.controller;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * JThink@JThink
 *
 * @author JThink
 * @version 0.0.1
 * date： 2016-11-16 14:51:42
 */
@ConfigurationProperties(prefix = "spring.data.hbase")
@Component
public class HbaseProperties {

    private String quorum;

    private String rootDir;

    private String nodeParent;

    private String zkClientPort;

    public String getZkClientPort() {
        return zkClientPort;
    }

    public void setZkClientPort(String zkClientPort) {
        this.zkClientPort = zkClientPort;
    }

    public String getQuorum() {
        return quorum;
    }

    public void setQuorum(String quorum) {
        this.quorum = quorum;
    }

    public String getRootDir() {
        return rootDir;
    }

    public void setRootDir(String rootDir) {
        this.rootDir = rootDir;
    }

    public String getNodeParent() {
        return nodeParent;
    }

    public void setNodeParent(String nodeParent) {
        this.nodeParent = nodeParent;
    }
}
