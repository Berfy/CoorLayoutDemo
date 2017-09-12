package cn.berfy.framework.common;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by Berfy on 2016/9/7.
 * 配置
 */
public class Constants {

    public static final String XML_TEMP_CACEH = "xml_temp";
    public static final ExecutorService EXECUTOR3 = Executors.newFixedThreadPool(3);
    public static final ExecutorService EXECUTOR = Executors.newFixedThreadPool(10);
    public static final ExecutorService EXECUTOR_REFRESH = Executors.newScheduledThreadPool(10);

    public static final String TEMP_PHOTO_PATH = "photo/";
}
