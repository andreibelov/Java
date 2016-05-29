package ru.belov.javase01.t03;

import java.text.DecimalFormat;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        writeYourCodeHere();
        //loop();
    }

    private static void writeYourCodeHere() {
        double[] m =new double[3], defoutls = new double[3];
        defoutls[0] = -1.6; defoutls[1] = 1.6; defoutls[2] = 0.1;
        System.out.println("Вам будет предложено последовательно ввести значение параметров a, b, h. \n");
        Scanner in = new Scanner(System.in);
        do
        {
            try {
                for (int i = 0; i < 3; i++) {
                    System.out.print("\nВведите значение параметра "+(i+1)+" ["+defoutls[i]+"]: ");
                    String s = in.nextLine();
                    if (null != s){m[i] = Double.parseDouble(s);}else{m[i] = defoutls[i];}
                    System.out.println("Вы ввели:"+m[i]);
                }
                break;
            }
            catch (Exception e)
            {
                System.out.println("Введены некорректные данные. Попробуйте еще раз.");
            }
        }
        while (true);
        doStuff2(m);
    }
    private static Boolean doStuff2(double[] d) {
        int i; double x,y;
        Function f = Function.getNewInstance(d[0],d[1],d[2]);
        DecimalFormat df = new DecimalFormat("+0.00000E00;-0");
        DecimalFormat dfi = new DecimalFormat("##00");
        String header = "\n\t╔====╦====================╦=====================╗\n";
        header +=         "\t║ ## ║ Значение аргумента ║   Значение функции  ║\n";
        String content = "";
        for (int index:f.getGrafic().Ind) {
            i = index;
            x = f.getGrafic().X[i];
            y = f.getGrafic().Y[i];
            content +=    "\t╟----╫---------=----------╫---------------------╢\n";
            content +=    "\t║ "+dfi.format((i+1))+" ║  x= "+df.format(x).replaceAll("E([^\\-]+)", "E+$1")+"  ║ "+"F(x)= "+df.format(y).replaceAll("E([^\\-]+)", "E+$1")+" ║\n";
        }
        String footer =   "\t╚====╩====================╩=====================╝";
        System.out.println(header+content+footer);
        return true;
    }
    private static Boolean doStuff(double[] d) {
        int i; double x,y;
        Function f = Function.getNewInstance(d[0],d[1],d[2]);
        DecimalFormat df = new DecimalFormat("+0.00000E00;-0");
        DecimalFormat dfi = new DecimalFormat("##00");
        String header = "\n\t╔════╦════════════════════╦════════════════════╗\n";
        header +=         "\t║ ## ║ Значение аргумента ║  Значение функции  ║\n";
        String content = "";
        for (int index:f.getGrafic().Ind) {
            i = index;
            x = f.getGrafic().X[i];
            y = f.getGrafic().Y[i];
            content +=    "\t╟────╫────────────────────╫────────────────────╢\n";
            content +=    "\t║ "+dfi.format((i+1))+" ║  x= "+df.format(x).replaceAll("E([^\\-]+)", "E+$1")+"  ║ "+"F(x)= "+df.format(y).replaceAll("E([^\\-]+)", "E+$1")+" ║\n";
        }
        String footer =   "\t╚════╩════════════════════╩════════════════════╝";
        System.out.println(header+content+footer);
        return true;
    }

    private static void loop() {
        do
        {
            Scanner in = new Scanner(System.in);
            try {
                System.out.println("Продолжить работу с программой? (y/n)[y]");
                String s = in.nextLine();
                if (s.equals("n")||s.equals("н")) break;
            } catch (Exception e) {
                System.out.println("Введены некорректные данные. Попробуйте еще раз.");
            }
            writeYourCodeHere();
        }
        while (true);
    }

}

class Function{
    //объявляем переменные
    private final double a,b,h;
    private Grafic grafic;
    //Блок конструторов
    private Function(double _a, double _b, double _h){
        double x=0,y;
        a = _a; b = _b; h = _h;
        grafic = new Grafic(a,b,h);
        int l = grafic.Ind.length;
        for (int i = 0; i < l; i++) {
            if(i==0){x=a;}
            if(i==l-1){x=b;}
            y = Math.tan(2*x)-3;
            grafic.Ind[i] = i;
            grafic.X[i] = x;
            grafic.Y[i] = y;
            x+=h;
        }
    }
    //Инстанциириемя с параметрами или без
    static Function getNewInstance(){ return new Function(-1.6,1.6,0.1); }
    static Function getNewInstance(double a, double b, double h){
        return new Function(a,b,h);
    }

    Grafic getGrafic() {
        return grafic;
    }
}

class Grafic {
    double[] X,Y;
    int[] Ind;

    Grafic(double a, double b, double h){
        int l = (int)((b-a)/h)+1;
        double remain = ((b-a)%h);
        if (remain!=0.0){l++;}
        X = new double[l];
        Y = new double[l];
        Ind = new int[l];
    }
}