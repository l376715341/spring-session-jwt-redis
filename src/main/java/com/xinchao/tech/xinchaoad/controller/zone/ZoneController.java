package com.xinchao.tech.xinchaoad.controller.zone;

import com.xinchao.tech.xinchaoad.common.api.zone.ZoneService;
import com.xinchao.tech.xinchaoad.common.domain.ResponseObj;
import com.xinchao.tech.xinchaoad.common.domain.zone.Zone;
import com.xinchao.tech.xinchaoad.controller.BaseController;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

/**
 * @author: luhanyu
 * @Date: 2018/7/6 10:36
 * @Description:
 */
@Controller
@RequestMapping(value = "zone")
public class ZoneController extends BaseController {
    @Autowired
    ZoneService zoneService;

    @RequestMapping(value = "/", method = GET)
    @ResponseBody
    @ApiOperation("获取所有地域信息")
    public ResponseObj getAll() {
        List<Zone> zoneVOList = zoneService.getAll();
        ResponseObj responseObj=success();
        responseObj.setData(zoneVOList);
        return responseObj;
    }
    @RequestMapping(value = "/{id}/children", method = GET)
    @ResponseBody
    @ApiOperation("获取下一级的列表，为空默认为查询“中国”的下一级")
    public ResponseObj getNext(@PathVariable Long id){
        List<Zone> list=null;
        if(id==null||id==0){
            list = zoneService.getNext(990001L);
        }else {
            list = zoneService.getNext(id);
        }
        ResponseObj responseObj=success();
        responseObj.setData(list);
        return responseObj;
    }

}
