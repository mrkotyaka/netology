package streams;

import java.io.File;

public class Main {
    public static void main(String[] args) {
        File dir = new File("my_dir");
//        File file1 = new File("my_dir", "file1.txt");
//        File file2 = new File(dir, "file2.txt");

        if (dir.mkdir()) {
            System.out.println("Directory created");
        } else  {
            System.out.println("Directory not created");
        }

        File new_my_dir =  new File("new_my_dir");
        if (dir.renameTo(new_my_dir)) {
            System.out.println("File renamed");
        } else   {
            System.out.println("File not renamed");
        }
    }
}
