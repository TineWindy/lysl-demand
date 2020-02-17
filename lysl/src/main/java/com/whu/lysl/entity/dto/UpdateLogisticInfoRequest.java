package com.whu.lysl.entity.dto;

import com.whu.lysl.base.utils.StringUtils;
import lombok.Data;

import java.util.List;

/**
 * 更新物流参数请求类
 *
 * @author Jzh
 * @since 2020-02-16 21:13
 **/
@Data
public class UpdateLogisticInfoRequest {
    String logisticCode;
    List<String> picList;
    String remark;
    int matchOrder;

    public String getPicListStr(){
        String picListStr = "";
        for(int i = 0;i<picList.size();i++){
            picListStr += picList.get(i) + ",";
        }
        if(!StringUtils.isNotEmpty(picListStr)){
            return null;
        }
        picListStr = picListStr.substring(0,picListStr.length()-1);
        return picListStr;
    }
}
