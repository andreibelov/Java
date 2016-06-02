package com.company;
import java.io.IOException;
import java.util.Arrays;
import java.util.Random;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
	// My code is here
        int dim=10;
        while(dim>0) {
            dim = getDim();
            double[] unSorted = getDoubles(dim);
            double[] sorted = doBubble(unSorted);
            for (double s:sorted) {
                System.out.print(round(s,5)+" ");
            }
            System.out.println();
            //System.out.println(Arrays.toString(sorted));
        }
    }

    private static int getDim() {
        int dim;
        System.out.println("Enter dim:");
        Scanner in = new Scanner(System.in);
        dim = in.nextInt();
        System.out.print("You entered ");
        System.out.println(dim);
        return dim;
    }

    private static double[] getDoubles(int l) {
        double[] anArray = new double[l];
        Random rand = new Random();
        for(int j=0;j<l;j++)
        {
            anArray[j] = rand.nextDouble();
        }
        return anArray;
    }

    private static double[] doBubble(double[] var) {
        double tmp;
        boolean flag = true;
        int cnt=0;
        while(flag){
            flag = false;

            for (int i = 0; i < var.length-1; i++) {
                if (var[i]>var[i+1]) {
                    tmp = var[i];
                    var[i]=var[i+1];
                    var[i+1]=tmp;
                    cnt++;
                    flag=true;
                }
            }
        }
        return var;
    }

    private static double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();
        BigDecimal bd = new BigDecimal(value);
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }
}
