package com.geoffdoesstuff.java;

import com.geoffdoesstuff.java.utility.ADUtils;
import com.geoffdoesstuff.java.utility.CommandLine;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.OptionalInt;

public class Conversions {

    private static final String CMD_LINE_MS_TIMESTAMP = "ms-timestamp";
    private static final String CMD_LINE_ADD_DAYS = "add-days";

    /**
     * Input is number of 100-nanosecond intervals since 1 January 1601 UTC
     * Java base date is 1 January 1970
     */
    public static void main(String[] args) {
        System.out.println("Data Conversion utility");
        Map<String, String> cmdLineArguments = CommandLine.processArgs(args);
        System.out.println(CommandLine.getFormattedArguments(cmdLineArguments));

        if ((cmdLineArguments.size() == 1) || (cmdLineArguments.size() == 2)) {
            boolean msTimeStamp = CommandLine.checkArgs(cmdLineArguments, List.of(CMD_LINE_MS_TIMESTAMP), false);
            boolean msTimeStampWithDays = CommandLine.checkArgs(cmdLineArguments, List.of(CMD_LINE_MS_TIMESTAMP, CMD_LINE_ADD_DAYS), false);

            if (msTimeStamp || msTimeStampWithDays) {
                String msFileTimestamp = cmdLineArguments.get(CMD_LINE_MS_TIMESTAMP);
                long hundredNanos = Long.parseLong(msFileTimestamp);

                System.out.println("Input MS Timestamp (" + msFileTimestamp + ") converts as follows:");
                ZonedDateTime zonedDateTime = ADUtils.convertMsFileTimeStampToDate(hundredNanos);
                LocalDateTime localDateTime = zonedDateTime.toLocalDateTime();
                System.out.println(" - " + zonedDateTime.format(DateTimeFormatter.ofPattern("E d MMM yyyy HH:mm:ss.SSS (z)")));
                DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("E d MMM yyyy HH:mm:ss.SSS '(local)'");
                System.out.println(" - " + localDateTime.format(dateTimeFormatter));
                OptionalInt daysToAdd = getDaysToAdd(cmdLineArguments);
                if (daysToAdd.isPresent()) {
                    System.out.printf(" - %s with %s day(s) added%n",
                            localDateTime.plusDays(daysToAdd.getAsInt()).format(dateTimeFormatter),
                            cmdLineArguments.get(CMD_LINE_ADD_DAYS));
                    System.out.println("Done");
                }
            } else {
                displayIncorrectCommandLineError();
            }
        } else {
            displayIncorrectCommandLineError();
        }
    }

    private static void displayIncorrectCommandLineError() {
        System.err.println("ERROR: please specify a command line option");
        System.err.println("       --ms-timestamp=134423611700000000  -  convert number to human readable date/time");
        System.err.println("       --add-days=90  -  an optional argument to specify a number of days to add, can be negative");
    }

    private static OptionalInt getDaysToAdd(Map<String, String> cmdLineArgs) {
        OptionalInt result = OptionalInt.empty();
        String addDays = cmdLineArgs.get(CMD_LINE_ADD_DAYS);
        try {
            int days = Integer.parseInt(addDays);
            result = OptionalInt.of(days);
        } catch (NumberFormatException _) {}
        return result;
    }
}
