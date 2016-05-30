package ru.belov.javase01.t02;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by john on 5/27/2016.
 * Класс позволяет найти наименьший номер элемента последовательности,
 * для которого выполняется условие M, а также
 * вывести на экран этот номер и все элементы последовательности/
 *
 * a{n}=1.0/(n+1)^2
 *
 * M: a{n}<eps;
 */

public class Main {

    public static void main(String[] args) {

        doStuff();
        do
        {
            Scanner in = new Scanner(System.in);
            try {
                System.out.println("Продолжить работу с программой? (y/n)[y]");
                String s = in.nextLine();
                if (s.equals("n")) break;
            } catch (Exception e) {
                System.out.println("Введены некорректные данные. Попробуйте еще раз.");
            }
            doStuff();
        }
        while (true);

    }

    private static void doStuff() {
        Double epsilon;
        while (true){
            epsilon = getEps();
            if( epsilon<0 || epsilon.isNaN()) {
                System.out.println("Введено неверное значение, попробуйте еще раз");
            } else {break;}
        }
        Sequence sq = Sequence.getNewInstance(epsilon);

        ArrayList<Double> A = sq.getA();
        if (A.size()==1){System.out.println("Для любого n из {a(n)} условие M верно");}
        System.out.println("Наименьший номер элемента последовательности, удовлетворяющий условию М - n="+A.size()+"\n");
        System.out.println("Список элементов последовательности с 1 по n:");
        int j=1;
        for (double d:A) {
            DecimalFormat df = new DecimalFormat("#.#####");
            System.out.println("a{"+Integer.toString(j)+"}="+df.format(d));
            j++;
        }
    }

    private static Double getEps() {
        Double epsilon;
        Scanner in = new Scanner(System.in);
        do
        {
            try {
                System.out.println("Введите значение Epsilon > 0:");
                String s = in.nextLine();
                epsilon = Double.parseDouble(s);
                break;
            }
            catch (Exception e)
            {
                System.out.println("Введены некорректные данные. Попробуйте еще раз.");
            }
        }
        while (true);
        System.out.print("Вы ввели:");
        System.out.println(" "+epsilon+"\n");
        return epsilon;
    }
}

class Sequence {

    private double eps;
    private ArrayList<Double> A;
    private Sequence(double eps){
        this.eps = eps;
        this.A = new ArrayList<Double>();
        filler();
    }
    //Инстанциируемся с одним входным параметром
    static Sequence getNewInstance(double e){
        return new Sequence(e);
    }
    //Инстанциируемся без параметров
    static Sequence getNewInstance(){
        return new Sequence(0.24);
    }
    //Заполняем динамический массив элементами, удовлетворяющими условию задачи.
    private void filler(){
        double a; int i=1;

        //Так как для рассчета последовательности используется формула n-ного члена,
        // то итерацию надо начинать с 1-го номера, а не с 0, так как не существует нулевого
        // члена последовательности:

        do {
            a = 1.0/Math.pow((i+1.0), 2.0);
            this.A.add(a); i++;
        }
        while (a>eps);
    }
    /*Метод возвращает ArrayList заполненный первыми n элементами последовательности.*/
    ArrayList<Double> getA() {
        return this.A;
    }
}