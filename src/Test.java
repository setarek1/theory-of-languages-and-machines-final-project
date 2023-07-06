import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Test {
    public static void main(String[] args) throws CloneNotSupportedException {
        Scanner sc = new Scanner(System.in);
        String regex1 = sc.next();
        String regex2 = sc.next();
        CheckRegex check = new CheckRegex(regex1, regex2);


    }
}
