/*
 * Copyright (C) 2020 Bexon Pak.
 */
package com.dangerousteam.doomee;

import com.dangerousteam.doomee.util.FileUtil;

import java.io.File;
import java.util.List;
import java.util.regex.Pattern;

public class DoomeeCommand {
    // Version.
    public static final String VERSION = "1.0";

    /**
     * Main.
     */
    public static void main(String[] args) {
        String output = "";
        // If output is empty, output is set to the current directory.
        String file = "";
        boolean hasFile = false;
        String url = "";
        int task = 0;
        String outputPattern = "--output=.*";
        String filePattern = "--file=.*";
        String urlPattern = "--url=.*";
        String taskPattern = "--task=.*";
        String helpPattern = "--help";
        String versionPattern = "--version";

        String oPattern = "-o=.*";
        String fPattern = "-f=.*";
        String uPattern = "-u=.*";
        String tPattern = "-t=.*";
        String hPattern = "-h";
        String vPattern = "-v";

        // Get parameters.
        for (String arg : args) {
            if (Pattern.matches(outputPattern, arg.trim()) || Pattern.matches(oPattern, arg.trim())) {
                output = arg.trim().split("=")[1];
            }
            if (Pattern.matches(filePattern, arg.trim()) || Pattern.matches(fPattern, arg.trim())) {
                file = arg.trim().split("=")[1];
                hasFile = true;
            }
            if (Pattern.matches(urlPattern, arg.trim()) || Pattern.matches(uPattern, arg.trim())) {
                url = arg.trim().split("=")[1];
            }
            if (Pattern.matches(taskPattern, arg.trim()) || Pattern.matches(tPattern, arg.trim())) {
                task = Integer.parseInt(arg.trim().split("=")[1]);
            }
            if (Pattern.matches(helpPattern, arg.trim()) || Pattern.matches(hPattern, arg.trim())) {
                checkHelp();
            }
            if (Pattern.matches(versionPattern, arg.trim()) || Pattern.matches(vPattern, arg.trim())) {
                System.out.println("Doomee version 1.0 \n Copyright (C) 2020 Bexon Pak.");
                System.exit(0);
            }
        }

        // Only one can be selected between url and file.
        if (!url.trim().isEmpty() && !file.trim().isEmpty()) {
            System.err.println("🙅‍♂️ Only one can be selected between url and file.");
            checkHelp();
        }

        // Both url and file are empty.
        if (url.trim().isEmpty() && file.trim().isEmpty()) {
            System.err.println("🙅‍♂️ Please enter url or file parameter.");
            checkHelp();
        }

        // Set the number of simultaneous downloads.
        if (task == 0) {
            task = Doomee.task;
        }

        // Download from Url.
        if (!url.equals("")) {
            Doomee.load("./", new String[]{url}).tasks(task).start();
            System.exit(0);
        }

        // Download from file.
        if (hasFile) {
            File f = new File(file);
            List<String> urlList = FileUtil.txt2StringList(f);
            String[] urlArray = urlList.toArray(new String[0]);
            Doomee.load(output, urlArray).tasks(task).start();
            System.exit(0);
        }
    }

    /**
     * Help.
     */
    private static void checkHelp() {
        System.out.println("java -jar Doomee.jar [options...]\n" +
                "\n" +
                "--output=f          -o          Specify local output file.\n" +
                "--task=3            -t          Specify the number of simultaneous downloads.\n" +
                "--file=*.txt        -f          Get URL from txt file to download.\n" +
                "--url=URL           -u          Download from url.\n" +
                "--help              -h          This information.\n" +
                "--version           -v          Version information.\n" +
                "\n" +
                "Home page https://dangerous-team.com");
        System.exit(0);
    }
}
