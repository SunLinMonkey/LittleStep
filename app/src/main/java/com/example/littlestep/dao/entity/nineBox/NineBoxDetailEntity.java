package com.example.littlestep.dao.entity.nineBox;

import com.example.littlestep.annotation.NineBoxDetailStatusAnnotation;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;

import java.util.Date;

import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Index;
import org.greenrobot.greendao.annotation.Property;

/**
 * 九宫格明细
 * create on 2021/8/29 20:39
 * Email:923998007@qq.com
 *
 * @author lin
 */
@Entity(nameInDb = "NineBoxDetail")
public class NineBoxDetailEntity {

    @Id(autoincrement = true)
    private Long id;

    /**
     * 每日生成的唯一标识符，只与日期和用户有关
     */
    @Property(nameInDb = "recordKey")
    private String recordKey;

    /**
     * 九宫格,格子的key
     */
    @Property(nameInDb = "itemKey")
    private String itemKey;

    /**
     * 九宫格的对于key下的value
     */
    private String itemValue;

    /**
     * 处理状态
     */
    private int status;

    /**
     * 更新value值的时间
     */
    private Date updateValueTime;

    /**
     * 第一次填写记录时间
     */
    private Date crateTime;

    /**
     * 更新status值的时间
     */
    private Date updateStatusTime;


    @Generated(hash = 1127425558)
    public NineBoxDetailEntity(Long id, String recordKey, String itemKey,
                               String itemValue, int status, Date updateValueTime, Date crateTime,
                               Date updateStatusTime) {
        this.id = id;
        this.recordKey = recordKey;
        this.itemKey = itemKey;
        this.itemValue = itemValue;
        this.status = status;
        this.updateValueTime = updateValueTime;
        this.crateTime = crateTime;
        this.updateStatusTime = updateStatusTime;
    }

    @Generated(hash = 191056513)
    public NineBoxDetailEntity() {
    }


    public String getRecordKey() {
        return recordKey;
    }

    public void setRecordKey(String recordKey) {
        this.recordKey = recordKey;
    }

    public String getItemKey() {
        return itemKey;
    }

    public void setItemKey(String itemKey) {
        this.itemKey = itemKey;
    }

    public String getItemValue() {
        return itemValue;
    }

    public void setItemValue(String itemValue) {
        this.itemValue = itemValue;
    }

    public int getStatus() {
        return status;
    }


    public void setStatus(@NineBoxDetailStatusAnnotation int status) {
        this.status = status;
    }

    public Date getUpdateValueTime() {
        return updateValueTime;
    }

    public void setUpdateValueTime(Date updateValueTime) {
        this.updateValueTime = updateValueTime;
    }

    public Date getCrateTime() {
        return crateTime;
    }

    public void setCrateTime(Date crateTime) {
        this.crateTime = crateTime;
    }

    public Date getUpdateStatusTime() {
        return updateStatusTime;
    }

    public void setUpdateStatusTime(Date updateStatusTime) {
        this.updateStatusTime = updateStatusTime;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return this.id;
    }

    @Override
    public String toString() {
        return "NineBoxDetailEntity{" +
                "id=" + id +
                ", recordKey='" + recordKey + '\'' +
                ", itemKey='" + itemKey + '\'' +
                ", itemValue='" + itemValue + '\'' +
                ", status=" + status +
                ", updateValueTime=" + updateValueTime +
                ", crateTime=" + crateTime +
                ", updateStatusTime=" + updateStatusTime +
                '}';
    }
}
