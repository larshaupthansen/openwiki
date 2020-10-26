
package openwiki.web;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import openwiki.model.Page;
import openwiki.service.IPageService;


@Controller
public class PageController {

    private IPageService pageService;

    @Autowired
    public PageController(IPageService pService) {
        this.pageService = pService;
    }

    @GetMapping("/**")
    public String page(HttpServletRequest request, Model model) throws IOException {


        var uri = request.getRequestURI();
        Page[] pages = pageService.getPagePath(uri);
        var page = pages[pages.length-1];

        model.addAttribute("pages", pages);
        model.addAttribute("name", page.getTitle());
        model.addAttribute("title", page.getTitle());
        model.addAttribute("content", page.getContent());
                 
		return "page";
	}

}
