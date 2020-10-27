package com.heeexy.example.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.heeexy.example.dao.ArticleDao;
import com.heeexy.example.dao.UserDao;
import com.heeexy.example.service.ArticleService;
import com.heeexy.example.util.CommonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * @author: hxy
 * @date: 2017/10/24 16:07
 */
@Service
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private ArticleDao articleDao;

    /**
     * 新增文章
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public JSONObject addArticle(JSONObject jsonObject) {
        articleDao.addArticle(jsonObject);
        return CommonUtil.successJson();
    }

    /**
     * 文章列表
     */
    @Override
    public JSONObject listArticle(JSONObject jsonObject) {
        CommonUtil.fillPageParam(jsonObject);
        int count = articleDao.countArticle(jsonObject);
        List<JSONObject> list = articleDao.listArticle(jsonObject);
        return CommonUtil.successPage(jsonObject, list, count);
    }

    /**
     * 更新文章
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public JSONObject updateArticle(JSONObject jsonObject) {
        articleDao.updateArticle(jsonObject);
        return CommonUtil.successJson();
    }

    @Override
    public JSONObject checkTime(JSONObject jsonObject) {
        return CommonUtil.successJson(getTimeResult(new Date(), (List<Integer>) jsonObject.get("sendTime")));
    }

    private boolean getTimeResult(Date date, List<Integer> integers) {
        boolean result = false;
        int[][] arrs = {{0, 1}, {2, 3}, {4, 5}, {6, 7},
                {8, 9}, {10, 11}, {12, 13}, {14, 15},
                {16, 17}, {18, 19}, {20, 21}, {22, 23},
                {24, 25}, {26, 27}, {28, 29}, {30, 31},
                {32, 33}, {34, 35}, {36, 37}, {38, 39},
                {40, 41}, {42, 43}, {44, 45}, {46, 47}};
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int min = calendar.get(Calendar.MINUTE);
        int[] arr = arrs[hour];

        int number;
        if (min > 30) {
            number = arr[1];
        } else {
            number = arr[0];
        }
        for (Integer string : integers) {
            if (number == string) {
                result = true;
                break;
            }
        }
        return result;

    }



}
