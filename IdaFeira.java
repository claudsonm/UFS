import java.text.NumberFormat;
import java.util.Locale;
import java.util.Scanner;
  
public class Main {
  
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        // Number of visits to the fair
        int N = s.nextInt();
        // Amount needed in each visit
        float[] sum = new float[N];

        for (int i = 0; i < N; i++) {
            // Number of products available in the visit
            int M = s.nextInt();
            // List of names and prices
            Object[][] products = new Object[M][2];
            // Set the default value to 0
            sum[i] = 0;

            // Read the inputs to populate the list of products available
            for (int j = 0; j < M; j++) {
                String name = s.next();
                float price = Float.parseFloat(s.next().trim());
                products[j][0] = name;
                products[j][1] = price;
            }

            // Number of items to buy
            int P = s.nextInt();
            for (int j = 0; j < P; j++) {
                String name = s.next();
                int quant = s.nextInt();

                for (int k = 0; k < M; k++) {
                    if (products[k][0].equals(name)) {
                        sum[i] += (float) products[k][1] * quant;
                    }
                }
            }
        }

        NumberFormat nf = NumberFormat.getNumberInstance(Locale.ENGLISH);
        nf.setGroupingUsed(true);
        nf.setMaximumFractionDigits(2);
        nf.setMinimumFractionDigits(2);
        for (int i = 0; i < N; i++) {
            //System.out.printf("R$ %.2f\n", sum[i]);
            System.out.println("R$ " + nf.format(sum[i]));
        }  
    } 
}