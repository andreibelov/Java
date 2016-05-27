package com.company;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Arrays;


public class Main {

    public static void main(String[] args) {
	// My code is written here
        double[] x = new double[21];
        double R=10;
        double[][] X = new double[21][21];
        double[][] Y = new double[21][21];
        double[][] Z = new double[21][21];
       // System.out.println("Array length: "+x.length);

        int l=x.length;
        double a = -R;
        double[] theta = new double[21];
        for (int i=0; i<l; i++) {
            x[i]=a;
            a++;
            theta[i] = i*Math.PI/(l-1);
        }

        //System.out.println("x Array: "+ Arrays.toString(theta));

        for (int i = 0; i < l; i++) {
            System.arraycopy(x,0,X[i],0,21);
        }

        for (int i = 0; i < l; i++) {
            for (int j = 0; j < l; j++) {
                Y[i][j]=x[i]*(-1);
            }
        }


        for (int i = 0; i < l; i++) {
            for (int j = 0; j < l; j++) {
                Z[i][j] = round(R*Math.sin(theta[i]), 5);
            }
        }


        for (int i = 0; i < l; i++) {
            //System.out.println("X"+i+" Array: "+ Arrays.toString(X[i]));
            //System.out.println("Y"+i+" Array: "+ Arrays.toString(Y[i]));
            System.out.println("Z"+i+" Array: "+ Arrays.toString(Z[i]));
        }

            //System.out.println("X"+" Array: "+ Arrays.deepToString(X));

    }

    private static double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        BigDecimal bd = new BigDecimal(value);
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }
}
