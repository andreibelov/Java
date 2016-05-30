package ru.belov.javase01.t03;

import java.text.DecimalFormat;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        doStuff2(consoleReader());
        //loop();
    }

    private static double[] consoleReader() {
        double[] m =new double[3], defaults = new double[3];
        defaults[0] = -1.6; defaults[1] = 1.6; defaults[2] = 0.1;
        System.out.println("Вам будет предложено последовательно ввести значение параметров a, b, h. \n");
        Scanner in = new Scanner(System.in);
        do
        {
            try {
                for (int i = 0; i < 3; i++) {
                    System.out.print("\nВведите значение параметра "+(i+1)+" ["+defaults[i]+"]: ");
                    String s = in.nextLine();
                    if (null != s){m[i] = Double.parseDouble(s);}else{m[i] = defaults[i];}
                    System.out.println("Вы ввели:"+m[i]);
                }
                if(m[2]<=0 || m[1]<=m[0]){throw new Exception();}

                break;
            }
            catch (Exception e)
            {
                System.out.println("Введены некорректные данные. Попробуйте еще раз.");
            }
        }
        while (true);
        return m;
    }

    //Use this method in case non-monospace font
    private static Boolean doStuff2(double[] d) {
        double x,y;
        Function f = Function.getNewInstance(d[0],d[1],d[2]);
        DecimalFormat df = new DecimalFormat("+0.00000E00;-0");
        DecimalFormat dfi = new DecimalFormat("##00");
        String header = "\n\t╔====╦====================╦=====================╗\n";
        header +=         "\t║ ## ║ Значение аргумента ║   Значение функции  ║\n";
        String content = "";
        for (int i = 0; i < f.getGraphic().X.length; i++) {
            x = f.getGraphic().X[i];
            y = f.getGraphic().Y[i];
            content +=    "\t╟----╫--------------------╫---------------------╢\n";
            content +=    "\t║ "+dfi.format((i+1))+" ║  x= "+df.format(x).replaceAll("E([^\\-]+)", "E+$1")+"   ║ "+"F(x)= "+df.format(y).replaceAll("E([^\\-]+)", "E+$1")+"  ║\n";
        }
        String footer =   "\t╚====╩====================╩=====================╝";
        System.out.println(header+content+footer);
        return true;
    }
    //Use this method in case monospace font
    private static Boolean doStuff(double[] d) {
        double x,y;
        Function f = Function.getNewInstance(d[0],d[1],d[2]);
        DecimalFormat df = new DecimalFormat("+0.00000E00;-0");
        DecimalFormat dfi = new DecimalFormat("##00");
        String header = "\n\t╔════╦════════════════════╦════════════════════╗\n";
        header +=         "\t║ ## ║ Значение аргумента ║  Значение функции  ║\n";
        String content = "";
        for (int i = 0; i < f.getGraphic().X.length; i++) {
            x = f.getGraphic().X[i];
            y = f.getGraphic().Y[i];
            content +=    "\t╟────╫────────────────────╫────────────────────╢\n";
            content +=    "\t║ "+dfi.format((i+1))+" ║  x= "+df.format(x).replaceAll("E([^\\-]+)", "E+$1")+"   ║ "+"F(x)= "+df.format(y).replaceAll("E([^\\-]+)", "E+$1")+" ║\n";
        }
        String footer =   "\t╚════╩════════════════════╩════════════════════╝";
        System.out.println(header+content+footer);
        return true;
    }
    //Allows stay in touch with user =)
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
            consoleReader();
        }
        while (true);
    }

}

class Function{
    //объявляем переменные
    private Graphic graphic;
    //Блок конструторов
    private Function(final double a, final double b, final double h){
        double x=0,y;

        graphic = new Graphic(a,b,h);
        int l = graphic.X.length;
        for (int i = 0; i < l; i++) {
            if(i==0){x=a;}
            if(i==l-1){x=b;}
            y = Math.tan(2*x)-3;
            graphic.X[i] = x;
            graphic.Y[i] = y;
            x+=h;
        }
    }
    //Инстанциируемся с параметрами или без
    static Function getNewInstance(){ return new Function(-1.6,1.6,0.1); }
    static Function getNewInstance(double a, double b, double h){
        return new Function(a,b,h);
    }

    Graphic getGraphic() {
        return graphic;
    }
}

class Graphic {
    double[] X,Y;

    Graphic(double a, double b, double h){
        //расчет количества точек
        int l = (int) ((b-a)/h)+1;
        //Проверка, если все "кусочки" попадают в заданный интервал.
        double remain = ((b-a)%h);
        if (remain!=0.0){l++;}
        X = new double[l];
        Y = new double[l];
    }
}
