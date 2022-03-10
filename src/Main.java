import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Main {

    public static final String PATH = "D:\\Java\\Games\\savegames\\";


    public static void main(String[] args) {
        List<String> gameList = new ArrayList<>();

        String fileName1 = "save1.dat";
        GameProgress gameProgress1 = new GameProgress(94, 10, 2, 254.32);
        saveGame(PATH + fileName1, gameProgress1);
        gameList.add(PATH + fileName1);

        String fileName2 = "save2.dat";
        GameProgress gameProgress2 = new GameProgress(87, 15, 3, 280.43);
        saveGame(PATH + fileName2, gameProgress2);
        gameList.add(PATH + fileName2);

        String fileName3 = "save3.dat";
        GameProgress gameProgress3 = new GameProgress(105, 20, 4, 315.12);
        saveGame(PATH + fileName3, gameProgress3);
        gameList.add(PATH + fileName3);

        zipFiles(PATH + "zipFile.zip", gameList);

        //File deleteFiles = new File(PATH);
        for (String file : gameList) {
            File deleteFile = new File(file);
            deleteFile.delete();
        }

    }

    public static void saveGame(String filePath, GameProgress gameProgress) {
        try (FileOutputStream fos = new FileOutputStream(filePath);
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            // запишем экземпляр класса в файл
            oos.writeObject(gameProgress);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    public static void zipFiles(String filePath, List<String> filesToZip) {
        try (ZipOutputStream zout = new ZipOutputStream(new FileOutputStream(filePath))) {
             for (String item : filesToZip) {
                 try (FileInputStream fis = new FileInputStream(item)) {
                     ZipEntry entry = new ZipEntry(item);
                     zout.putNextEntry(entry);
                     byte[] buffer = new byte[fis.available()];
                     fis.read(buffer);
                     zout.write(buffer);
                     zout.closeEntry();
                 } catch (Exception ex) {
                     System.out.println(ex.getMessage());
                 }
             }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
}
