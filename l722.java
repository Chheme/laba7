import java.io.*;
import java.util.Scanner;
//package recordLudiFile;

class Ludi implements Serializable {
    String fam;
    String name;
    int god;
    String mes;
}

public class l722{
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Scanner sc = new Scanner(System.in, "cp1251");
        FileOutputStream fos = null;
        ObjectOutputStream oos = null;
        FileInputStream fin = null;
        ObjectInputStream oin = null;

        FileOutputStream fos2 = null;
        ObjectOutputStream oos2 = null;
        FileInputStream fin2 = null;
        ObjectInputStream oin2 = null;
        try {
            File f = new File("C:\\My\\l722inp.txt");
            if (f.exists()) f.delete();
            f.createNewFile();

            fos = new FileOutputStream(f);
            oos = new ObjectOutputStream(fos);

            System.out.print("Введите количество людей для записи в файл\n"
                    + "=> ");
            int kol = sc.nextInt();
            sc.nextLine();
            Ludi ludi;

            System.out.println("Введите информацию о людях: ");
            for (int i = 0; i < kol; i++) {
                System.out.print("Фамилия " + (i + 1) + " человека => ");
                ludi = new Ludi();
                ludi.fam = sc.nextLine();
                System.out.print("Его имя=> ");
                ludi.name = sc.nextLine();
                System.out.print("Год его рождения=> ");
                ludi.god = sc.nextInt();
                sc.nextLine();
                System.out.print("Месяц его рождения=> ");
                ludi.mes = sc.nextLine();


                oos.writeObject(ludi);


            }oos.flush();
            oos.close();

            fin = new FileInputStream(f);
            oin = new ObjectInputStream(fin);

            File fo = new File("C:\\My\\l722out.txt");
            if (fo.exists()) fo.delete();
            fo.createNewFile();

            fos2 = new FileOutputStream(fo);
            oos2 = new ObjectOutputStream(fos2);

            fin2 = new FileInputStream(fo);
            oin2 = new ObjectInputStream(fin2);
            int ludijan = 0;

            for (int i = 0; i < kol; i++) {
                ludi = (Ludi) oin.readObject();
                if (ludi.mes.equals("january")) {
                    oos2.writeObject(ludi);
                    ludijan++;
                }
            }

            System.out.println("Люди, родившееся в январе: ");
            if (ludijan != 0) {
                for (int i = 0; i < ludijan; i++) {
                    System.out.println((Ludi) oin2.readObject());
                }
            } else {
                System.out.println("Нету людей, родившихся в январе");
            }
        } catch (IOException ie) {
            ie.printStackTrace();
        } catch (ClassNotFoundException one) {
            one.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
oos2.flush();
oos2.close();
            fos2.flush();
            fos2.close();
            oin2.close();
            fin2.close();
        }
    }}