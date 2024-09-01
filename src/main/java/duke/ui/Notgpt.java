package duke.ui;

import java.io.IOException;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.PrintWriter;


public class Notgpt {
    /**
     * Creates a line break between commands
     */
    public static void lnDiv() {
        System.out.println("___________________________________________________________________________");
    }

    public static void main(String[] args) {
        try {
            Scanner scanner = new Scanner(System.in);
            String logo = " _   _       _                 _\n"
                    + "| \\ | | ___ | |_    ____ ____ | |_ \n"
                    + "|  \\| |/ _ \\  __|  / _` | '_ \\| __|\n"
                    + "| |\\  | (_) | |_  | (_| | |_) | |_ \n"
                    + "|_| \\_|\\___/ \\__|  \\__, | .__/ \\__|\n"
                    + "                   |___/|_|\n";
            System.out.println(logo + "\n" + "hi, I'm Not-gpt,\ndo you really need me to do sth for you?");
            lnDiv();
            Parser.parse(scanner);
        } catch (Exception e) {
            e.printStackTrace();
            try (FileWriter fw = new FileWriter("error.log", true);
                 PrintWriter pw = new PrintWriter(fw)) {
                e.printStackTrace(pw);
            } catch (IOException ioe) {
                ioe.printStackTrace();
            }
            System.out.println("An error occurred. Press Enter to exit.");
            new Scanner(System.in).nextLine();
        }
    }
}
