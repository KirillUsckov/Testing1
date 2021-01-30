package unionReportingTests.sysUtils;

import logger.Logger;

import java.net.InetAddress;

public class SystemUtil {
    private static final Logger LOG = new Logger(SystemUtil.class);

    public static String getPCName() {
        try {
            InetAddress addr = InetAddress.getLocalHost();
            return addr.getHostName();
        } catch (Exception e) {
            LOG.error(e);
            return null;
        }
    }
}
