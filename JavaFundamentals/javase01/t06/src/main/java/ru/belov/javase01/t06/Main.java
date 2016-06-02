package ru.belov.javase01.t06;

import java.io.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by user on 5/30/2016.
 * Спроектировать и разработать классы Запись в блокноте и Блокнот (записи блокнота хранятся в массиве).
 * Реализовать методы: Добавить запись, Удалить запись, Редактировать запись, Посмотреть все записи.
 * Написать для данного кода javadoc-документацию.
 */
public class Main {

    public static void main(String[] args) {

        loop(NotePad.getNewInstance());

    }

    private static void loop(NotePad np){
        String in;
        do {
            System.out.println("Введите сообщение или exit для выхода из программы");
            BufferedReader console = new BufferedReader( new InputStreamReader(System.in));
            try {
                in = console.readLine();
            } catch (UnsupportedEncodingException utf){
                in ="";
            } catch (IOException ex){
                in ="";
            }

            if (in==null){ System.out.println("UnsupportedEncodingException"); in ="Ошибка чтения с консоли! ссчч";} else {

                if (in.equals("exit")) {
                    break;
                }
                if (in.equals("show entries")) {
                    for (NotePad.Note entry : np.getLastEntries()) {
                        System.out.println(entry.toString());
                    }
                    continue;
                }
            }

            np.addNewEntry(in);
            System.out.println("Запись успешно создана");
        }
        while (true);

    }

}

class NotePad{
    private ArrayList<Note> entries;
    private NotePad(){
        entries = new ArrayList<>();
        entries.add(new Note("Notepad created"));
    }

    static NotePad getNewInstance(){
        return new NotePad();
    }

    boolean addNewEntry(String text){
        this.entries.add(new Note(text));
        return true;
    }

    Note getLastEntry(){
        try {
            return this.entries.get(entries.size() - 1);
        } catch (IndexOutOfBoundsException ex) {return null;}
    }
    List<Note> getLastEntries(int quantity){
        List<Note> list = new ArrayList<>();
        int size_of_list = this.entries.size();
        // Проверка на размер блокнота
        if (quantity > size_of_list && size_of_list!=0){quantity = size_of_list;}
        int lastID = this.entries.size()-1;
        for (int i = 0; i < quantity; i++) {
            list.add(this.entries.get(lastID-(quantity-1)+i));
        }
        return list;

    }

    List<Note> getLastEntries(){
        return this.getLastEntries(30);
    }

    Note getEntry(int id){

        return this.entries.get(id);
    }

    class Note{
        private MessageDigest messageDigest;
        private String text;
        private Date timestamp;
        String HASH_CODE;
        public int id;

        Note(String text){
            this.text = text;
            this.timestamp = new Date();
            try {
                this.HASH_CODE = MessageDigest.getInstance("MD5").toString();
            } catch (NoSuchAlgorithmException ex){

            }

        }

        public String toString(){
            String tmp;
            String separator = System.lineSeparator();
            StringBuilder sb = new StringBuilder(this.text.length()+50);
            tmp = "@["+this.timestamp.toString()+"] ";
            sb.append(tmp);
            sb.append(separator);
            tmp = "\t"+this.text;
            sb.append(tmp);
            sb.append(separator);
            return sb.toString();
        }

    }
}
