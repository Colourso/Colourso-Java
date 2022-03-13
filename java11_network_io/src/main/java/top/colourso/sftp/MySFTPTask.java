package top.colourso.sftp;

import com.google.common.collect.Lists;
import com.jcraft.jsch.ChannelSftp;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateUtils;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPSClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

/**
 * @date 2022/3/13 13:28
 */
public class MySFTPTask {

    private final static Logger log = LoggerFactory.getLogger(MySFTPTask.class);

    public List<String> getFTPDetail(String profitDate, FTPConfig config, String type) throws Exception {
        List<String> infos = Lists.newArrayList();
        if ("1".equals(type)) {
            FTPSClient ftpsClient = FtpsClient.defaultConnect(config.getUrl(), Integer.parseInt(config.getPort()),
                    config.getUserName(), config.getPasswd(), config.getProxyIp(), Integer.parseInt(config.getProxyPort()));
            // 文件路径
            String filePath = config.getFilePath();
            FTPFile[] ftpFiles = ftpsClient.listFiles(filePath);
            if (ftpFiles == null || ftpFiles.length == 0) {
                throw new Exception("FTP目录中文件为空");
            }
            InputStream inputStream = ftpsClient.retrieveFileStream(filePath + "/" + config.formatFileName(DateUtils.parseDate(profitDate, new String[]{"yyyyMMdd"})));
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
                infos = processFile(reader);
            }
        } else if ("2".equals(type)) {
            ChannelSftp channelSftp = FtpsClient.defaultSftpConnect(config.getUrl(), Integer.parseInt(config.getPort()),
                    config.getUserName(), config.getPasswd(), config.getProxyIp(), Integer.parseInt(config.getProxyPort()));
            String filePath = config.getFilePath();
            channelSftp.cd(filePath);

            // 文件名称
            String fileName = config.formatFileName(DateUtils.parseDate(profitDate, new String[]{"yyyyMMdd"}));
            InputStream inputStream = channelSftp.get(fileName);
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
                infos = processFile(reader);
            }finally {
                FtpsClient.closeChannel(channelSftp);
            }
        }

        return infos;
    }

    private List<String> processFile(BufferedReader reader) throws IOException {
        List<String> strInfos = Lists.newArrayList();
        int realLineCnt = 0;
        String dataLine;
        while (StringUtils.isNotBlank(dataLine = reader.readLine())) {
            ++realLineCnt;
            log.info("processFile# line:{}, content:{}", realLineCnt, dataLine);
            if (StringUtils.isNotBlank(dataLine)) {
                strInfos.add(dataLine);
            }
        }
        return strInfos;
    }
}
