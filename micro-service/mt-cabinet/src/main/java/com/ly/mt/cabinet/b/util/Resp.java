package com.ly.mt.cabinet.b.util;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class Resp<T> {
  private int code;
  private T result;
  private String msg;

  private Resp(RespCode respCode) {
    this.code = respCode.getCode();
    this.msg = respCode.getDesc();
  }

  private Resp(RespCode success, T result) {
    this(success);
    this.result = result;
  }

  private Resp(int code, String msg) {
    this.code = code;
    this.msg = msg;
  }

  public void setCode(int code) {
    this.code = code;
  }


  public void setMsg(String msg) {
    this.msg = msg;
  }

  public int getCode() {
    return code;
  }

  public String getMsg() {
    return msg;
  }

  public T getResult() {
    return result;
  }

  public void setResult(T result) {
    this.result = result;
  }

  @JsonIgnore
  public boolean isSuccess() {
    return code == RespCode.SUCCESS.getCode();
  }

  public static <T> Resp<T> createBySuccess() {
    return new Resp<>(RespCode.SUCCESS);
  }

  public static <T> Resp<T> createBySuccess(T data) {
    return new Resp<>(RespCode.SUCCESS, data);
  }


  public static <T> Resp<T> createByError() {
    return new Resp<>(RespCode.ERROR);
  }

  public static <T> Resp<T> createByErrorMessage(String msg) {
    return new Resp<>(RespCode.ERROR.getCode(), msg);
  }

  public static <T> Resp<T> createMessage(RespCode respCode) {
    return new Resp<>(respCode.getCode(), respCode.getDesc());
  }

  public static <T> Resp<T> createBySuccess(RespCode respCode, T data) {
    return new Resp<>(respCode, data);
  }

  public static <T> Resp<T> createBySuccessMessage(String msg) {
    return new Resp<>(RespCode.SUCCESS.getCode(), msg);
  }

}
