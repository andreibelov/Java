package com.john;
/**
 * Created by user on 5/22/2016.
 */
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

public class ChatClass extends HttpServlet {

    @Override
    //метод возвращает список всех обращений
    public void doGet(HttpServletRequest request, HttpServletResponse response) {
        response.setContentType("text/html;charset=utf-8");
        String path = "\\tpl\\index.tpl";
        print(response, path);

    }

    private void print(HttpServletResponse response, String path) {
        PrintWriter out;
        String filePath = getServletContext().getRealPath(path);
        try {
            out = response.getWriter();
            InputStream is = new FileInputStream(new File(filePath));
            Reader r = new InputStreamReader(is, "utf-8");
            BufferedReader br = new BufferedReader(r);
            while (true) {
                String s = br.readLine();
                if (s == null)
                    break;
                out.println(s);
            }
            is.close();
        } catch (IOException ignored) {
        }
    }

    @Override
    //отправляет строчку, обрабатывая запрос клиента
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {



    }
}