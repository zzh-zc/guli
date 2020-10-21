package com.zzh.commonutils;

public enum ResultCodeEnum {

    FILE_UPLOAD_ERROR(20001,"上传失败");

    private Integer code;

    private String message;


    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    ResultCodeEnum(Integer code,String message) {
        this.code = code;
        this.message = message;
    }
}
