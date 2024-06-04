package Main;
import java.util.Scanner;

public class ScannerClass {
    private final Scanner sc;

    /**
     * repeatedly get user input until a legal integer response is received
     * @param max the greatest number allowed - will only accept integers between 0 and max
     * @return their response
     */
    public int scanInt(int max) {
        while (true) {
            try {
                int response = Integer.parseInt(sc.nextLine());
                if (response <= max) {
                    return response;
                } else {
                    System.out.println("input a number in range");
                }
//            } catch (InputMismatchException e) {
            } catch (NumberFormatException e) {
                System.out.println("input a number in range");
            }
        }
    }

    /**
     * repeatedly get user input until a legal String response is received
     * @param allowedStrings which strings are considered legal responses. not case sensitive
     * @return their response
     */
    public String scanStr(String[] allowedStrings) {
        while (true) {
            String response = sc.nextLine();
            for (String s : allowedStrings) {
                if (response.equalsIgnoreCase(s)) {
                    return s;
                }
            }
        }
    }

    /**
     * Take the user's input of any string
     * @return their resposne
     */
    public String scanStr() {
        return sc.nextLine();
    }



    public ScannerClass() {
        sc = new Scanner(System.in);
    }

}
