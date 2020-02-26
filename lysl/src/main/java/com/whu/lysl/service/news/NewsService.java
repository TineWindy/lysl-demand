package com.whu.lysl.service.news;

import com.whu.lysl.base.enums.OrderStatusEnum;
import com.whu.lysl.entity.condition.InstCondition;
import com.whu.lysl.entity.condition.NewsCondition;
import com.whu.lysl.entity.dto.Institution;
import com.whu.lysl.entity.dto.News;

import java.util.List;

/**
 * @Author Caspar
 * @CreateTime 2020/2/26 01:35
 * @Description:
 */
public interface NewsService {

    /**
     * 查询符合条件的机构
     * @param newsCondition 查询条件
     * @return 机构 list
     */
    List<News> getNewsByCondition(NewsCondition newsCondition);

    /**
     * 名称模糊查询
     * @param title title
     * @return 机构 list
     */
    List<News> getNewsByPartitionOfTitle(String title);

    /**
     * 新增一条机构数据
     * @param news 机构
     */
    int insertNews(News news);

    int updateNews(News news);

    int deleteNews(Integer id);

}
