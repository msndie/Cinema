package edu.school21.cinema.utils;

import org.springframework.web.multipart.MultipartFile;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.UUID;

public class Utils {
    public static boolean createFile(MultipartFile file, String path, String extension, UUID uuid) {
        try {
            byte[] barr = file.getBytes();
            BufferedOutputStream stream = new BufferedOutputStream((new FileOutputStream(path + "/" + uuid + "." + extension)));
            stream.write(barr);
            stream.flush();
            stream.close();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
