import java.util.Scanner;

public class CurrencyConverter {

    public static void main(String[] args) {
        Scanner sc= new Scanner(System.in);
        System.out.println("Choose the currency of your country");
        System.out.println("Type 1 for Rupee 2 for Dollar 3 for Yen");
        int countryCurrency = sc.nextInt();
        System.out.println("Please enter the amount you would like to convert");
        Double amount = sc.nextDouble();
        Double amountInRupee = convertToRupee(countryCurrency,amount);
        System.out.println("Please select the country you would like to convert");
        System.out.println("Type 1 for Rupee 2 for Dollar 3 for Yen");
        int desiredCountryCurrency = sc.nextInt();
        Double convertedAmount = convertRupeeToGivenCountry(amountInRupee,desiredCountryCurrency);
        System.out.println("Your converted amount is " + convertedAmount);
    }

    private static Double convertToRupee(int countryCurrency,Double amount){
        switch (countryCurrency){
            case 1:
                return amount;
            case 2:
                return (amount * 83.56);
            case 3:
                return (amount * 0.52);
        }
        return 1.0;
    }

    private static Double convertRupeeToGivenCountry(Double amountInRupee,int desiredCountryCurrency){
        switch (desiredCountryCurrency){
            case 1:
                return amountInRupee;
                case 2:
                    return (amountInRupee * 0.012);
                    case 3:
                        return (amountInRupee * 1.91);
        }
        return 1.0;
    }
}