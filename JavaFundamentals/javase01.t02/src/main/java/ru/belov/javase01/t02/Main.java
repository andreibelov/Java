package ru.belov.javase01.t02;

import java.text.DecimalFormat;
import java.util.ArrayList;

/**
 * Created by john on 5/27/2016.
 * Класс позволяет найти наименьший номер элемента последовательности,
 * для которого выполняется условие M, а также
 * Вывести на экран этот номер и все элементы последовательности/
 *
 * a{n}=1.0/(n+1)^2
 *
 * M: a{n}<eps;
 */

public class Main {
    public static void main(String[] args) {

        Sequence sq = Sequence.getNewInstance(0.006);

        ArrayList<Double> A = sq.getA();

        System.out.println("Наименьший номер элемента последовательности "+A.size()+"\n");
        System.out.println("Список элементов последовательности с 1 по n:");
        for (double d:A) {
            DecimalFormat df = new DecimalFormat("#.#####");
            System.out.println(df.format(d));
        }

    }

}

class Sequence {

    private double eps;
    private ArrayList<Double> A;

    private Sequence(double eps){
        this.eps = eps;
        this.A = new ArrayList<Double>();
    }
    //Инстанциируемся со входным параметром
    static Sequence getNewInstance(double e){
        return new Sequence(e);
    }
    //Инстанциируемся без параметров
    static Sequence getNewInstance(){
        return new Sequence(0.1);
    }
    //Заполняем динамический массив элементами, удовлетворяющими условию задачи.
    private void filler(){
        double a = 1;
        int i=0;
        while (a > eps){
            a = 1.0/Math.pow((i+1.0), 2.0);
            this.A.add(a);
            i++;
        }
    }

   ArrayList<Double> getA() {
        filler();
        return this.A;
    }
}