package ru.belov.javase01.t04;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * Created by user on 5/30/2016.
 *
 */

public class Main {

    public static void main(String[] args) {
        Sequence sequence;
        int n = 9;
        sequence = Sequence.getNewInstance();

        int i=1;
        for (double d:sequence.generated_set) {
            System.out.println("a("+i+") = "+d); i++;
        }

        System.out.println("\nMaximum element in set is: "+sequence.getMaxInSet());
    }
}
class Sequence {
    Set<Double> generated_set;
    private List<Double> sub_set;
    private Sequence(int n,double rangeMin,double rangeMax){
        Random rnd = new Random();
        this.generated_set = new LinkedHashSet<Double>();

        while (this.generated_set.size() < n)
        {
            this.generated_set.add(rangeMin + (rangeMax - rangeMin) * rnd.nextDouble());
        }
    }

    static Sequence getNewInstance(){
        int n=0;
        System.out.print("Введите размер последовательности: n=");
        BufferedReader console = new BufferedReader(new InputStreamReader(System.in));
        try {
            n = Integer.parseInt(console.readLine());

        }catch (IOException ex) {
            System.out.println("Smth went wrong!");
        }
        return new Sequence(n,-50,50);
    }

    double getMaxInSet(){
        getSorted();
        return this.sub_set.get(this.sub_set.size()-1);
    }

    //private void createSubSet(){this.sub_set = new ArrayList<>();}

    private void getSorted(){
        this.sub_set = new ArrayList<>(this.generated_set);
        Collections.sort(this.sub_set);
    }

}