package com.whu.lysl.service.news.impl;

import com.whu.lysl.base.converters.NewsConverter;
import com.whu.lysl.base.enums.LYSLResultCodeEnum;
import com.whu.lysl.base.exceptions.LYSLException;
import com.whu.lysl.base.utils.AssertUtils;
import com.whu.lysl.dao.NewsDAO;
import com.whu.lysl.entity.condition.NewsCondition;
import com.whu.lysl.entity.dbobj.NewsDO;
import com.whu.lysl.entity.dto.News;
import com.whu.lysl.service.news.NewsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author Caspar
 * @CreateTime 2020/2/26 01:38
 * @Description:
 */
@Service
@Slf4j
public class NewsServiceImpl implements NewsService {
    @Resource
    private NewsDAO newsDAO;

    @Override
    public List<News> getNewsByCondition(NewsCondition newsCondition) {
        return NewsConverter.batchDo2Model(newsDAO.selectByCondition(newsCondition));
    }

    @Override
    public List<News> getNewsByPartitionOfTitle(String title) {
        return NewsConverter.batchDo2Model(newsDAO.queryByPartitionOfTitle(title));
    }

    @Override
    public int insertNews(News news) {

        validateNews(news);
        NewsDO newsDO = NewsConverter.model2DO(news);
        int ans = newsDAO.insert(newsDO);
        if (ans!=1) {
            throw new LYSLException("新增新闻失败", LYSLResultCodeEnum.ERROR);
        }
        return newsDO.getId();
    }

    @Override
    public int updateNews(News news) {
        validateNews(news);
        return newsDAO.update(NewsConverter.model2DO(news));
    }

    @Override
    public int deleteNews(Integer id) {
        int ans=0;
        try {
            ans = newsDAO.delete(id);
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new LYSLException("删除操作失败" ,LYSLResultCodeEnum.ERROR);
        }
        return ans;
    }

    public void validateNews(News news) {
        AssertUtils.AssertNotNull(news);
        AssertUtils.AssertNotNull(news.getOrigin(), "origin不为空");
        AssertUtils.AssertNotNull(news.getPublishDatetime(), "publishDatetime不为空");
        AssertUtils.AssertNotNull(news.getTitle(), "title不为空");
        AssertUtils.AssertNotNull(news.getUrl(), "url不为空");
    }
}
