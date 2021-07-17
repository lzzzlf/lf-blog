package com.lzzz.blog.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @Description:
 * @Author: BeforeOne
 * @Date: Created in 2021/5/27 20:49
 */
@Data
@NoArgsConstructor
public class Visitor {
    private Long id;
    private String uuid;//访客标识码
    private String ip;//ip
    private String ipSource;//ip来源
    private String os;//操作系统
    private String browser;//浏览器
    private Date createTime;//首次访问时间
    private Date lastTime;//最后访问时间
    private Integer pv;//访问页数统计
    private String userAgent;

    public Visitor(String uuid, String ip, String userAgent) {
        this.uuid = uuid;
        this.ip = ip;
        Date date = new Date();
        this.createTime = date;
        this.lastTime = date;
        this.pv = 0;
        this.userAgent = userAgent;
    }
}
