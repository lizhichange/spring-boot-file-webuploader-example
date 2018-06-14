package com.webuploader.repository;

/**
 * Created by IntelliJ IDEA.
 * User:   lizhi
 * Date: 2018-6-12
 * Time: 13:53
 *
 * @author Administrator
 */
public interface QrCodeNewRepository {


    /**
     * Put string.
     *
     * @param rowKey    the row key
     * @param tableName the table name
     * @param family    the family
     * @param column    the column
     * @param value     the value
     * @return the string
     */
    String put(final String rowKey, final String tableName, String family, final String[] column,
               final String[] value);
}
