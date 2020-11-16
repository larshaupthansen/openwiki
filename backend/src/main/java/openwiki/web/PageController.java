
package openwiki.web;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import openwiki.model.Page;
import openwiki.service.IPageService;


@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class PageController {

    private IPageService pageService;

    @Autowired
    public PageController(IPageService pService) {
        this.pageService = pService;
    }

    @GetMapping("/api/page/{id}")
    public Page getPage(@PathVariable int id) throws IOException {

        return  this.pageService.getPage(id);
    }

    @GetMapping("/api/children/{id}")
    public Page[] getChildren(@PathVariable int id) throws IOException {

        return  this.pageService.getChildren(id);
    }

    @GetMapping("/api/path/**")
    public Page[] getPath(HttpServletRequest request) throws IOException {

        var uri = request.getRequestURI().substring(9);

        return  pageService.getPagePath(uri);

    }


}
