import java.io.*;
import java.util.*;

public class Cookie {
    public static void main(String[] args) {
        // Make sure the command line arguments are input in the proper format
        if (args.length != 3) {
            System.out.println("Please enter proper number of command line arguments");
        } else if (!args[1].equals("-d")) {
            System.out.println("Incorrect second argument");
        }

        // Store information about the input arguments
        String fileName = args[0];
        String date = args[2];
        int dateLen = date.length();

        // Try to open the input file
        Scanner sc;
        try {
            sc = new Scanner(new File(fileName));
            sc.useDelimiter("\n");
        } catch (FileNotFoundException e) {
            System.out.println("Error " + e.getMessage());
            System.out.println("Please input a correct file path");
            e.printStackTrace();
            return;
        }

        // Keep track of the frequencies of each cookie and the frequency of the most frequent
        // cookie
        Map<String, Integer> cookieFrequency = new HashMap<>();
        int maxFreq = 0;

        while (sc.hasNext()) {
            // Get the cookie and the date
            String[] line = sc.next().split(",");
            String cookie = line[0];
            String cookieDate = line[1];

            // Check to see if the cookie happened on the input date, and if so, add it to the
            // frequency map
            if (cookieDate.length() > dateLen && cookieDate.substring(0, dateLen).equals(date)) {
                cookieFrequency.put(cookie, cookieFrequency.getOrDefault(cookie, 0) + 1);
                maxFreq = Math.max(maxFreq, cookieFrequency.get(cookie));
            }

            // Since the cookies are sorted in most recent order, once we advance past our date, we
            // can stop scanning
            if (cookieDate.compareTo(date) < 0) {
                sc.close();
                break;
            }
        }

        for (String cookie : cookieFrequency.keySet()) {
            if (cookieFrequency.get(cookie) == maxFreq) {
                System.out.println(cookie);
            }
        }

        return;
    }
}
