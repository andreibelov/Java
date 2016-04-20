package com.company;

import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
	// My code is written here
        int[] x = new int[21];
        int a=-10;
        int[][] X = new int[21][21];
        int[][] Y = new int[21][21];
        int[][] Z = new int[21][21];
        System.out.println("Array length: "+x.length);

        int l=x.length;

        for (int i=0; i<l; i++) {
            x[i]=a;
            a++;
        }
        System.out.println("x Array: "+ Arrays.toString(x));

        for (int i = 0; i < l; i++) {
            for (int j = 0; j < l; j++) {
                X[i][j]=x[j];
            }
        }

        for (int i = 0; i < l; i++) {
            for (int j = 0; j < l; j++) {
                Y[i][j]=x[i]*(-1);
            }
        }

        int z;

        z =

        for (int i = 0; i < l; i++) {
            System.out.println("X"+i+" Array: "+ Arrays.toString(Y[i]));
        }

    }

}
