package com.whu.lysl.web.controllers.h5;

import com.alibaba.fastjson.JSON;
import com.whu.lysl.entity.dto.City;
import com.whu.lysl.entity.dto.Province;
import com.whu.lysl.service.region.RegionService;
import com.whu.lysl.web.LYSLBaseController;
import com.whu.lysl.web.LYSLResult;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @Author Caspar
 * @CreateTime 2020/2/11 23:16
 * @Description:
 */

@RestController
@RequestMapping("region")
public class RegionController extends LYSLBaseController {

    @Resource
    private RegionService regionService;


    /**
     * 获取所有省份
     * @param request request
     * @return json
     */
    @RequestMapping(value = "/getProvinceList",method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String getProvinceList(HttpServletRequest request) {
        LYSLResult<Object> res = protectController(request, () -> {
            LYSLResult<Object> result = new LYSLResult<>();
            List<Province> listProvince = regionService.getProvinceList();
            result.setResultObj(listProvince);
            return result;
        }, BaseControllerEnum.IGNORE_VERIFY.getCode());

        return JSON.toJSONString(res);
    }

    /**
     * 获取指定省份城市列表
     * @param request request
     * @return json
     */
    @RequestMapping(value = "/getCityListByProvinceId",method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String getCityListByProvinceId( HttpServletRequest request) {
        LYSLResult<Object> res = protectController(request, () -> {
            LYSLResult<Object> result = new LYSLResult<>();
            List<City> listCity = regionService.getCityListByProvinceId(Integer.parseInt(request.getParameter("provinceId")));
            result.setResultObj(listCity);
            return result;
        }, BaseControllerEnum.IGNORE_VERIFY.getCode());

        return JSON.toJSONString(res);
    }

//    /**
//     * 获取指定城市地区列表
//     * @param request
//     * @return
//     */
//    @RequestMapping(value = "/getDistrictListByCityId",method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
//    @ResponseBody
//    public String getDistrictListByCityId(@RequestBody Map<String, Integer> map, HttpServletRequest request) {
//        LYSLResult<Object> res = protectController(request, () -> {
//            LYSLResult<Object> result = new LYSLResult<>();
//            AssertUtils.AssertNotNull(map.get("cityId"));
//            List<District> listDistrict = regionService.getDistrictListByCityId(map.get("cityId"));
//            result.setResultObj(listDistrict);
//            return result;
//        }, AuthEnum.IGNORE_VERIFY.getCode());
//
//        return JSON.toJSONString(res);
//    }

}
