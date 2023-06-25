import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class salesTax {
    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);
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
                if(Objects.equals(product, "book") ||Objects.equals(product,"food")||Objects.equals(product,"medicine") ){
                    String statement= quantity+" "+product+"at"+price;
                    products.add(statement);
                }
                else {
                    double priceStax=tax(price);
                    String statement = quantity+" "+product+"at "+price;
                    products.add(statement);
                    Sales_Taxes+=priceStax;
                }
            }

            count++;
            System.out.println("next product quantity if no product remaining press 0 ");
            quantity = scn.nextInt();
        }
        for(int i=0;i < count;i++){
            System.out.println(products.get(i));
        }
        double roundedValue = Math.round(Sales_Taxes * 20) / 20.0;
        String Sale = String.format("%.2f", roundedValue);
        System.out.println("Sales Tax= "+Sale+" ");
    }

    public static double tax(double price){
        double percent= 0.1;
        return price*percent;
    }
    public static double ImportedTax(double price){
        double percent = 0.05;
        return price*percent;
    }
}
