package com.ly.mt.center.data.vote.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
public class VoteConfig {
    @ApiModelProperty(value = "主键id", required = true)
    private String id;
    @ApiModelProperty(" 投票名称")
    private String vote_name;
    @ApiModelProperty("投票类型 ONCE单次投票  MORE多次投票")
    private String vote_type;
    @ApiModelProperty("限制投票次数，与上述的condition为一个周期性限制")
    private String vote_num;
    @ApiModelProperty("投票条件 ONCE/DAY/WEEK/MONTH/YEAR")
    private String vote_condition;
    @ApiModelProperty("投票有效性 0失效 1有效")
    private String vote_status;
    @ApiModelProperty("规则创建时间")
    private String create_time;
    @ApiModelProperty("预留字段1")
    private String reserved_one;
    @ApiModelProperty("预留字段2")
    private String reserved_two;
    @ApiModelProperty("预留字段3")
    private String reserved_three;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getVote_name() {
        return vote_name;
    }

    public void setVote_name(String vote_name) {
        this.vote_name = vote_name;
    }

    public String getVote_type() {
        return vote_type;
    }

    public void setVote_type(String vote_type) {
        this.vote_type = vote_type;
    }

    public String getVote_num() {
        return vote_num;
    }

    public void setVote_num(String vote_num) {
        this.vote_num = vote_num;
    }

    public String getVote_condition() {
        return vote_condition;
    }

    public void setVote_condition(String vote_condition) {
        this.vote_condition = vote_condition;
    }

    public String getVote_status() {
        return vote_status;
    }

    public void setVote_status(String vote_status) {
        this.vote_status = vote_status;
    }

    public String getCreate_time() {
        return create_time;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }

    public String getReserved_one() {
        return reserved_one;
    }

    public void setReserved_one(String reserved_one) {
        this.reserved_one = reserved_one;
    }

    public String getReserved_two() {
        return reserved_two;
    }

    public void setReserved_two(String reserved_two) {
        this.reserved_two = reserved_two;
    }

    public String getReserved_three() {
        return reserved_three;
    }

    public void setReserved_three(String reserved_three) {
        this.reserved_three = reserved_three;
    }

}