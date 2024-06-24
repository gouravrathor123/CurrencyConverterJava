import org.json.JSONObject;

import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Scanner;

public class CurrencyConverter2 {
    public static void main(String[] args) throws IOException {
        Boolean running = true;
        do {
            HashMap<Integer, String> currencyCodes = new HashMap<Integer, String>();

            //adding currency codes
            currencyCodes.put(1, "USD");
            currencyCodes.put(2, "CAD");
            currencyCodes.put(3, "EUR");
            currencyCodes.put(4, "HKD");
            currencyCodes.put(5, "INR");

            String fromCode, toCode;
            double amount;
            Integer from, to;
            Scanner sc = new Scanner(System.in);

            System.out.println("Welcome to the currency converter 2.0");

            System.out.println("Currency converting from ?");
            System.out.println("1:USD (US Dollar) \t 2:CAD (Canadian Dollar) \t 3:EUR (Euro) \t 4:HKD (HonkKong Dollar) 5: INR(Indian Rupee)");
            from = sc.nextInt();
            while (from < 1 || from > 5) {
                System.out.println("Please select a valid currency (1-5)");
                System.out.println("1:USD (US Dollar) \t 2:CAD (Canadian Dollar) \t 3:EUR (Euro) \t 4:HKD (HonkKong Dollar) 5: INR(Indian Rupee)");
                from = sc.nextInt();
            }
            fromCode = currencyCodes.get(from);
            System.out.println("Currency converting to ?");
            System.out.println("1:USD (US Dollar) \t 2:CAD (Canadian Dollar) \t 3:EUR (Euro) \t 4:HKD (HonkKong Dollar) 5: INR(Indian Rupee)");

            to = sc.nextInt();
            while (to < 1 || to > 5) {
                System.out.println("Please select a valid currency (1-5)");
                System.out.println("1:USD (US Dollar) \t 2:CAD (Canadian Dollar) \t 3:EUR (Euro) \t 4:HKD (HonkKong Dollar) 5: INR(Indian Rupee)");
                to = sc.nextInt();
            }
            toCode = currencyCodes.get(to);

            System.out.println("Amount to wish to convert ?");
            amount = sc.nextFloat();

            sendHttpGetRequest(fromCode, toCode, amount);
            System.out.println("Would you like to make another conversion?");
            System.out.println("1:Yes \t Any other integer: No");
            if(sc.nextInt() != 1){
                running = false;
            }
        }while (running);
        System.out.println("Thank you for using the currency converter!");
    }

    private static void sendHttpGetRequest(String fromCode, String toCode, double amount) throws IOException {
        DecimalFormat f = new DecimalFormat("00.00");
        String GET_URL = "https://open.er-api.com/v6/latest/" + fromCode;
        URL url = new URL(GET_URL);

        HttpsURLConnection httpsURLConnection = (HttpsURLConnection) url.openConnection();
        httpsURLConnection.setRequestMethod("GET");
        int responseCode = httpsURLConnection.getResponseCode();

        if(responseCode == HttpsURLConnection.HTTP_OK){
            BufferedReader in = new BufferedReader(new InputStreamReader(httpsURLConnection.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();

            while ((inputLine = in.readLine()) != null){
                response.append(inputLine);
            }in.close();

            JSONObject obj = new JSONObject(response.toString());
            Double toCodeRates = obj.getJSONObject("rates").getDouble(toCode);
            System.out.println(f.format(amount) + " " + fromCode + " = " + f.format(toCodeRates*amount) + " " + toCode);
        }
        else {
            System.out.println("Get request Failed !");
        }
    }
}
