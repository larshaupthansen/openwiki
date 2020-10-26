package openwiki.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.stereotype.Service;

import openwiki.model.Page;

/**
 * Note: This implementation is hopelessly inefficient - It is solely made so I cound concentrate on other aspects of the solution.
 * Do not use this for *anything* but testing
 */
@Service
public class FilePageService implements IPageService {

    private String BASEFOLDER =  "/home/haupt/openwiki/";

    public Page getPage(int id) throws IOException {

        String fileName =BASEFOLDER + id + ".json";
        return readPageFromFile(fileName);
    }

    private Page readPageFromFile(String fileName) throws IOException {
        String json = new String(Files.readAllBytes(Paths.get(fileName)));
        return jsonToPage(json);
    }

    public Page getPage(String uri) throws IOException {

        var pagePath = getPagePath(uri);
        return pagePath[pagePath.length-1];    
    }

    public Page[] getPagePath(String uri) throws IOException {

        var segments = uri.split("/");
        
        List<Page> pageList = new ArrayList<>();

        
        if(segments.length > 0) {
            var page = searchForBaseUriPage( segments[0]);

            pageList.add(page);
            if(segments.length>1) {
                pageList.addAll(searchForSubpage(page, segments, 1));
            }
        }
        else {
            var page = searchForBaseUriPage("");
            pageList.add(page);
        }

        return pageList.toArray(new Page[0]);
    }

    private List<Page> searchForSubpage(Page parentPage, String[] uriSegments, int currentSegment) throws IOException { 

        List<Page> pageList = new ArrayList<>();

        var childPages =  searchForChildPages(parentPage);
        for (Page page : childPages) {
            if(page.getUri().equals(uriSegments[currentSegment])) {
                pageList.add(page);
            }
            if(uriSegments.length>currentSegment+1) {
                pageList.addAll(searchForSubpage(page, uriSegments, currentSegment +1));
            }
        }
        return pageList;
    }

    private Page searchForBaseUriPage(String uri) throws IOException {

        var pageList = searchFiles ( (Page p) -> p.getUri().equals(uri));
        if(!pageList.isEmpty()) {
            var page = pageList.get(0);
            page.setParent(null);
            return page ;
        }
        return null;
    }

    private List<Page> searchForChildPages(Page parentPage) throws IOException {

        var childPages = searchFiles( (Page p) -> p.getParentId() == parentPage.getId());

        for (Page page : childPages) {
            page.setParent(parentPage);
        }
        return childPages;
    }

    private List<Page> searchFiles(Predicate<Page> selector) throws IOException {
        File folder = new File(BASEFOLDER);
        List<Page> pages = new ArrayList<>();

        for (final File fileEntry : folder.listFiles()) {
            Page page = readPageFromFile(fileEntry.getAbsolutePath());
            
            if(selector.test(page)) {
                pages.add(page);
            }   
        }
        return pages;
    }


    private Page jsonToPage(String json) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(json, Page.class);   
    }
}
