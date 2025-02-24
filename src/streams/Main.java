package streams;

import java.io.File;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        File dir = new File("my_dir");
        File file1 = new File("my_dir", "file1.txt");

        if (dir.mkdir()) {
            System.out.println("Directory created");
        } else  {
            System.out.println("Directory not created");
        }

        try {
            if (dir.exists()){
                if (file1.createNewFile()){
                    System.out.println("File created");
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


        File new_my_dir =  new File("new_my_dir");
        File file2 = new File(new_my_dir, "file2.txt");
        if (dir.renameTo(new_my_dir)) {
            System.out.println("File renamed");
        } else   {
            System.out.println("File not renamed");
        }

        try {
            if (new_my_dir.exists()){
                if (file2.createNewFile()){
                    System.out.println("File created");
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
