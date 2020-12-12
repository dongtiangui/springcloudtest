package com.hik.common.commonmodule.utils;

import com.jcraft.jsch.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import java.io.InputStream;
import java.util.Random;
import java.util.Properties;

/**
 * nginx 工具类
 */
@Component
public class NginxUtil {
    private static Logger logger = LoggerFactory.getLogger(NginxUtil.class);
    /**
     * ftp服务器ip地址
     */
    private static String host;
    @Value("${nginx.host}")
    public void setHost(String val){
        NginxUtil.host = val;
    }
    /**
     * 端口
     */
    private static int port;
    @Value("${nginx.port}")
    public void setPort(int val){
        NginxUtil.port = val;
    }
    /**
     * 用户名
     */
    private static String userName;
    @Value("${nginx.userName}")
    public void setUserName(String val){
        NginxUtil.userName = val;
    }
    /**
     * 密码
     */
    private static String password;
    @Value("${nginx.password}")
    public void setPassword(String val){
        NginxUtil.password = val;
    }
    /**
     * 存放图片的根目录
     */
    private static String rootPath;
    @Value("${nginx.rootPath}")
    public void setRootPath(String val){
        NginxUtil.rootPath = val;
    }
    /**
     * 存放图片的路径
     */
    private static String imgUrl;
    @Value("${nginx.img.url}")
    public void setImgUrl(String val){
        NginxUtil.imgUrl = val;
    }

    /**
     * 获取连接
     * @return
     * @throws JSchException
     */
    public static ChannelSftp getChannel() throws JSchException {
        JSch jsch = new JSch();
        //->ssh root@host:port
        Session sshSession = jsch.getSession(userName,host,port);
        sshSession.setPassword(password);
        Properties sshConfig = new Properties();
        sshConfig.put("StrictHostKeyChecking", "no");
        sshSession.setConfig(sshConfig);
        sshSession.connect();
        Channel channel = sshSession.openChannel("sftp");
        channel.connect();
        return (ChannelSftp) channel;
    }

    /**
     * 插入图片
     * @param inputStream
     * @param imagePath
     * @param imagesName
     * @return
     */
    public static String putImages(InputStream inputStream, String imagePath, String imagesName){
        try {
            ChannelSftp sftp = getChannel();
            String path = rootPath + imagePath + "/";
            createDir(path,sftp);
            //上传文件
            sftp.put(inputStream, path + imagesName);
            logger.info("上传成功！");
            sftp.quit();
            sftp.exit();
            //处理返回的路径
            String resultFile;
            resultFile = imgUrl + imagePath+ "/"+ imagesName;
            return resultFile;
        } catch (Exception e) {
            logger.error("上传失败：" + e.getMessage());
        }
        return "";
    }
    public static String genImageName() {
        //取当前时间的长整形值包含毫秒
        long millis = System.currentTimeMillis();
        //加上三位随机数
        Random random = new Random();
        int end3 = random.nextInt(999);
        //如果不足三位前面补0
        return millis + String.format("%03d", end3);
    }

    private static void createDir(String path,ChannelSftp sftp) throws SftpException {
        String[] folders = path.split("/");
        sftp.cd("/");
        for ( String folder : folders ) {
            if ( folder.length() > 0 ) {
                try {
                    sftp.cd( folder );
                }catch ( SftpException e ) {
                    sftp.mkdir( folder );
                    sftp.cd( folder );
                }
            }
        }
    }
    /**
     * 删除图片
     * @param imagesName
     */
    public static void delImages(String imagesName){
        try {
            ChannelSftp sftp = getChannel();
            String path = rootPath + imagesName;
            sftp.rm(path);
            sftp.quit();
            sftp.exit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
