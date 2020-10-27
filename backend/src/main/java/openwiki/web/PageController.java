
package openwiki.web;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import openwiki.model.Page;
import openwiki.service.IPageService;


@RestController
public class PageController {

    private IPageService pageService;

    @Autowired
    public PageController(IPageService pService) {
        this.pageService = pService;
    }

    @GetMapping("/api/pages/{id}")
    public Page page(@PathVariable int id) throws IOException {

        return  this.pageService.getPage(id);
    }
    
    

}
