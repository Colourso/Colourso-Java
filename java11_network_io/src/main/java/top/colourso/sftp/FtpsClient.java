package top.colourso.sftp;

import com.jcraft.jsch.*;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.net.ftp.FTPSClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

/**
 * @date 2022/3/13 11:46
 */
public class FtpsClient {

    private static Logger log = LoggerFactory.getLogger(FtpsClient.class);

    /************ 远端vsftp服务器信息 ************/

    // 方式一 FTPClient不支持通过协议SSH2进行SFTP连接,会报错
    // org.apache.commons.net.MalformedServerReplyException: Could not parse response code. Server Reply: SSH-2.0-OpenSSH_7.6p1 Ubuntu-4ubuntu0.6
    public static FTPSClient defaultConnect(String url, int port, String userName, String password, String proxyUrl, int proxyPort) throws Exception {
        FTPSClient ftpsClient = new FTPSClient(getSSL());
        ftpsClient.setKeyManager(null);
        if (StringUtils.isNotBlank(proxyUrl) && proxyPort > 0) {
            Proxy proxy = new Proxy(Proxy.Type.SOCKS, new InetSocketAddress(InetAddress.getByName(proxyUrl), port));
            ftpsClient.setProxy(proxy);
        }
//        ftpsClient.setTrustManager(getTrustManager());
        ftpsClient.setDefaultTimeout(10000);
        ftpsClient.connect(url, port);
        ftpsClient.setSoTimeout(900000);
        ftpsClient.getReplyCode();
        ftpsClient.execPBSZ(0);
        ftpsClient.execPROT("P");
        ftpsClient.login(userName, password);
        ftpsClient.changeWorkingDirectory("/");
        ftpsClient.setDataTimeout(5000);
        ftpsClient.enterLocalPassiveMode();
        return ftpsClient;
    }

    private static SSLContext getSSL(){
        SSLContext sslContext = null;
        try {
            TrustManager[] trustManager = new TrustManager[]{
                    new X509TrustManager() {
                        @Override
                        public X509Certificate[] getAcceptedIssuers() {
                            return null;
                        }

                        @Override
                        public void checkServerTrusted(X509Certificate[] arg0, String arg1) throws CertificateException {
                        }

                        @Override
                        public void checkClientTrusted(X509Certificate[] arg0, String arg1) throws CertificateException {
                        }
                    }
            };
            sslContext = SSLContext.getInstance("SSL");
            sslContext.init(null, trustManager, new SecureRandom());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return  sslContext;
    }

    // 方式二 支持通过协议SSH2进行SFTP连接
    public static ChannelSftp defaultSftpConnect(String url, int port, String userName, String password, String proxyUrl, int proxyPort) throws Exception {
        JSch jsch = new JSch();
        Session sshSession = jsch.getSession(userName, url, port);
        if (!StringUtils.isEmpty(proxyUrl) && proxyPort > 0) {
            sshSession.setProxy(new ProxyHTTP(proxyUrl, proxyPort));
        }
        sshSession.setPassword(password);
        sshSession.setConfig("StrictHostKeyChecking", "no");
        sshSession.connect();
        Channel channel = sshSession.openChannel("sftp");
        channel.connect();
        return (ChannelSftp) channel;
    }

    // 下述两个方法待定 来源：https://www.cnblogs.com/muliu/p/6126126.html
    public static void closeChannel(Channel channel) {
        if (channel != null) {
            if (channel.isConnected()) {
                channel.disconnect();
            }
        }
    }

    public static void closeSession(Session session) {
        if (session != null) {
            if (session.isConnected()) {
                session.disconnect();
            }
        }
    }
}


