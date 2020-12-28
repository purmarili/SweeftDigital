package CurrencyConverter;

import org.jsoup.Jsoup;
import org.w3c.dom.CharacterData;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;


public class ParseInput {

    private Map<String, Pair<String, Double>> currencies = new HashMap<>();

    public ParseInput(String feed) throws ParserConfigurationException, IOException, SAXException {
        URL feedUrl = new URL(feed);

        DocumentBuilderFactory docBuilder = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = docBuilder.newDocumentBuilder();
        Document doc = builder.parse(feedUrl.openStream());

        doc.getDocumentElement().normalize();

        NodeList nList = doc.getElementsByTagName("description");
        Element line = (Element) nList.item(1);
        String data = getCharacterDataFromElement(line);

        data = "<html>" + data + "</html>";
        org.jsoup.nodes.Document dc1 = Jsoup.parse(data);

        org.jsoup.select.Elements crs = dc1.getElementsByTag("td");
        String curr = "";
        String currGeo = "";
        for (int i = 0; i < crs.size(); i++) {
            org.jsoup.nodes.Element elem = crs.get(i);
            if (i % 5 == 0) curr = elem.text();
            if (i % 5 == 1) currGeo = elem.text();
            if (i % 5 == 2) currencies.put(curr, new Pair<String, Double>(currGeo, Double.valueOf(elem.text())));
        }
    }

    private String getCharacterDataFromElement(Element f) {

        NodeList list = f.getChildNodes();
        String data;

        for (int index = 0; index < list.getLength(); index++) {
            if (list.item(index) instanceof CharacterData) {
                CharacterData child = (CharacterData) list.item(index);
                data = child.getData();

                if (data != null && data.trim().length() > 0)
                    return child.getData();
            }
        }
        return "";
    }

    public Map<String, Pair<String, Double>> getCurrencies() {
        return currencies;
    }

    public void printCurrencies() {
        for (Map.Entry<String, Pair<String, Double>> entry : currencies.entrySet()) {
            String cur = entry.getKey();
            String curGeo = entry.getValue().getKey();
            double val = entry.getValue().getVal();
            System.out.println(cur + ": " + curGeo + ": " + val);
        }
    }

    public Double exchangeRate(String from, String to) {
        DecimalFormat df = new DecimalFormat("#.####");
        boolean foundFrom = false, foundTo = false;
        Double fromRate = 0d, toRate = 0d;
        String fromGeo = "", toGeo = ""; // am shemtxvevashi ar viyeneb, magram rom dagvchirdes amomaqvs
        if (from.equals("GEL")) {
            fromRate = 1d;
            foundFrom = true;
            fromGeo = "1 ქართული ლარი";
        }
        if (to.equals("GEL")) {
            toRate = 1d;
            foundTo = true;
            toGeo = "1 ქართული ლარი";
        }
        if (foundFrom && foundTo) return Double.valueOf(df.format(1));
        for (Map.Entry<String, Pair<String, Double>> entry : currencies.entrySet()) {
            String cur = entry.getKey();
            if (!foundFrom && cur.equals(from)) {
                fromRate = entry.getValue().getVal();
                fromGeo = entry.getValue().getKey();
            }
            if (!foundTo && cur.equals(to)) {
                toRate = entry.getValue().getVal();
                toGeo = entry.getValue().getKey();
            }
        }
        return Double.valueOf(df.format(fromRate / toRate));
    }

    public boolean checkValidity(String from, String to) {
        boolean foundFrom = false, foundTo = false;
        if (from.equals("GEL")) foundFrom = true;
        if (to.equals("GEL")) foundTo = true;
        if (foundFrom && foundTo) return true;
        for (Map.Entry<String, Pair<String, Double>> entry : currencies.entrySet()) {
            String cur = entry.getKey();
            if (!foundFrom && cur.equals(from)) foundFrom = true;
            if (!foundTo && cur.equals(to)) foundTo = true;
        }
        return foundFrom && foundTo;
    }
}