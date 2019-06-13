import java.io.*;
import java.util.*;

public class DirTree {
    //Вся полезная работа делается внутри методов main и list
    //Метод main создает цикл, внутри которого он запрашивает у пользователя
    // путь к исходному каталогу и отображает расположенные в дереве каталогов файлы.
    // Ввод пути к исходному каталогу выполняется методом getKbdString
    public static void main(String args[]) {
        String s;

        while (true) {
            System.out.println("BBOD: ");

            //После запуска наша программа запрашивает на консоли путь к каталогу.
            // Далее она обходит дерево каталогов, расположенное в указанном каталоге.
            // При этом на консоль выводятся полные пути к обнаруженным файлам:

            // Ввод пути к исходному каталогу выполняется методом getKbdString
            s = new String(getKbdString());
            if (s.equals("Нажмите Esc для выхода"))
                break;
            //Цикл завершается, когда пользователь вводит строку "quit".
            //метод main проверяет, существует ли введенный путь и указывает ли он на каталог
            File f = new File(s);
            if (!f.exists()) {
                //При ошибке пользователю предлагается повторить ввод пути к каталогу.
                System.out.println("\nОшибка пути к каталогу: " + s);
                continue;
            }

            if (!f.isDirectory()) {
                System.out.println(
                        "\nНет директории: " + s);
                continue;
            }
            //Если же путь введен правильно, метод main вызывает метод list
            //Этот метод распечатывает на консоли содержимое дерева каталогов.
            list(s);
        }
    }

    //В качестве единственного параметра метод list получает путь к каталогу szDir
    static void list(String szDir) {
        int i;
        //Для каталога szDir метод list создает объект класса File
        File f = new File(szDir);
        //Далее метод заполняет массив sDirList списком имен содержимого каталога,
        // путь к которому задан параметром szDir
        String[] sDirList = f.list();
        //Обработка содержимого каталога выполняется в цикле
        //Здесь мы создаем для каждого обнаруженного в каталоге файла
        // или каталога объект класса File.
        // Если мы обнаружили файл, мы выводим на консоль полный путь к нему,
        // комбинируя его из пути к каталогу szDir и имени каталога.
        // При обнаружении каталога метод list вызывает сам себя,
        // передавая себе через параметр полный путь к обнаруженному каталогу.
        // Это и есть рекурсивный вызов метода list.
        for (i = 0; i < sDirList.length; i++) {
            File f1 = new File(szDir +
                    File.separator + sDirList[i]);

            if (f1.isFile())
                System.out.println(szDir +
                        File.separator + sDirList[i]);
            else {
                list(szDir +
                        File.separator + sDirList[i]);
            }
        }
    }

    static public String getKbdString() {
        byte bKbd[] = new byte[256];
        int iCnt = 0;
        String szStr = "";

        try {
            iCnt = System.in.read(bKbd);
        } catch (Exception ex) {
            System.out.println(ex.toString());
        }

        szStr = new String(bKbd, 0, iCnt);
        szStr = szStr.trim();
        return szStr;
    }
}
