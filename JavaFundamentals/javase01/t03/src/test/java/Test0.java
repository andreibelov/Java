/**
 * Created by john on 5/28/2016.
 */
public class Test0 {
    private static double x,y,a,b,h;
    public static void main(String[] args) {
        a = -1.6; b = 1.6; h=0.2;
        int dots = (int)((b-a)/h)+1;
        double l = ((b-a)%h);
        if (l!=0.0){dots++;}
        System.out.println(l);
        double[] X = new double[dots];
        x=a;
        int i=0;
        do {
            X[i]=x;
            System.out.println("X{"+i+"}="+X[i]);
            x+=h; i++;
        }
        while (x<b);
        X[i]=b;
        System.out.println("X{"+i+"}="+X[i]);
        System.out.println("length="+dots);
    }
}
