package com.geoffdoesstuff.java;

import com.geoffdoesstuff.java.utility.ADUtils;
import com.geoffdoesstuff.java.utility.CommandLine;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

public class Conversions {

    private static final String CMD_LINE_MS_TIMESTAMP = "ms-timestamp";

    /**
     * Input is number of 100-nanosecond intervals since 1 January 1601 UTC
     * Java base date is 1 January 1970
     */
    public static void main(String[] args) {
        System.out.println("Data Conversion utility");
        Map<String, String> cmdLineArguments = CommandLine.processArgs(args);
        System.out.println(CommandLine.getFormattedArguments(cmdLineArguments));

        if (cmdLineArguments.size() == 1) {
            boolean msTimeStamp = CommandLine.checkArgs(cmdLineArguments, List.of(CMD_LINE_MS_TIMESTAMP), false);

            if (msTimeStamp) {
                String msFileTimestamp = cmdLineArguments.get(CMD_LINE_MS_TIMESTAMP);
                long hundredNanos = Long.parseLong(msFileTimestamp);

                System.out.println("Input MS Timestamp (" + msFileTimestamp + ") converts as follows:");
                ZonedDateTime zonedDateTime = ADUtils.convertMsFileTimeStampToDate(hundredNanos);
                System.out.println(" - " + zonedDateTime);
                LocalDateTime localDateTime = zonedDateTime.toLocalDateTime();
                DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("E d MMM yyyy HH:mm:ss.SSS (z)");
                System.out.println(" - " + zonedDateTime.format(dateTimeFormatter));
                dateTimeFormatter = DateTimeFormatter.ofPattern("E d MMM yyyy HH:mm:ss.SSS");
                System.out.println(" - " + localDateTime.format(dateTimeFormatter) + " (local)");
            } else {
                displayIncorrectCommandLineError();
            }
        } else {
            displayIncorrectCommandLineError();
        }
    }

    private static void displayIncorrectCommandLineError() {
        System.err.println("ERROR: please specify a command line option");
        System.err.println("       --ms-timestamp=134415835700000000  -  convert number to human readable date/time");
    }
}
