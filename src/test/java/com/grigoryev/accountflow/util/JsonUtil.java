package com.grigoryev.accountflow.util;

import lombok.SneakyThrows;
import lombok.experimental.UtilityClass;
import org.apache.commons.io.FileUtils;
import org.springframework.core.io.ClassPathResource;

import java.io.File;

@UtilityClass
public class JsonUtil {

    @SneakyThrows
    public String getJson(String pathname) {
        File file = new ClassPathResource(pathname).getFile();
        return FileUtils.readFileToString(file, "UTF-8");
    }

}
