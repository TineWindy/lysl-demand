package com.whu.lysl.dao;

import com.whu.lysl.entity.dbobj.MatchOrderDo;
import org.apache.ibatis.annotations.Mapper;

/**
 * 匹配单 dao
 * @author Jzh
 * @since 2020-02-09 19:54
 **/
@Mapper
public interface MatchOrderDAO {

    /**
     * 对匹配单进行保存
     * @param matchOrderDo
     */
    void saveMatchOrder(MatchOrderDo matchOrderDo);

}
