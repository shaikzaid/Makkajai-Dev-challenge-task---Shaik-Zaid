import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class salesTax {

    public static double tax(double price){
        double percent= 0.1;
        return price*percent;
    }
    public static double ImportedTax(double price){
        double percent = 0.05;

        return price*percent;
    }
    public static String roundupVal(double price){
        double roundedValue = Math.round(price * 20) / 20.0;
        return String.format("%.2f", roundedValue);
    }
    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);
        double Total_Sales=0;
         double Sales_Taxes = 0;
         int count=0;
        System.out.println("please Enter the quantiy of the product");
        int quantity = scn.nextInt();
        ArrayList<String> products = new ArrayList<>();

        while (quantity > 0) {
            System.out.println("If Imported press y else n");
            String ans = scn.next();
            System.out.println("Does It belong to book/food/medicine catogery if Yes then press y else n");
            String productcatogery= scn.next();
            System.out.println("Enter product full name");
            String product=scn.next();
            System.out.println("price");
            double price= scn.nextDouble();
            price=quantity*price;

            if(Objects.equals(ans, "y")){
                if(Objects.equals(productcatogery,"y")){
                    double ImportedETax=ImportedTax(price);
                    price += ImportedETax;
                    String formattedNumber = String.format("%.2f", price);
                    String statement= " "+quantity+" imported "+product+" at "+formattedNumber+" ";
                    products.add(statement);
                    Sales_Taxes += ImportedETax;
                }
                else {
                   double p1=ImportedTax(price);
                   double p2=tax(price);
                    price += p1+p2;
                   double priceItax= p1+p2;
                String formattedNumber = String.format("%.2f", price);
               String statement= " "+quantity+" imported "+product+" at "+formattedNumber+" ";
                products.add(statement);
                  Sales_Taxes += priceItax;}
            }
            else if(Objects.equals(ans, "n")){
                if(Objects.equals(productcatogery,"y")){
                    String statement= quantity+"  "+product+" at "+price;
                    products.add(statement);
                }
                else {
                    double priceStax=tax(price);
                    price +=priceStax;
                    String formattedNumber = String.format("%.2f", price);
                    String statement = quantity+" "+product+" at "+formattedNumber;
                    products.add(statement);
                    Sales_Taxes+=priceStax;
                }
            }
            Total_Sales+=price;
            count++;
            System.out.println("next product quantity if no product remaining press 0 ");
            quantity = scn.nextInt();
        }
        for(int i=0;i < count;i++){
            System.out.println(products.get(i));
        }

           String roundedValue2=roundupVal(Sales_Taxes);
             System.out.println("Sales Tax = "+roundedValue2);
        String roundedValue1=roundupVal(Total_Sales);
        System.out.println("Total = "+roundedValue1);
    }

}
