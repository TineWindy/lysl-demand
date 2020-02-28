package com.whu.lysl.service.wx.impl;

import com.whu.lysl.service.wx.wxService;
import me.chanjar.weixin.mp.api.WxMpService;

import javax.annotation.Resource;

/**
 * @Author Caspar
 * @CreateTime 2020/2/28 16:36
 * @Description:
 */
public class wxServiceImpl implements wxService {
    @Resource
    WxMpService wxMpService;


}
