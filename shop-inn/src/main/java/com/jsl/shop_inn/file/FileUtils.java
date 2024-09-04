package com.jsl.shop_inn.file;

import com.jsl.shop_inn.exception.NoFileFoundInPath;
import com.jsl.shop_inn.models.FurnitureImageNames;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class FileUtils {

    public static List<byte[]> readFiles(List<FurnitureImageNames> fileList) {
        List<byte[]> files = new ArrayList<>();
        List<String> filenames = fileList.stream().map(FurnitureImageNames::getFilename).toList();
        try {
            for (String name: filenames) {
                Path filePath = new java.io.File(name).toPath();
                files.add(Files.readAllBytes(filePath));
            }
        } catch (IOException e) {
            throw new NoFileFoundInPath("No file found in the paths ::");
        }
        return files;
    }
}
