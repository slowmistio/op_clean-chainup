package com.chainup.common.util;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * properties 文件操作的工具类
 * 
 * @author lilp
 *
 */
public class KlinePropertyUtil {
    private static final Logger logger = LoggerFactory.getLogger(KlinePropertyUtil.class);
    private static final String CONFIG_PROPERTY = "application_config.properties";
    private static Properties properties;

    static {
        loadProperties();
    }

    synchronized static private void loadProperties() {
        logger.info("开始加载properties文件内容.........");
        properties = new Properties();
        InputStream in = null;
        try {
            // 第一种，通过类加载器进行获取properties文件流
            in = KlinePropertyUtil.class.getClassLoader().getResourceAsStream(CONFIG_PROPERTY);
            // 第二种，通过类进行获取properties文件流
            // in = PropertyUtil.class.getResourceAsStream(CONFIG_PROPERTY);
            properties.load(in);
        } catch (FileNotFoundException e) {
            logger.error(CONFIG_PROPERTY + "文件未找到");
        } catch (IOException e) {
            logger.error("出现IOException");
        } finally {
            try {
                if (null != in) {
                    in.close();
                }
            } catch (IOException e) {
                logger.error(CONFIG_PROPERTY + "文件流关闭出现异常");
            }
        }
        logger.info("加载properties文件内容完成.........");
    }

    public static String getProperties(String key) {
        String value = "";
        try {
            value = properties.getProperty(key);
        } catch (Exception e) {

        }
        return value;
    }

    public static void main(String[] args) {
        String car_card_type = KlinePropertyUtil.getProperties("db_exchange_jdbc_url");
        System.out.println(car_card_type);
    }
}
