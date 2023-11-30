import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Scanner;

public class Main {

    static String clientMessage;

    String path = "File";

    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);

        System.out.println("Write text message");
        clientMessage = scan.next();
        System.out.println("Select the type to print: " +
                "\n1)To the standard output stream " +
                "\n2)Into the standard error output stream " +
                "\n3)To the “*.txt” format file");

        int choseOpinion = scan.nextInt();

        switch (choseOpinion) {
            case 1:
                printMessage(clientMessage, new Printable() {
                    @Override
                    public void print(String msg) {
                        System.out.println(msg);
                    }
                });
                break;
            case 2:
                printMessage(clientMessage, System.err::println);
                break;
            case 3:
                printMessage(clientMessage, printInFileImplementsPrintable());
                break;

        }

    }

    static void printMessage(String msg, Printable printable) {
        printable.print(msg);
    }

    static Printable printInFileImplementsPrintable() {
        return msg -> {
            byte[] bytes = clientMessage.getBytes();
            try (OutputStream outputStream = new FileOutputStream("File")) {
                outputStream.write(bytes);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        };
    }

}