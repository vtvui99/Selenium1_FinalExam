package Common;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DataGeneration {
    public static String generateString(){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyMMddHHmmssSSS");
        LocalDateTime now = LocalDateTime.now();
        return dtf.format(now);
    }

    public static String generateRandomEmail(String email){
        String[] parts = email.split("@");
        String part1 = parts[0];
        String part2 = parts[1];
        return part1 + "-" + generateString() + "@" + part2;
    }
}
