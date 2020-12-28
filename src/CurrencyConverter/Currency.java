package CurrencyConverter;

import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.Scanner;

public class Currency {

    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) throws IOException, SAXException, ParserConfigurationException {

        String rss = "http://www.nbg.ge/rss.php";
        ParseInput currencyParser = new ParseInput(rss);
        String from = "NOT_VALID", to = "NOT_VALID";
        currencyParser.printCurrencies();

        System.out.println("\nშემოიყვანეთ ორი ვალუტა, რათა მიიღოთ გაცვლის კურსი: ");
        System.out.println("ახსენეთ 'quit' რათა აპლიკაციამ შეწყვიტოს მუშაობა.");

        while (true) {
            from = sc.next();
            if (from.contains("quit")) break;
            to = sc.next();
            if (to.contains("quit")) break;
            if (currencyParser.checkValidity(from, to)) {
                System.out.println(currencyParser.exchangeRate(from, to));
                System.out.println("\nშემოიყვანეთ ორი ვალუტა, რათა მიიღოთ გაცვლის კურსი: ");
                continue;
            }
            System.out.println("შემოიყვანეთ ვალიდური ვალუტები: ");
        }

        System.out.println("\nგმადლობთ ჩვენი აპლიკაციით სარგებლობისთვის.");
    }
}
