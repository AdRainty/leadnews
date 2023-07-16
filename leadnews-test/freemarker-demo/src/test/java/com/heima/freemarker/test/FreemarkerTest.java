package com.heima.freemarker.test;

import com.heima.freemarker.FreemarkerDemoApplication;
import com.heima.freemarker.entity.Student;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author AdRainty
 * @version V1.0.0
 * @since 2023/7/16 22:20
 */

@RunWith(SpringRunner.class)
@SpringBootTest(classes = FreemarkerDemoApplication.class)
public class FreemarkerTest {

    @Autowired
    private Configuration configuration;

    @Test
    public void test() throws IOException, TemplateException {
        Template template = configuration.getTemplate("01-basic.ftl");
        template.process(getData(), new FileWriter("d:/basic.html"));
    }

    private Map<String, Object> getData() {
        Map<String, Object> data = new HashMap<>();
        Student student = new Student("小红", 18, new Date(), 200f);
        data.put("stu", student);
        data.put("name", "test");
        return data;
    }

}
