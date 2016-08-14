package ru.belov.javase01.t05;
import java.util.Arrays;

/**
 * Created by user on 5/30/2016.
 * Внимание! Для запуска программы воспользуйтесь CMD файлом run.cmd
 * В среде IDE (IDEA в данном случае), метод System.console().readLine() не работает!!!
 */

public class Main {

    public static void main(String[] args) {

        Matrix matrix = Matrix.getNewInstance();
        for (int[] row:matrix.M) System.out.println(Arrays.toString(row));

    }

}

class Matrix{
    int[][] M; ;    // Declares a 2-D array

    private Matrix(int size) {
        // Баловство с диагональной матрицей (школьный курс)
        M = new int[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++){
                //Здесь использовано выражение 0^0, что по результатам исполнения кода дает единицу.
                //Хотя чисто с теоретической точки зрения, оно бессмысленно. http://ru.wikipedia.org/wiki/0_(число)
                M[i][j] = (int) Math.pow(0, Math.abs(i - j));
            }
            M[i][(size-(i+1))] = 1;
        }
    }

    static Matrix getNewInstance(){
        System.out.println("Введите размер матрицы");
        int size = Integer.parseInt(System.console().readLine());
        return new Matrix(size);
    }
    static Matrix getNewInstance(int size){return new Matrix(size);}
}