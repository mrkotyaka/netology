package streams;

import java.io.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

public class Main {
    public static void main(String[] args) {

        File dir = new File("my_dir");
        File new_my_dir = new File("new_my_dir");
        File file1 = new File("my_dir", "file1.txt");

        if (new_my_dir.delete()) {
            System.out.println("Directory new_my_dir Deleted");
        }

        if (dir.mkdir()) {
            System.out.println("Directory created");
        } else {
            System.out.println("Directory not created");
        }

        try {
            if (dir.exists()) {
                if (file1.createNewFile()) {
                    System.out.println("File created");
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


        File file2 = new File(new_my_dir, "file2.txt");
        if (dir.renameTo(new_my_dir)) {
            System.out.println("File renamed");
        } else {
            System.out.println("File not renamed");
        }

        try {
            if (new_my_dir.exists()) {
                if (file2.createNewFile()) {
                    System.out.println("File created");
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


        String text = "\nHello world!";
        //writing
        try (FileOutputStream fos = new FileOutputStream("new_my_dir/file1.txt", true)) {
            byte[] bytes = text.getBytes();
            fos.write(bytes);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        //reading
        try (FileInputStream fis = new FileInputStream("new_my_dir/file1.txt")) {
            try {
                int i;
                while ((i = fis.read()) != -1) {
                    System.out.print((char) i);
                }
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        System.out.println("Done File streams \nStart Buffered streams\n");

        byte[] buffer = text.getBytes();
        try (ByteArrayInputStream bais = new ByteArrayInputStream(buffer);
             BufferedInputStream bis = new BufferedInputStream(bais)) {
            int c;
            while ((c = bis.read()) != -1) {

                System.out.print((char) c);
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        String textBuf = "Buffered world!\n";
        byte[] buffer2 = textBuf.getBytes();
// создаем выходной байтовый поток
// и передаем его в выходной буферизированный поток
        try (FileOutputStream out = new FileOutputStream("new_my_dir/file2.txt");
             BufferedOutputStream bos = new BufferedOutputStream(out)) {
// производим запись от 0 до последнего байта из массива
            bos.write(buffer2, 0, buffer2.length);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }


        System.out.println("Done Buffered streams \nStart FileWriter\n");

        String text3 = "writer World";
        try (FileWriter writer = new FileWriter("new_my_dir/file2.txt", true)) {
// запись всей строки
            writer.write(text3);
// запись по символам
            writer.append('\n');
            writer.append('!');
// дозаписываем и очищаем буфер
            writer.flush();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }


        try (FileReader reader = new FileReader("new_my_dir/file2.txt")) {
// читаем посимвольно
            int c;
            while ((c = reader.read()) != -1) {
                System.out.print((char) c);
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }

        System.out.println("Done FileWriter \nStart BufferedWriter\n");

        try (BufferedWriter bw = new BufferedWriter(new FileWriter("new_my_dir/file3.txt"))) {
            String text4 = "BufferedWriter World!";
            bw.write(text4);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }

        try (BufferedReader br = new BufferedReader(new FileReader("new_my_dir/file3.txt"))) {
//чтение построчно
            String s;
            while ((s = br.readLine()) != null) {
                System.out.println(s);
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }


        System.out.println("Done BufferedWriter \nStart Zip streams\n");

        try (ZipOutputStream zos = new ZipOutputStream(new FileOutputStream("new_my_dir/zos_zipers.zip"));
             FileInputStream fis = new FileInputStream("new_my_dir/file_zip.txt")) {
            ZipEntry entry = new ZipEntry("new_my_dir/packed_file_zip.txt");
            zos.putNextEntry(entry);
            byte[] b = new byte[fis.available()];
            if(fis.read(b) != -1) {
                System.out.println("File read");
            }
            zos.write(b);
            zos.closeEntry();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        try (ZipInputStream zin = new ZipInputStream(new
                FileInputStream("new_my_dir/output.zip"))) {
            ZipEntry entry;
            String name;
            while ((entry = zin.getNextEntry()) != null) {
                name = entry.getName(); // получим название файла
// распаковка
                FileOutputStream fout = new FileOutputStream(name);
                for (int c = zin.read(); c != -1; c = zin.read()) {
                    fout.write(c);
                }
                fout.flush();
                zin.closeEntry();
                fout.close();
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

        System.out.println("Done Zip streams \nStart GameProgress\n");

        GameProgress gameProgress = new GameProgress(94, 10, 2, 254.32);

        try (FileOutputStream fos = new FileOutputStream("new_my_dir/save.dat");
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(gameProgress);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }

        try (FileInputStream fis = new FileInputStream("new_my_dir/save.dat");
        ObjectInputStream ois = new ObjectInputStream(fis)){
            gameProgress = (GameProgress) ois.readObject();
        }  catch (IOException | ClassNotFoundException ex) {
            System.out.println(ex.getMessage());
        }
        System.out.println(gameProgress.toString());

        System.out.println("Done GameProgress\n");
    }

}

class GameProgress implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private int health;
    private int weapons;
    private int lvl;
    private double distance;

    public GameProgress(int health, int weapons, int lvl, double distance) {
        this.health = health;
        this.weapons = weapons;
        this.lvl = lvl;
        this.distance = distance;
    }

    @Override
    public String toString() {
        return "GameProgress{" +
                "health=" + health +
                ", weapons=" + weapons +
                ", lvl=" + lvl +
                ", distance=" + distance +
                '}';
    }
}
