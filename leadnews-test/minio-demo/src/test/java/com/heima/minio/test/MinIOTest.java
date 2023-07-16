package com.heima.minio.test;

import com.heima.file.service.FileStorageService;
import com.heima.minio.MinioApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * @author AdRainty
 * @version V1.0.0
 * @since 2023/7/16 23:08
 */

@RunWith(SpringRunner.class)
@SpringBootTest(classes = MinioApplication.class)
public class MinIOTest {

    @Autowired
    private FileStorageService fileStorageService;

    @Test
    public void test() throws IOException {
        FileInputStream fileInputStream = new FileInputStream("d:\\basic.html");
        String path = fileStorageService.uploadHtmlFile("", "basic.html", fileInputStream);
        System.out.println(path);
    }

}
