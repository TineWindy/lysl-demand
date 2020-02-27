package com.whu.lysl.web.controllers.backend;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.whu.lysl.base.converters.TransportationConverter;
import com.whu.lysl.base.enums.LYSLResultCodeEnum;
import com.whu.lysl.base.enums.OrderStatusEnum;
import com.whu.lysl.base.exceptions.LYSLException;
import com.whu.lysl.base.utils.AssertUtils;
import com.whu.lysl.entity.condition.TransportationCondition;
import com.whu.lysl.entity.dto.Transportation;
import com.whu.lysl.entity.vo.TransportationVO;
import com.whu.lysl.service.transportation.TransportationService;
import com.whu.lysl.web.LYSLBaseController;
import com.whu.lysl.web.LYSLResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @Author Caspar
 * @CreateTime 2020/2/27 16:16
 * @Description:
 */

@Slf4j
@RestController
@RequestMapping("backend/transportation")
public class BackendTransportationController extends LYSLBaseController {

    @Resource
    TransportationService transportationService;

    /**
     * 获取 news list
     * @param request request
     * @return json str
     */
    @GetMapping("getTransportationList")
    public String getTransportationList(HttpServletRequest request) {
        LYSLResult<Object> res = protectController(request, () -> {
            LYSLResult<Object> result = new LYSLResult<>();
            // 后台接口要求分页的页码从1开始
            int pageNo = Integer.parseInt(request.getParameter("pageNo"))-1;
            int pageSize = Integer.parseInt(request.getParameter("pageSize"));

            List<Transportation> transportationList = transportationService.getTransportationByCondition(
                    new TransportationCondition.Builder().deleted(0).build());

            int fromIndex = (pageNo * pageSize) > transportationList.size() ?
                    transportationList.size() : (pageNo * pageSize);
            int toIndex = (pageSize * (pageNo + 1)) > transportationList.size() ?
                    transportationList.size() : (pageSize * (pageNo + 1));
            result.setResultObj( TransportationConverter.batchModel2VO(transportationList.subList(fromIndex, toIndex)));
            result.setCount(transportationList.size());
            return result;
        }, BaseControllerEnum.IGNORE_VERIFY.getCode(), BaseControllerEnum.BACK_MANAGE.getCode());

        return JSON.toJSONString(res);
    }

    /**
     * 插入 news
     * @param request request
     * @return json str
     */
    @RequestMapping(value="addTransportation", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String addTransportation(@RequestBody String requestStr, HttpServletRequest request) {
        LYSLResult<Object> res = protectController(request, () -> {
            LYSLResult<Object> result = new LYSLResult<>();

            JSONObject requestBody = JSON.parseObject(requestStr);
            TransportationVO transportationVO;
            try {
                transportationVO = requestBody.getObject("transportation", TransportationVO.class);
            } catch (Exception e) {
                log.error(e.getMessage());
                throw new LYSLException(LYSLResultCodeEnum.DATA_INVALID);
            }

            int transportationId = transportationService.insertTransportation(TransportationConverter.vo2Model(transportationVO));

            result.setResultObj("插入运输信息,id: "+transportationId);
            return result;
        }, BaseControllerEnum.IGNORE_VERIFY.getCode(), BaseControllerEnum.BACK_MANAGE.getCode());

        return JSON.toJSONString(res);
    }

    /**
     * 插入 news
     * @param request request
     * @return json str
     */
    @RequestMapping(value="updateTransportation", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String updateTransportation(@RequestBody String requestStr, HttpServletRequest request) {
        LYSLResult<Object> res = protectController(request, () -> {
            LYSLResult<Object> result = new LYSLResult<>();

            JSONObject requestBody = JSON.parseObject(requestStr);
            TransportationVO transportationVO;
            try {
                transportationVO = requestBody.getObject("transportation", TransportationVO.class);
            } catch (Exception e) {
                log.error(e.getMessage());
                throw new LYSLException(LYSLResultCodeEnum.DATA_INVALID);
            }

            AssertUtils.AssertNotNull(transportationVO, "transportation is null");

            int transportationId = transportationService.updateTransportation(TransportationConverter.vo2Model(transportationVO));

            if (transportationId!=1) {
                throw new LYSLException("更新失败", LYSLResultCodeEnum.ERROR);
            }

            result.setResultObj("更新成功");
            return result;
        }, BaseControllerEnum.IGNORE_VERIFY.getCode(), BaseControllerEnum.BACK_MANAGE.getCode());

        return JSON.toJSONString(res);
    }

    /**
     * 插入 news
     * @param request request
     * @return json str
     */
    @GetMapping("deleteTransportation")
    public String deleteTransportation(@Validated Integer id, HttpServletRequest request) {
        LYSLResult<Object> res = protectController(request, () -> {
            LYSLResult<Object> result = new LYSLResult<>();

            AssertUtils.AssertNotNull(id, "id is null");

            int transportationId = transportationService.deleteTransportation(id);

            if (transportationId!=1) {
                throw new LYSLException("删除失败或id无效", LYSLResultCodeEnum.ERROR);
            }

            result.setResultObj("删除成功");
            return result;
        }, BaseControllerEnum.IGNORE_VERIFY.getCode(), BaseControllerEnum.BACK_MANAGE.getCode());

        return JSON.toJSONString(res);
    }


    /**
     * 审核 运输信息
     * @param request request
     * @return json str
     */
    @GetMapping("checkTransportation")
    public String checkTransportation(@Validated Integer id, @Validated String checkStatus, HttpServletRequest request) {
        LYSLResult<Object> res = protectController(request, () -> {
            LYSLResult<Object> result = new LYSLResult<>();

            int ans = transportationService.checkTranspirtatinById(id, checkStatus);
            if (ans!=1) {
                throw new LYSLException("审核失败", LYSLResultCodeEnum.ERROR);
            }
            result.setResultObj("审核操作成功");
            return result;
        }, BaseControllerEnum.IGNORE_VERIFY.getCode(), BaseControllerEnum.BACK_MANAGE.getCode());

        return JSON.toJSONString(res);
    }
}
