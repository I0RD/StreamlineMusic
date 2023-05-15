package com.app.StreamlineMusic.utils;

import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;

public class FileResourcesUtils {

    public String getFileFromResource(String fileName) throws URISyntaxException{

        ClassLoader classLoader = getClass().getClassLoader();
        URL resource=classLoader.getResource(fileName);
        if(resource==null){
            throw new IllegalArgumentException("file not found! "+fileName);
        }else{
            return new String(resource.toString());
        }
    }
}
