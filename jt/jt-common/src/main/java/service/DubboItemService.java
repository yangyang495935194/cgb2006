package service;

import com.jt.pojo.Item;
import com.jt.pojo.ItemDesc;

public interface DubboItemService {
    Item findItemById(Long ItemId);
    ItemDesc findItemDescById(Long ItemDescId);
}
