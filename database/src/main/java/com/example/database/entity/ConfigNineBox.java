package com.example.database.entity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;

import java.util.Date;

import org.greenrobot.greendao.annotation.Generated;

/**
 * create on 2021/7/25 17:10
 * Email:923998007@qq.com
 *
 * @author lin
 */
@Entity
public class ConfigNineBox {
    @Id
    private int id;
    private String configKey;
    private String configValue;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    @Generated(hash = 604327776)
    public ConfigNineBox(int id, String configKey, String configValue,
                         Date createTime, Date updateTime) {
        this.id = id;
        this.configKey = configKey;
        this.configValue = configValue;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }

    @Generated(hash = 636614705)
    public ConfigNineBox() {
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getConfigKey() {
        return this.configKey;
    }

    public void setConfigKey(String configKey) {
        this.configKey = configKey;
    }

    public String getConfigValue() {
        return this.configValue;
    }

    public void setConfigValue(String configValue) {
        this.configValue = configValue;
    }

    public Date getCreateTime() {
        return this.createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return this.updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}
