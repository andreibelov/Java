/**
 * Created by john on 5/28/2016.
 */
public class Test1 {
    private static double a,b,h;
    public static void main(String[] args) {
        a = -1.6; b = 1.6; h = 0.2;
        double remain = ((b-a)%h);
        int l = (int)((b-a)/h)+1;
        System.out.println(remain);
        System.out.println(l);
        double x = h*(l-1);
        System.out.println(x-1.6);
    }
}
