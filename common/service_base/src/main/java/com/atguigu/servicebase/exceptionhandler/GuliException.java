package com.atguigu.servicebase.exceptionhandler;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor //有参构造
@NoArgsConstructor  //无参构造
public class GuliException extends RuntimeException{

    private Integer code;

    private String msg;

    @Override
    public String toString() {
        return "GuliException{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                '}';
    }
}
