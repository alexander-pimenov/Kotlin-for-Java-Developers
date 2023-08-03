package _utils;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class ProcessInfo {
    public static void main(String[] args) {
        extractedInfoAboutProcess();
    }

    private static void extractedInfoAboutProcess() {
        try {
            String process;
            Process p = Runtime.getRuntime().exec("ps -few");
            BufferedReader input = new BufferedReader(new InputStreamReader(p.getInputStream()));
            while ((process = input.readLine()) != null) {
                System.out.println(process);

            }
            input.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
