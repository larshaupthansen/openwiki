package openwiki.service;

import java.io.IOException;

import openwiki.model.Page;

public interface IPageService {
    


        public Page getPage(int id) throws IOException;


}
