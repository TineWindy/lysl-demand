package com.whu.lysl.service;

import com.whu.lysl.base.converters.MatchOrderConverter;
import com.whu.lysl.base.exceptions.LYSLException;
import com.whu.lysl.dao.MatchOrderDAO;
import com.whu.lysl.entity.dbobj.MatchOrderDo;
import com.whu.lysl.entity.dto.MatchOrder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 订单匹配的具体实现类
 * @author Jzh
 * @since 2020-02-09 19:53
 **/
@Service
public class OrderMatchServiceImpl implements OrderMatchService{

    @Resource
    MatchOrderDAO matchOrderDAO;

    /**
     * 定向捐赠后的匹配接口（在人工审核后调用）
     * @param matchOrder 匹配单
     * @throws LYSLException 主要是参数异常
     */
    @Override
    public void saveMatchOrder(MatchOrder matchOrder) throws LYSLException {
        // 将DTO转换成DO，同时进行参数检查
        List<MatchOrderDo> matchOrderDoList = MatchOrderConverter.Model2DO(matchOrder);
        for (int i = 0;i< matchOrderDoList.size();i++){
            MatchOrderDo matchOrderDo = matchOrderDoList.get(i);
            // 调用DAO将数据存入数据库
            matchOrderDAO.saveMatchOrder(matchOrderDo);
        }

    }


}
