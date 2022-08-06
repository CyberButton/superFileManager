// Importing File Class
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Scanner;
import java.nio.file.StandardCopyOption;

import static java.nio.file.Files.move;


public class Main {
    // main directory where sorting needs to be done
    //static File mainDir = new File("C:\\Users\\nurto\\OneDrive\\Рабочий стол\\test");
    public static void main(String[] args) throws IOException {

        //...
        Scanner scan = new Scanner(System.in);
        String s = scan.nextLine();

        // main directory where sorting needs to be done
        File mainDir = new File(s);

        // list of all files in main
        File[] data = mainDir.listFiles();

        // creating directories for sorted files (inside the main directory)
        File pdfDir = new File(mainDir, "PDFs");
        File imagesDir = new File(mainDir, "Images");
        File docsDir = new File(mainDir, "Docs");
        File musicDir = new File(mainDir, "Music");
        File otherDir = new File(mainDir, "Other");

        pdfDir.mkdir();
        imagesDir.mkdir();
        docsDir.mkdir();
        musicDir.mkdir();
        otherDir.mkdir();

        // sorting
        Sorter(data, mainDir);

    }

    public static void Sorter(File[] contents, File mainDir) throws IOException {

        for(File item : contents) {
            if(item.isDirectory()) {

                //use recursion to sort child directories
                Sorter(item.listFiles(), mainDir);
                item.delete();
            }
            else if (item.isFile()) {

                //use file name to identify extension
                if(".pdf".equals(item.getName().substring(item.getName().lastIndexOf(".")))) {
                    //item.renameTo(new File("C:\\Users\\nurto\\OneDrive\\Рабочий стол\\test\\pdfDir"));

                    //move it to the specific folder
                    move(item.toPath(), Path.of(mainDir.getPath()+"\\PDFs\\"+item.getName()), StandardCopyOption.REPLACE_EXISTING);
                }
                else if (".jpg".equals(item.getName().substring(item.getName().lastIndexOf("."))) || ".png".equals(item.getName().substring(item.getName().lastIndexOf(".")))) {
                    move(item.toPath(), Path.of(mainDir+"\\Images\\"+item.getName()), StandardCopyOption.REPLACE_EXISTING);
                }
                else if (".doc".equals(item.getName().substring(item.getName().lastIndexOf("."))) || ".docx".equals(item.getName().substring(item.getName().lastIndexOf(".")))) {
                    move(item.toPath(), Path.of(mainDir+"\\Docs\\"+item.getName()), StandardCopyOption.REPLACE_EXISTING);
                }
                else if (".mp3".equals(item.getName().substring(item.getName().lastIndexOf("."))) || ".ogg".equals(item.getName().substring(item.getName().lastIndexOf(".")))) {
                    move(item.toPath(), Path.of(mainDir+"\\Music\\"+item.getName()), StandardCopyOption.REPLACE_EXISTING);
                }
                else {
                    move(item.toPath(), Path.of(mainDir+"\\Other\\"+item.getName()), StandardCopyOption.REPLACE_EXISTING);
                }
            }
        }
    }

}