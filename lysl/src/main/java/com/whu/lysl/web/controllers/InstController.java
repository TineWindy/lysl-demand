package com.whu.lysl.web.controllers;

import com.alibaba.fastjson.JSON;
import com.whu.lysl.base.converters.InstConverter;
import com.whu.lysl.base.enums.LYSLDataStatusEnum;
import com.whu.lysl.entity.condition.InstCondition;
import com.whu.lysl.entity.dto.Institution;
import com.whu.lysl.entity.vo.InstitutionVO;
import com.whu.lysl.service.institution.InstitutionService;
import com.whu.lysl.web.LYSLBaseController;
import com.whu.lysl.web.LYSLResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 机构 controller
 *
 * @author Visionary
 * @since 2020/2/8 9:48 PM
 */
@RestController
@RequestMapping("inst")
public class InstController extends LYSLBaseController {

    /**
     * 机构服务
     */
    @Resource
    private InstitutionService institutionService;

    @RequestMapping("getInstByStatus")
    public String getInstByStatus(HttpServletRequest request) {
        LYSLResult<Object> res = protectController(request, () -> {
            LYSLResult<Object> result = new LYSLResult<>();

            // 这里应该 do 转 vo，先这样写个示例
            List<Institution> institutionList = institutionService.getInstsByCondition(new InstCondition.Builder().status(LYSLDataStatusEnum.UNCHECKED.getCode()).build());
            result.setResultObj(institutionList);
            return result;
        }, BaseControllerEnum.IGNORE_VERIFY.getCode());

        return JSON.toJSONString(res);
    }

    /**
     * 名称模糊查询机构
     *
     * @param request request
     * @return json str
     */
    @GetMapping("queryByPartOfName")
    public String queryByPartOfName(HttpServletRequest request) {
        LYSLResult<Object> res = protectController(request, () -> {
            LYSLResult<Object> result = new LYSLResult<>();

            String partOfName = request.getParameter("name");
            List<InstitutionVO> institutionVOS = InstConverter.batchModel2VO(
                    institutionService.getInstsByPartitionOfName(partOfName));

            result.setResultObj(institutionVOS);
            return result;

        }, BaseControllerEnum.IGNORE_VERIFY.getCode());

        return JSON.toJSONString(res);
    }

}
