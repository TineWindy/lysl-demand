package com.whu.lysl.web.controllers;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.whu.lysl.base.converters.NewsConverter;
import com.whu.lysl.base.enums.LYSLResultCodeEnum;
import com.whu.lysl.base.enums.LovePoolStatusEnum;
import com.whu.lysl.base.exceptions.LYSLException;
import com.whu.lysl.base.utils.AssertUtils;
import com.whu.lysl.entity.condition.MatchOrderCondition;
import com.whu.lysl.entity.condition.NewsCondition;
import com.whu.lysl.entity.dto.DonationOrder;
import com.whu.lysl.entity.dto.MatchOrder;
import com.whu.lysl.entity.dto.News;
import com.whu.lysl.entity.dto.User;
import com.whu.lysl.entity.vo.DonationOrderVO;
import com.whu.lysl.entity.vo.NewsVO;
import com.whu.lysl.service.news.NewsService;
import com.whu.lysl.web.LYSLBaseController;
import com.whu.lysl.web.LYSLResult;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @Author Caspar
 * @CreateTime 2020/2/26 02:01
 * @Description:
 */

@RestController
@RequestMapping("news")
public class NewsController extends LYSLBaseController {
    @Resource
    private NewsService newsService;


    /**
     * 获取 news list
     * @param request request
     * @return json str
     */
    @GetMapping("getNewsList")
    public String getNewsList(HttpServletRequest request) {
        LYSLResult<Object> res = protectController(request, () -> {
            LYSLResult<Object> result = new LYSLResult<>();

            int pageNo = Integer.parseInt(request.getParameter("pageNo"));
            int pageSize = Integer.parseInt(request.getParameter("pageSize"));

            List<News> newsList = newsService.getNewsByCondition(null);

            int fromIndex = (pageNo * pageSize) > newsList.size() ?
                    newsList.size() : (pageNo * pageSize);
            int toIndex = (pageSize * (pageNo + 1)) > newsList.size() ?
                    newsList.size() : (pageSize * (pageNo + 1));
            result.setResultObj( NewsConverter.batchModel2VO(newsList.subList(fromIndex, toIndex)));
            return result;
        }, BaseControllerEnum.IGNORE_VERIFY.getCode());

        return JSON.toJSONString(res);
    }

    /**
     * 获取 news list
     * @param request request
     * @return json str
     */
    @GetMapping("getNewsListByPartionOfTitle")
    public String getNewsListByPartionOfTitle(HttpServletRequest request) {
        LYSLResult<Object> res = protectController(request, () -> {
            LYSLResult<Object> result = new LYSLResult<>();

            String title = request.getParameter("title");

            List<News> newsList = newsService.getNewsByPartitionOfTitle(title);

            result.setResultObj( NewsConverter.batchModel2VO(newsList));
            return result;
        }, BaseControllerEnum.IGNORE_VERIFY.getCode());

        return JSON.toJSONString(res);
    }

    /**
     * 插入 news
     * @param request request
     * @return json str
     */
    @RequestMapping(value="addNews", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String addNews(@RequestBody String requestStr, HttpServletRequest request) {
        LYSLResult<Object> res = protectController(request, () -> {
            LYSLResult<Object> result = new LYSLResult<>();

            JSONObject requestBody = JSON.parseObject(requestStr);
            NewsVO newsVO;
            try {
                newsVO = requestBody.getObject("news", NewsVO.class);
            } catch (Exception e) {
                throw new LYSLException(LYSLResultCodeEnum.DATA_INVALID);
            }

            AssertUtils.AssertNotNull(newsVO, "news is null");

            int newsId = newsService.insertNews(NewsConverter.vo2Model(newsVO));

            result.setResultObj("插入新闻,id: "+newsId);
            return result;
        }, BaseControllerEnum.IGNORE_VERIFY.getCode());

        return JSON.toJSONString(res);
    }

    /**
     * 插入 news
     * @param request request
     * @return json str
     */
    @RequestMapping(value="updateNews", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String updateNews(@RequestBody String requestStr, HttpServletRequest request) {
        LYSLResult<Object> res = protectController(request, () -> {
            LYSLResult<Object> result = new LYSLResult<>();

            JSONObject requestBody = JSON.parseObject(requestStr);
            NewsVO newsVO;
            try {
                newsVO = requestBody.getObject("news", NewsVO.class);
            } catch (Exception e) {
                throw new LYSLException(LYSLResultCodeEnum.DATA_INVALID);
            }

            AssertUtils.AssertNotNull(newsVO, "news is null");

            int newsId = newsService.updateNews(NewsConverter.vo2Model(newsVO));

            if (newsId!=1) {
                throw new LYSLException("更新失败", LYSLResultCodeEnum.ERROR);
            }

            result.setResultObj("更新新闻成功");
            return result;
        }, BaseControllerEnum.IGNORE_VERIFY.getCode());

        return JSON.toJSONString(res);
    }

    /**
     * 删除新闻
     * @param request request
     * @return json str
     */
    @GetMapping("deleteNews")
    public String deleteNews(Integer id, HttpServletRequest request) {
        LYSLResult<Object> res = protectController(request, () -> {
            LYSLResult<Object> result = new LYSLResult<>();
            AssertUtils.AssertNotNull(id);
            int ans = newsService.deleteNews(id);
            if (ans!=1) {
                throw new LYSLException("操作失败", LYSLResultCodeEnum.ERROR);
            }
            result.setResultObj("操作成功");
            return result;
        }, BaseControllerEnum.IGNORE_VERIFY.getCode());

        return JSON.toJSONString(res);
    }

}
