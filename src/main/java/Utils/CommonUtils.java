package Utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class CommonUtils {
	public static String getTimeStamp() {
		return new SimpleDateFormat("YYYYMMDDhhmmss").format(new Date());
	}

}
