package ru.belov.javase01.t06;

import org.jetbrains.annotations.Contract;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by user on 5/30/2016.
 * Спроектировать и разработать классы Запись в блокноте и Блокнот (записи блокнота хранятся в массиве).
 * Реализовать методы: Добавить запись, Удалить запись, Редактировать запись, Посмотреть все записи.
 * Написать для данного кода javadoc-документацию.
 *
 */

public class Main {

    public static void main(String[] args) {
        loop(NotePad.getNewInstance());
    }
    private static void loop(NotePad np){
        String input="",tmp = "";
        boolean flag=true;
        do {
            System.out.println("Введите \"help\" для вызова справки или \"exit\" для выхода из программы");
            Scanner in = new Scanner(System.in);
            try {
                input = in.nextLine();
            }catch (NoSuchElementException exception){
                //Exception caught
            }

            if(input==null){
                System.out.println("Something went wrong");
                continue;
            }
            switch (input.toLowerCase()) {
                case "exit":
                    flag=false;
                    break;
                case "add new":
                    if(np.addNewEntry(readText(in)))
                        System.out.println("Запись успешно создана");
                    break;
                case "show entries":
                    for (NotePad.Note entry : np.getLastEntries()) {
                        System.out.println(entry.toString());
                    }
                    break;
                case "listmode":
                    listMode(np,in);
                    break;
                case "help":
                    System.out.println("Используемые команды: add new, show entries, listmode, exit");
                case "":
                case "\n":
                default:
                    break;
            }
        }
        while (flag);
    }
    private static void listMode(NotePad np, Scanner in){
        String input = "";
        NotePad.Note selectedNote=null;
        Integer selctdId=null;
        boolean flag = true;
        do {
            System.out.print("Введите id записи для редактирования, list для вывода списка или exit: ");
            try {
                input = in.nextLine();
            }catch (NoSuchElementException exception){
                //Exception caught
                continue;
            }
            switch (input.toLowerCase()) {
                case "exit":
                    flag=false;
                    break;
                case "list":
                    System.out.println(np.getList());
                    break;
                case "delete":
                    //delete selected;
                    if(selctdId==null){System.out.print("нет выбранных элементов"); break;}
                    assert selectedNote != null;
                    selectedNote.deleteText();
                    break;
                case "edit":
                    //edit selected
                    if(selctdId==null){System.out.print("нет выбранных элементов"); break;}
                    edit(in,selctdId,np);
                    break;
                case "":
                case "\n":
                default:
                    try {
                        selctdId = Integer.parseInt(input);
                        try {
                            if(selctdId<np.range[0]||selctdId>np.range[1]){
                                throw new IndexOutOfBoundsException();
                            }
                            selectedNote = np.getEntry(selctdId);
                            System.out.println(selectedNote.toString());
                        } catch (IndexOutOfBoundsException e) {
                            selctdId=null;
                            System.out.print("Введенный Вами данные не верны, по пробуйте еще раз.");
                            break;
                        }
                    } catch (NumberFormatException e) {
                        e.printStackTrace();
                    }


                    break;
            }
        }while (flag);
    }
    @Contract("_, null, _ -> fail;")
    private static void edit(Scanner in, int id, NotePad np){
        String input ="";
        System.out.print("Введите id записи для редактирования: ");
        String separator = System.lineSeparator();
        np.getEntry(id).editText(readText(in));
        System.out.print("Изменения успешно сохранены!");
    }

    private static String readText(Scanner in){
        System.out.print("Введите текст (для окончания ввода наберите \"^Z\"): ");
        String separator = System.lineSeparator();
        StringBuilder sb = new StringBuilder(300);
        String s="";
        boolean flag = true;
        do {
            try {
                s = in.nextLine();
                if (s.matches("(.*)\\^Z")){s=s.replaceAll("\\^Z",""); flag = false;}
                sb.append(s);
            } catch (NoSuchElementException exception) { break; }
            if (flag) sb.append(separator);
        } while (flag);
        return sb.toString();
    }
}

class NotePad{
    protected int[] range = new int[2];
    private ArrayList<Note> entries;
    private static AtomicInteger ai;
    private NotePad(){
        ai = new AtomicInteger(-1);
        this.range[0]=ai.get();
        entries = new ArrayList<>();
    }
    static NotePad getNewInstance(){
        return new NotePad();
    }
    boolean addNewEntry(String text){
        this.entries.add(new Note(ai.incrementAndGet(),text));
        this.range[1]=ai.get();
        return true;
    }
    void editNote(Note note,String text){
        note.editText(text);
    }

    Note getLastEntry(){
        try {
            return this.entries.get(entries.size() - 1);
        } catch (IndexOutOfBoundsException ex) {return null;}
    }
    List<Note> getLastEntries(int quantity){
        List<Note> list = new ArrayList<>();
        int size_of_list = this.entries.size();
        //Проверка на размер блокнота
        if (size_of_list==0){throw new IndexOutOfBoundsException();}
        else if (quantity > size_of_list){quantity = size_of_list;}
        // задание последнего ID
        int lastID = this.entries.size()-1;
        for (int i = 0; i < quantity; i++) {
            list.add(this.entries.get(lastID-(quantity-1)+i));
        }
        return list;

    }
    List<Note> getAllEntries(){return this.getLastEntries(entries.size());}
    List<Note> getLastEntries(){return this.getLastEntries(30);}
    Note getEntry(int id) {return this.entries.get(id);}
    List getList(){
        List<String> list = new ArrayList<>();
        if (this.entries.size()==0){
            list.add("В блокноте пока нет записей");
        } else {
            for (Note note : this.entries) {
                list.add(note.toList());
            }
        }
        return list;
    }

    class Note{
        private String text;
        private Date timestamp;
        final int HASH_CODE;
        final int ID;
        private boolean deleted = false;
        private boolean edited = false;
        Note(int id, String text){
            this.text = text;
            this.timestamp = new Date();
            this.ID = id;
            this.HASH_CODE = text.hashCode()+(id*37);
        }

        void editText(String text){
            this.timestamp = new Date();
            this.text = text;
            this.edited = true;
        }

        void deleteText(){
            this.text = "[DELETED]";
            this.timestamp = new Date();
            this.deleted = true;
        }

        @Override
        public String toString(){
            String tmp;
            String separator = System.lineSeparator();
            StringBuilder sb = new StringBuilder(this.text.length()+50);
            tmp = "[@"+this.timestamp.toString()+"]";
            sb.append(tmp);
            if(deleted){
                sb.append(separator);
                tmp = "\tDELETED";
                sb.append(tmp);
                return sb.toString();
            }
            sb.append(separator);
            tmp = "\t"+this.text;
            sb.append(tmp);
            sb.append(separator);
            return sb.toString();
        }
        String toList(){ return "[@"+this.timestamp.toString()+"]"+" [id="+this.ID+"]"; }
    }
}
