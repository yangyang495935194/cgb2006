package com.jt.controller.web;

import com.jt.pojo.Item;
import com.jt.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class HttpClientController {
    @Autowired
    private ItemService itemService;
    @RequestMapping("/getItem")
    public List<Item> getItem(){
        return itemService.getItems();
    }

}
