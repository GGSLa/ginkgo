package club.uglyland.application;

import java.util.Properties;

/**
 * @Author : ZGQ
 * @Date : 2020/5/14 11:10
 * @Version : 1.0
 */
public class FileOperationPath {
    //文件读取的路径
    public static final String logPath;
    public static final String panFilePath;
    private static final String path;
    private static final String linuxPath = "/usr/ginkgo/";
    private static final String windowsPath = "D:\\Java\\server_source\\";

    public static final char splitor;

    static {
        Properties props = System.getProperties();
        String osName =  props.getProperty("os.name");
        if(osName.startsWith("Windows")){
            path =windowsPath;
            splitor='\\';
        }else{
            path =linuxPath;
            splitor='/';
        }
        logPath = path;
        panFilePath = path+"files"+splitor;
    }

    private FileOperationPath(){

    }
}
