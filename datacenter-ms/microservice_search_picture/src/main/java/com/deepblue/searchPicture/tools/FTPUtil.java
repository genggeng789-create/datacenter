package com.deepblue.searchPicture.tools;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;

import java.io.*;
import java.net.MalformedURLException;
@Slf4j
public class FTPUtil {


    /**
     * 打开FTP服务链接
     * @param ftpHost
     * @param ftpPort
     * @param ftpUserName
     * @param ftpPassword
     */
    private FTPClient ftpClient;
    public FTPUtil(String hostname, Integer port, String username, String password){
        this.ftpClient = initFtpClient(hostname,port,username,password);
    }

    public FTPClient getFtpClient() {
        return ftpClient;
    }

    public FTPClient initFtpClient(String hostname, Integer port, String username, String password) {
        FTPClient ftpClient = new FTPClient();
        ftpClient.setControlEncoding("utf-8");
        try {
            ftpClient.connect(hostname, port); //连接ftp服务器
            ftpClient.login(username, password);//登录ftp服务器
            ftpClient.getReplyCode(); //是否成功登录服务器
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ftpClient;
    }

    /**
     * 关闭FTP服务链接
     * @throws IOException
     */
    public void disConnection(){
        try {
            if(ftpClient.isConnected()){
                ftpClient.disconnect();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取文件夹下的所有文件信息
     * @param path 文件路径
     */
    public FTPFile[] getFTPDirectoryFiles(String path){
        FTPFile[] files = null;
        try {
            ftpClient.changeWorkingDirectory(path);
            files = ftpClient.listFiles();
        }catch (Exception e){
            e.printStackTrace();
            //关闭连接
            disConnection();
            System.out.println("FTP读取数据异常！");
        }
        return files;
    }

    /**
     * 下载文件 *
     *
     * @param pathname  FTP服务器文件 *
     * @param localFile 下载后的文件 *
     * @return
     */
    public boolean downloadFile(String localFile,String pathname) {
        boolean flag = false;
        OutputStream os = null;
        InputStream is = null;
        BufferedOutputStream bos = null;
        //System.out.println(localFile);
        //System.out.println(pathname);
        String filename = new File(pathname).getName();
        try {
            //切换FTP目录
            ftpClient.changeWorkingDirectory(new File(pathname).getParent());
            ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE); //二进制
            os = new FileOutputStream(new File(localFile));
            bos = new BufferedOutputStream(os);
            ftpClient.retrieveFile(pathname, bos);
            bos.flush();
            os.flush();
//            ftpClient.logout();
            flag = true;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (null != os) {
                try {
                    os.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if (null != bos) {
                try {
                    bos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return flag;
    }

    public void downloadDirectory(String localDirectoryPath,String remoteDirectoryPath) throws IOException {
        String ldp_temp = localDirectoryPath + "/" + new File(remoteDirectoryPath).getName();
        new File(ldp_temp).mkdirs();
        FTPFile[] files = this.getFTPDirectoryFiles(remoteDirectoryPath);
        for (FTPFile file : files) {
            //System.out.println(ldp_temp);
            if(file.isDirectory()){
                downloadDirectory(ldp_temp,remoteDirectoryPath + "/" + file.getName());
            }
            else
            {
                if(downloadFile(ldp_temp+ "/" + file.getName(),remoteDirectoryPath + "/" + file.getName()))
                {
                    log.info(ldp_temp+ "/" + file.getName() + "文件下载完毕");
                }
            }
        }
    }
}