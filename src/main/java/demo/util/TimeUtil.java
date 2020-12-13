package demo.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import lombok.extern.slf4j.Slf4j;

@Slf4j
class TimeUtil {

  public static String TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";

  public static String formatTimeStamp(long ts) {
    if (ts == 0) {
      return "can not read time";
    }
    SimpleDateFormat dateFormat = new SimpleDateFormat(TIME_FORMAT);
    String date = dateFormat.format(new Date(ts));
    return date;
  }

}
