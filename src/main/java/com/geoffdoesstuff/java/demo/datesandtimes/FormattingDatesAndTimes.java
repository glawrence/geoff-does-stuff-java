package com.geoffdoesstuff.java.demo.datesandtimes;

import com.geoffdoesstuff.java.utility.DemoUtilities;

import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class FormattingDatesAndTimes {
    private final LocalDateTime nowLocal;
    private final ZonedDateTime nowZoned;
    private final ZonedDateTime nowUTC;

    public FormattingDatesAndTimes() {
        nowZoned = ZonedDateTime.now();
        nowLocal = nowZoned.toLocalDateTime();
        nowUTC = nowZoned.withZoneSameInstant(ZoneId.of("UTC"));
    }

    /*
        Get the current UTC time and then show formatted output, in one of two ways
     */
    public void formattingDemo() {
        ZonedDateTime currentUTC = ZonedDateTime.now(ZoneId.of("UTC"));
        System.out.println(DateTimeFormatter.ISO_DATE_TIME.format(currentUTC));
        System.out.println(currentUTC.format(DateTimeFormatter.ISO_DATE_TIME));
    }

    public void outputDateTime() {
        DemoUtilities.outputTitle("ISO_DATE_TIME", true);
        outputDateTime(DateTimeFormatter.ISO_DATE_TIME);
        DemoUtilities.outputTitle("ISO_LOCAL_DATE_TIME", true);
        outputDateTime(DateTimeFormatter.ISO_LOCAL_DATE_TIME);

        List<String> patterns = List.of("E dd MMM yyyy hh:mm:ss",
                "EEEE, dd MMMM yyyy 'at' hh:mm:ss z",
                "dd-MM-yyyy G hh:mm:ss VV, v, O",
                "'day' D 'of' yyyy 'which is in' qqq, 'the' qqqq");
        patterns.forEach(pattern -> {
            DemoUtilities.outputTitle(pattern, true);
            outputDateTime(pattern);
        });
    }

    public void outputDateTime(DateTimeFormatter formatter) {
        try {
            System.out.println(nowLocal.format(formatter));
        } catch (DateTimeException dte) {
            if (dte.getMessage().startsWith("Unable to extract ZoneId from temporal")) {
                System.out.println("nowLocal does not have time zone information required for formatter: " + nowLocal);
            }
        }
        System.out.println(nowZoned.format(formatter));
        System.out.println(nowUTC.format(formatter));
    }

    public void outputDateTime(String pattern) {
        outputDateTime(DateTimeFormatter.ofPattern(pattern));
    }
}
