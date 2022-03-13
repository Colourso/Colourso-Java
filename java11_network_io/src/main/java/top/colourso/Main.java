package top.colourso;

import org.apache.commons.lang.time.DateFormatUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import top.colourso.sftp.FTPConfig;
import top.colourso.sftp.MySFTPTask;

import java.util.Date;
import java.util.List;

/**
 * @date 2022/3/13 11:46
 */
public class Main {

    private static final Logger log = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        try {

            FTPConfig config = new FTPConfig.Builder().userName("lighthouse").passwd("UYZGXRl.0418")
                    .url("101.42.134.52").port("22").proxyPort("0").connectType("SFTP").filePath("/home/lighthouse").fileType("csv")
                    .datePattern("yyyyMMdd").fileNamePattern("ZCKTCK_deitail_%s.csv").build();

            String date = DateFormatUtils.format(new Date(),"yyyyMMdd");

            MySFTPTask mySFTPTask = new MySFTPTask();
            List<String> ftpDetail = mySFTPTask.getFTPDetail(date, config,"2");
            log.info("main# ftpDetail: {}", ftpDetail);
        } catch (Exception e) {
            log.error("main# err", e);
        }
        return;
    }


}
