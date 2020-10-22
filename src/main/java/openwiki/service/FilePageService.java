package openwiki.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.stereotype.Service;

import openwiki.model.Page;

@Service
public class FilePageService implements IPageService {

    public Page getPage(int id) throws IOException {

        String fileName = "/home/haupt/openwiki/" + id + ".json";
        String json = new String(Files.readAllBytes(Paths.get(fileName)));

        return jsonToPage(json);
    }

    private Page jsonToPage(String json) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(json, Page.class);   
    }
}
