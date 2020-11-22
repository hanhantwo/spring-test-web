package com.cn.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cn.entity.Item;

public interface ItemService extends IService<Item>{
    int saveItem(Item item);

    void generateHtml(int id);
    String getFileTemplateString() throws Exception;
    void saveFileTemplateString(String content) throws Exception;
}
