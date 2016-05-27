package com.company;

public class Kot {
    public static void main(String[] args) {
        int y = 131;
        //y = .....ab

        int b = y % 10;
        int a = y % 100 / 10;

        if (b == 1 && a != 1)
            System.out.println(y + " kot");
        else if (b >= 2 && b <= 4 && a != 1)
            System.out.println(y + " kota");
        else
            System.out.println(y + " kotov");
    }
}
