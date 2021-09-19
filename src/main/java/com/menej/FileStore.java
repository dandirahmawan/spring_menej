package com.menej;

import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Base64;

@Service
public class FileStore {

    String rootFolder;
    String uploadDir;

    public FileStore(){
        String userDir = new File(System.getProperty("user.dir")).getAbsolutePath();
        String rootDir = userDir.substring(0, userDir.indexOf(File.separator)+1);

        this.rootFolder = rootDir+"/menej";
        File pathRoot = new File(rootFolder);
        if(!pathRoot.exists()) pathRoot.mkdir();

        /*folder upload*/
        this.uploadDir = this.rootFolder+"/upload";
        File pathUpload = new File(this.uploadDir);
        if(!pathUpload.exists()) {
            pathUpload.mkdir();
        }
    }

    public String getPathUrl(){
        return this.uploadDir;
    }

    public String folderUserModule(Integer userId, Integer moduleId){
        File folderUser = new File(this.uploadDir+"/usr_"+userId);
        if(!folderUser.exists()) folderUser.mkdir();

        File folderModule = new File(folderUser+"/mdl_"+moduleId);
        if(!folderModule.exists()) folderModule.mkdir();

        return this.uploadDir+"/usr_"+userId+"/mdl_"+moduleId;
    }

    public File storeFileBase64(String fileDir, String base64, Integer ort, String fileName){
        String[] sourceData = base64.split(",");
        BufferedImage bufferedImage = null;
        byte[] imageByte;

        try {
            imageByte = Base64.getDecoder().decode(sourceData[1]);
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(imageByte);
            bufferedImage = ImageIO.read(byteArrayInputStream);
            byteArrayInputStream.close();

            Utils utils = new Utils();
            BufferedImage rotatedImage = bufferedImage;
            if(ort != 0 && ort != 1) rotatedImage = utils.rotateImage(ort, bufferedImage);

            File file = new File(fileDir+"/"+fileName);
            ImageIO.write(rotatedImage, "jpg", file);
            return file;
        }catch (Exception e){
            e.printStackTrace();
        }

        return null;
    }

    public File storeFile(MultipartFile file, String fileDir){
        try{
            File fileUpload = new File(fileDir+"/"+file.getOriginalFilename());
            InputStream inputStream = file.getInputStream();
            OutputStream outputStream = new FileOutputStream(fileDir+"/"+file.getOriginalFilename());

            int readBytes = 0;
            int i = 0;
            byte[] buffer = new byte[1024 * 50];
            while ((readBytes = inputStream.read(buffer, 0, 1024 * 50)) != -1) {
                outputStream.write(buffer, 0, readBytes);
            }

            inputStream.close();
            outputStream.close();
            return fileUpload;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public String getUploadDir() {
        return uploadDir;
    }
}
