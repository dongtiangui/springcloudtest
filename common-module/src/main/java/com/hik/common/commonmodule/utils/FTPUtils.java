package com.hik.common.commonmodule.utils;

import com.hik.common.commonmodule.config.FTPClientFactory;
import com.hik.common.commonmodule.config.FTPClientPool;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPFileFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.*;
import java.util.StringTokenizer;

/**
 * ftp工具类
 */
@Slf4j
@Component
public class FTPUtils {

    /**
     * FTP的连接池
     */
    @Autowired
    public FTPClientPool ftpClientPool;
    /**
     * FTPClient对象
     */
    public FTPClient ftpClient;

    public FTPClient getFtpClient() {
        return ftpClient;
    }

    public void setFtpClient(FTPClient ftpClient) {
        this.ftpClient = ftpClient;
    }

    private FTPUtils ftpUtils;

    @Autowired
    private FTPProperties ftpProperties;

    /**
     * 初始化设置
     * @return
     */
    @PostConstruct
    public boolean init() {
        FTPClientFactory factory = new FTPClientFactory(ftpProperties);
        ftpUtils = this;
        try {
            ftpClientPool = new FTPClientPool(factory);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }


    /**
     * 获取连接对象
     * @return
     * @throws Exception
     */
    public FTPClient getFTPClientInstans() throws Exception {
        //初始化的时候从队列中取出一个连接
        if (getFtpClient()==null) {
            this.setFtpClient(ftpClientPool.borrowObject());
        }
        return ftpClient;
    }


    /**
     * 当前命令执行完成命令完成
     * @throws IOException
     */
    public void complete() throws IOException {
        ftpClient.completePendingCommand();
    }

    /**
     * 当前线程任务处理完成，加入到队列的最后
     * @return
     */
    public void disconnect() throws Exception {
        ftpClientPool.addObject(ftpClient);
    }

    /**
     * Description: 向FTP服务器上传文件
     *
     * @Version1.0
     * @param remoteFile
     *      上传到FTP服务器上的文件名
     * @param input
     *      本地文件流
     * @return 成功返回true，否则返回false
     */
    public  boolean uploadFile(String remoteFile, InputStream input) {
        boolean result = false;
        try {
//            防止ftp连接池 第二次调用为空需要初始化ftpClient为空
            this.ftpClient = null;
            getFTPClientInstans();
            getFtpClient().enterLocalPassiveMode();
            result = getFtpClient().storeFile(remoteFile, input);
            input.close();
            getFtpClient().disconnect();
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return result;
    }

    /**
     * Description: 向FTP服务器上传文件
     *
     * @Version1.0
     * @param remoteFile
     *      上传到FTP服务器上的文件名
     * @param localFile
     *      本地文件
     * @return 成功返回true，否则返回false
     */
    public  boolean uploadFile(String remoteFile, String localFile){
        FileInputStream input = null;
        try {
            input = new FileInputStream(new File(localFile));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return uploadFile(remoteFile, input);
    }

    /**
     * 拷贝文件
     * @param fromFile
     * @param toFile
     * @return
     * @throws Exception
     */
    public boolean copyFile(String fromFile, String toFile) throws Exception {
        this.ftpClient = null;
        InputStream in=getFileInputStream(fromFile);
        getFTPClientInstans();
        boolean flag = ftpClient.storeFile(toFile, in);
        in.close();
        return flag;
    }

    /**
     * 获取文件输入流
     * @param fileName
     * @return
     * @throws IOException
     */
    public  InputStream getFileInputStream(String fileName) throws Exception {
        this.ftpClient = null;
        ByteArrayOutputStream fos=new ByteArrayOutputStream();
        getFTPClientInstans();
        ftpClient.retrieveFile(fileName, fos);
        ByteArrayInputStream in=new ByteArrayInputStream(fos.toByteArray());
        fos.close();
        return in;
    }

    /**
     * Description: 从FTP服务器下载文件
     *
     * @Version1.0
     * @return
     */
    public boolean downFile(String remoteFile, String localFile){
        this.ftpClient = null;
        boolean result = false;
        try {
            getFTPClientInstans();
            OutputStream os = new FileOutputStream(localFile);
            ftpClient.retrieveFile(remoteFile, os);
            ftpClient.logout();
            ftpClient.disconnect();
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    /**
     * 从ftp中获取文件流
     * @param filePath
     * @return
     * @throws Exception
     */
    public  InputStream getInputStream(String filePath) throws Exception {
        this.ftpClient = null;
        getFTPClientInstans();
        return ftpClient.retrieveFileStream(filePath);
    }

    /**
     * ftp中文件重命名
     * @param fromFile
     * @param toFile
     * @return
     * @throws Exception
     */
    public boolean rename(String fromFile,String toFile) throws Exception {
        this.ftpClient = null;
        getFTPClientInstans();
        boolean result = ftpClient.rename(fromFile,toFile);
        return result;
    }

    /**
     * 获取ftp目录下的所有文件
     * @param dir
     * @return
     */
    public FTPFile[] getFiles(String dir) throws Exception {
        this.ftpClient = null;
        getFTPClientInstans();
        FTPFile[] files = new FTPFile[0];
        try {
            files = ftpClient.listFiles(dir);
        }catch (Throwable thr){
            thr.printStackTrace();
        }
        return files;
    }

    /**
     * 获取ftp目录下的某种类型的文件
     * @param dir
     * @param filter
     * @return
     */
    public FTPFile[] getFiles(String dir, FTPFileFilter filter) throws Exception {
        this.ftpClient = null;
        getFTPClientInstans();
        FTPFile[] files = new FTPFile[0];
        try {
            files = ftpClient.listFiles(dir, filter);
        }catch (Throwable thr){
            thr.printStackTrace();
        }
        return files;
    }

    /**
     * 创建文件夹
     * @param remoteDir
     * @return 如果已经有这个文件夹返回false
     */
    public boolean makeDirectory(String remoteDir) throws Exception {
        this.ftpClient = null;
        getFTPClientInstans();
        boolean result = false;
        try {
            result = ftpClient.makeDirectory(remoteDir);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    public boolean mkdirs(String dir) throws Exception {
        this.ftpClient = null;
        boolean result = false;
        if (null == dir) {
            return false;
        }
        getFTPClientInstans();
        ftpClient.changeWorkingDirectory("/");
        StringTokenizer dirs = new StringTokenizer(dir, "/");
        String temp = null;
        while (dirs.hasMoreElements()) {
            temp = dirs.nextElement().toString();
            //创建目录
            ftpClient.makeDirectory(temp);
            //进入目录
            ftpClient.changeWorkingDirectory(temp);
            result = true;
        }
        ftpClient.changeWorkingDirectory("/");
        return result;
    }
}
