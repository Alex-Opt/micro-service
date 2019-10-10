package com.ly.mt.mall.h5.vote.controller;

import com.ly.mt.core.base.entity.ResponseCode;
import com.ly.mt.core.base.entity.ResponseJson;
import com.ly.mt.core.base.util.ResponseUtil;
import com.ly.mt.mall.h5.vote.dto.VoteCityDto;
import com.ly.mt.mall.h5.vote.service.VoteService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@Api(description = "一小时到达城市投票")
@RestController
@RequestMapping("/mall/h5/voteCity")
public class HomeVoteCityController {

    @Resource
    private VoteService voteService;


    /**
     * 查询是否投票
     * @param areaId
     * @param userId
     * @return 0 不可以 1可以
     */
    @ApiOperation(value = "查询是否可以投票，0不可以，1可以")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "areaId", value = "区域id", paramType = "string", required = true),
            @ApiImplicitParam(name = "userId", value = "用户id", paramType = "string", required = true),
    })
    @PostMapping("/vote/{areaId}/{userId}")
    public ResponseJson ifVote(@PathVariable("areaId")String areaId,@PathVariable("userId")String userId){
        if (StringUtils.isEmpty(areaId) || StringUtils.isEmpty(userId)){
            return ResponseUtil.getResponseMsg(ResponseCode.RESPONSE_CODE_ERROR,"参数非法");
        }
        Long longAreaId = Long.valueOf(areaId);
        Long longUserId = Long.valueOf(userId);
        return voteService.ifVote(longUserId, longAreaId);
    }

    /**
     * 投票
     * @param voteCityDto
     * @return
     */
    @ApiOperation(value = "投票")
    @PostMapping("/vote")
    public ResponseJson vote(@RequestBody VoteCityDto voteCityDto){
        if (StringUtils.isEmpty(voteCityDto.getAreaId()) || StringUtils.isEmpty(voteCityDto.getUserId())){
            return ResponseUtil.getResponseMsg(ResponseCode.RESPONSE_CODE_ERROR,"参数非法");
        }
        return voteService.vote(Long.valueOf(voteCityDto.getUserId()), Long.valueOf(voteCityDto.getAreaId()));
    }


    /**
     * 查询
     * @param areaId
     * @return
     */
    @ApiOperation(value = "查询投票数",notes = "查询所有区域投票数 areaId传0")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "areaId", value = "区域id", paramType = "string", required = true),
    })
    @PostMapping("/vote/{areaId}")
    public ResponseJson findCount(@PathVariable("areaId")String areaId){
        if (StringUtils.isEmpty(areaId)){
            return ResponseUtil.getResponseMsg(ResponseCode.RESPONSE_CODE_ERROR,"非法参数");
        }
        Long LongAreaId = Long.valueOf(areaId);
        return voteService.findCount(LongAreaId);
    }

    /**
     * 查询是否开通一小时到达
     * @param areaId string
     * @return   areaOpen  true已经开通 false 未开通
     */
    @ApiOperation(value = "查询是否开通一小时到达", notes = "返回areaOpen  true已经开通 false 未开通")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "areaId", value = "区域id", paramType = "string", required = true),
    })
    @PostMapping("/onehour/city/{areaId}")
    public ResponseJson getcityoneHour(@PathVariable("areaId")String areaId){
        if (StringUtils.isEmpty(areaId)){
            return ResponseUtil.getResponseMsg(ResponseCode.RESPONSE_CODE_ERROR,"非法参数");
        }
        Long aLong = Long.valueOf(areaId);
        return voteService.isOpenOneHourArea(aLong);
    }
}
