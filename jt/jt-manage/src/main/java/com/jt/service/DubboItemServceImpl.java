package com.jt.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.jt.mapper.ItemDescMapper;
import com.jt.mapper.ItemMapper;
import com.jt.pojo.Item;
import com.jt.pojo.ItemDesc;
import org.springframework.beans.factory.annotation.Autowired;
import service.DubboItemService;


@Service
public class DubboItemServceImpl implements DubboItemService {
    @Autowired
    private ItemMapper itemMapper;
    @Autowired
    private ItemDescMapper itemDescMapper;



    @Override
    public Item findItemById(Long itemId) {

        return itemMapper.selectById(itemId);
    }

    @Override
    public ItemDesc findItemDescById(Long itemId) {

        return itemDescMapper.selectById(itemId);
    }
}