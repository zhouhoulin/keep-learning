package com.java.concurrent.pattern;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @ProjectName keep-learning
 * @Title FileSaver
 * @Description
 * @Author zhouhoulin
 * @Date 2022-10-09
 */
@Slf4j(topic = "logger.FileSaver")
public class FileSaver {

    private String fileName;

    private ObjectMapper mapper;
    private boolean changed;

    public FileSaver(String fileName) {
        this.fileName = fileName;
        this.mapper = new ObjectMapper();
    }

    public void save(ConcurrentHashMap<String, String> info){

        if (!changed){
            return;
        }
        try {
            mapper.writeValue(new File(fileName), info);
        } catch (IOException e) {
            e.printStackTrace();
        }

        changed = false;

    }

    public void change() {
        this.changed = true;
    }

}
