package com.geoffdoesstuff.java.demo;

import com.geoffdoesstuff.java.utility.DemoUtilities;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HexFormat;

/**
 * Some handy examples of working with the file system, so files, directories and paths. It is worth noting that NIO is
 * the recommended approach as it is better for high performance, compared to the old IO.
 */
public class FileSystem {

    /**
     * This is here to suppress Javadoc complaining about not commenting the default constructor
     */
    private FileSystem() {
    }

    /**
     * Main method, for independent running of demo, or use via MainDemoApp menu
     * @param args command line arguments
     */
    public static void main(String[] args) {
        Path targetLocation = Path.of(".\\build\\output");
        if (Files.isDirectory(targetLocation)) {
            System.out.println("Target directory exists (relative): " + targetLocation);
            System.out.println("                        (absolute): " + targetLocation.toAbsolutePath());
            System.out.println("                      (normalised): " + targetLocation.toAbsolutePath().normalize());
        } else {
            System.out.println("Target directory missing, creating " + targetLocation);
            try {
                Path created = Files.createDirectories(targetLocation);
                System.out.println("Created: " + created);
            } catch (IOException e) {
                throw new RuntimeException("Could not create directory", e);
            }
        }

        DemoUtilities.outputTitle("Write to file");
        writeToFiles();
        DemoUtilities.outputTitle("Read binary file");
        readBinaryFile();
    }

    /**
     * The recommended way to write to files is with java.nio as follows. It should be made clear that all four of
     * these files are the same, with the same ANSI encoding.
     */
    private static void writeToFiles() {
        try {
            try (BufferedWriter bufferedWriter = Files.newBufferedWriter(Path.of(".\\build\\output\\temp.txt"), StandardCharsets.ISO_8859_1)) {
                bufferedWriter.write("Hello World");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        // Write raw bytes to file
        byte[] byteArrayData = "Hello World".getBytes(StandardCharsets.UTF_8);
        try {
            Path savedFile = Files.write(Path.of(".\\build\\output\\temp2.txt"), byteArrayData);
            System.out.println(savedFile);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        // When no OpenOptions are specified you get the following: StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING, StandardOpenOption.WRITE
        try {
            Files.writeString(Path.of(".\\build\\output\\temp3.txt"), "Hello World", StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        // output file using classic IO, this is NOT recommended
        try (FileOutputStream outputStream = new FileOutputStream(".\\build\\output\\temp4.txt")) {
            outputStream.write(byteArrayData);
        } catch (FileNotFoundException e) {
            System.out.println("New file not found...");
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Read a file, in binary mode, getting a byte array and then outputting it.
     */
    private static void readBinaryFile() {
        try {
            try (InputStream inputStream = Files.newInputStream(Path.of(".\\build\\output\\temp2.txt"))) {
                byte[] byteArray = inputStream.readAllBytes();
                System.out.println(HexFormat.of().withUpperCase().formatHex(byteArray));
                System.out.println(HexFormat.ofDelimiter(":").withUpperCase().withPrefix("<").withSuffix(">").formatHex(byteArray));
                System.out.println(new String(byteArray, StandardCharsets.UTF_8));
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
