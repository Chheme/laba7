import java.io.*;
import java.util.Scanner;
//Вариант8

public class l721 {
    public static void main(String[] args) {
        try {
            File folder = new File("C:\\My");
            if (!folder.exists())
                folder.mkdir();

            File f1 = new File("C:\\My\\l721inp.txt");
                if (f1.exists()) f1.delete();
            f1.createNewFile();
            
            RandomAccessFile rf = new RandomAccessFile(f1,"rw");
            long fSize = rf.length();
            Scanner sc = new Scanner(System.in, "cp1251");
            System.out.print("Введите количество людей для записи в файл\n"
                    + "=> ");
            int kol = sc.nextInt();
            sc.nextLine();

            String fam, im, mes;
            int god;

            for (int i = 0; i < kol; i++) {
                System.out.print("Введите фамилию " + (i + 1) + " человека => ");
                fam = sc.next();
                rf.seek(rf.length());
                rf.writeUTF(fam);
                for (int j = 0; j < 20 - fam.length(); j++)
                    rf.writeByte(1);
                System.out.print("Введите его имя => ");
                im = sc.next();
                rf.writeUTF(im);
                for (int j = 0; j < 20 - im.length(); j++)
                    rf.writeByte(1);
                System.out.print("Введите год его рождения => ");
                god = sc.nextInt();
                sc.nextLine();
                rf.writeInt(god);
                System.out.print("Введите месяц его рождения => ");
                mes = sc.next();
                rf.writeUTF(mes);
                for (int j = 0; j < 20 - mes.length(); j++)
                    rf.writeByte(1);
            }
            rf.close();

            rf = new RandomAccessFile(f1, "r");
            rf.seek(0);
            
            File f2 = new File("C:\\My\\l721out.txt");
            if (f2.exists()) f2.delete();
            f2.createNewFile();
            RandomAccessFile rfo = new RandomAccessFile(f2,"rw");
            long f2Size = rf.length();
            String y="january";

            System.out.println("Информация о людях, родившихся в январе");
            System.out.println("Фамилия \t\t Имя \t\t Год рождения \t\t Месяц рождения");

            for (int i = 0; i < kol; i++) {
                fam = rf.readUTF();
                for (int j = 0; j < 20 - fam.length(); j++)
                    rf.readByte();

                im = rf.readUTF();
                for (int j = 0; j < 20 - im.length(); j++)
                    rf.readByte();

                god = rf.readInt();

                mes = rf.readUTF();
                for (int j = 0; j < 20 - mes.length(); j++)
                    rf.readByte();

if (mes.equals(y)){
                rfo.seek(rfo.length());
                rfo.writeUTF(fam);
                for (int j = 0; j < 20 - fam.length(); j++)
                    rfo.writeByte(1);
                rfo.writeUTF(im);
                for (int j = 0; j < 20 - im.length(); j++)
                    rfo.writeByte(1);
                rfo.writeInt(god);
                rfo.writeUTF(mes);
                for (int j = 0; j < 20 - mes.length(); j++)
                    rfo.writeByte(1);
    System.out.println(fam + "\t\t\t" + im + "\t\t\t" + god + "\t\t\t" + mes);
                }}
        rfo.close();
        } catch (IOException e) {
            System.out.println("End of file " + e);
        } }
}
