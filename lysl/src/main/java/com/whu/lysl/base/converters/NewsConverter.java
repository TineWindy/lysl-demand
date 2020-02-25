package com.whu.lysl.base.converters;

import com.whu.lysl.entity.dbobj.NewsDO;
import com.whu.lysl.entity.dto.News;
import com.whu.lysl.entity.vo.NewsVO;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author Caspar
 * @Date 2020/2/26 01:21
 * @Description:
 */
public class NewsConverter {

    /**
     * do 2 model
     * @param newsDO do
     * @return model
     */
    public static News do2Model(NewsDO newsDO) {
        if (newsDO == null) {
            return null;
        }

        News news = new News();
        news.setId(newsDO.getId());
        news.setGmtCreated(newsDO.getGmtCreated());
        news.setGmtModified(newsDO.getGmtModified());
        news.setOrigin(newsDO.getOrigin());
        news.setTitle(newsDO.getTitle());
        news.setPublishDatetime(newsDO.getPublishDatetime());
        news.setUrl(newsDO.getUrl());

        return news;
    }

    /**
     * 批量 do 2 model
     * @param newsDOS do list
     * @return model list
     */
    public static List<News> batchDo2Model(List<NewsDO> newsDOS) {
        List<News> newss = new ArrayList<>();

        if (newsDOS == null) {
            return newss;
        }

        for (NewsDO newsDO : newsDOS) {
            newss.add(NewsConverter.do2Model(newsDO));
        }
        return newss;
    }

    /**
     * model 2 do
     * @param news model
     * @return do
     */
    public static NewsDO model2DO(News news) {
        if (news == null) {
            return null;
        }

        NewsDO newsDO = new NewsDO();
        newsDO.setId(news.getId());
        newsDO.setGmtCreated(news.getGmtCreated());
        newsDO.setGmtModified(news.getGmtModified());
        newsDO.setOrigin(news.getOrigin());
        newsDO.setTitle(news.getTitle());
        newsDO.setPublishDatetime(news.getPublishDatetime());
        newsDO.setUrl(news.getUrl());

        return newsDO;
    }

    /**
     * vo 2 model
     * @param newsVO vo
     * @return model
     */
    public static News vo2Model(NewsVO newsVO) {
        if (newsVO == null) {
            return null;
        }

        News news = new News();
        news.setId(newsVO.getId());
        news.setOrigin(newsVO.getOrigin());
        news.setTitle(newsVO.getTitle());
        news.setPublishDatetime(newsVO.getPublishDatetime());
        news.setUrl(newsVO.getUrl());

        return news;
    }

    /**
     * model 2 vo
     * @param news model
     * @return vo
     */
    public static NewsVO model2VO(News news) {
        if (news == null) {
            return null;
        }

        NewsVO newsVO = new NewsVO();
        newsVO.setId(news.getId());
        newsVO.setOrigin(news.getOrigin());
        newsVO.setTitle(news.getTitle());
        newsVO.setPublishDatetime(news.getPublishDatetime());
        newsVO.setUrl(news.getUrl());

        return newsVO;
    }

    /**
     * 批量 model 2 vo
     * @param listNews model list
     * @return vo list
     */
    public static List<NewsVO> batchModel2VO(List<News> listNews) {
        List<NewsVO> newsVOS = new ArrayList<>();

        if (listNews == null) {
            return newsVOS;
        }

        for (News news : listNews) {
            newsVOS.add(NewsConverter.model2VO(news));
        }
        return newsVOS;
    }

}
