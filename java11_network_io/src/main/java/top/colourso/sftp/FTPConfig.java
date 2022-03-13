package top.colourso.sftp;

import org.apache.commons.lang.time.DateFormatUtils;

import java.util.Date;

/**
 * @date 2022/3/13 13:31
 */
public class FTPConfig {

    /**
     * url地址
     */
    private String url;

    /**
     * 端口
     */
    private String port;

    /**
     * 用户名
     */
    private String userName;

    /**
     * 密码
     */
    private String passwd;

    /**
     * 文件目录
     */
    private String filePath;

    /**
     * 文件名样式
     */
    private String fileNamePattern;

    /**
     * 代理IP
     */
    private String proxyIp;

    /**
     * 代理端口
     */
    private String proxyPort;

    /**
     * 日期格式
     */
    private String datePattern;

    /**
     * 文件类型
     */
    private String fileType;
    /**
     * 压缩文件的名称
     */
    private String zipFileNamePattern;

    private String connectType;

    private FTPConfig(Builder builder) {
        setUrl(builder.url);
        setPort(builder.port);
        setUserName(builder.userName);
        setPasswd(builder.passwd);
        setFilePath(builder.filePath);
        setFileNamePattern(builder.fileNamePattern);
        setProxyIp(builder.proxyIp);
        setProxyPort(builder.proxyPort);
        setDatePattern(builder.datePattern);
        setFileType(builder.fileType);
        setZipFileNamePattern(builder.zipFileNamePattern);
        setConnectType(builder.connectType);
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPasswd() {
        return passwd;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getFileNamePattern() {
        return fileNamePattern;
    }

    public void setFileNamePattern(String fileNamePattern) {
        this.fileNamePattern = fileNamePattern;
    }

    public String getProxyIp() {
        return proxyIp;
    }

    public void setProxyIp(String proxyIp) {
        this.proxyIp = proxyIp;
    }

    public String getProxyPort() {
        return proxyPort;
    }

    public void setProxyPort(String proxyPort) {
        this.proxyPort = proxyPort;
    }

    public String getDatePattern() {
        return datePattern;
    }

    public void setDatePattern(String datePattern) {
        this.datePattern = datePattern;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public String getZipFileNamePattern() {
        return zipFileNamePattern;
    }

    public void setZipFileNamePattern(String zipFileNamePattern) {
        this.zipFileNamePattern = zipFileNamePattern;
    }

    public String getConnectType() {
        return connectType;
    }

    public void setConnectType(String connectType) {
        this.connectType = connectType;
    }

    /**
     * 获取文件格式
     * @param execDate
     * @return
     */
    public String formatFileName(Date execDate){
        return String.format(fileNamePattern, DateFormatUtils.format(execDate, getDatePattern()));
    }


    public static final class Builder {
        private String url;
        private String port;
        private String userName;
        private String passwd;
        private String filePath;
        private String fileNamePattern;
        private String proxyIp;
        private String proxyPort;
        private String datePattern;
        private String fileType;
        private String zipFileNamePattern;
        private String connectType;

        public Builder() {
        }

        public Builder url(String val) {
            url = val;
            return this;
        }

        public Builder port(String val) {
            port = val;
            return this;
        }

        public Builder userName(String val) {
            userName = val;
            return this;
        }

        public Builder passwd(String val) {
            passwd = val;
            return this;
        }

        public Builder filePath(String val) {
            filePath = val;
            return this;
        }

        public Builder fileNamePattern(String val) {
            fileNamePattern = val;
            return this;
        }

        public Builder proxyIp(String val) {
            proxyIp = val;
            return this;
        }

        public Builder proxyPort(String val) {
            proxyPort = val;
            return this;
        }

        public Builder datePattern(String val) {
            datePattern = val;
            return this;
        }

        public Builder fileType(String val) {
            fileType = val;
            return this;
        }

        public Builder zipFileNamePattern(String val) {
            zipFileNamePattern = val;
            return this;
        }

        public Builder connectType(String val) {
            connectType = val;
            return this;
        }

        public FTPConfig build() {
            return new FTPConfig(this);
        }
    }
}
