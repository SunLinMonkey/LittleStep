package com.example.littlestep.dao.entity.nineBox;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

import java.util.Date;

/**
 * 九宫格主信息
 * create on 2021/7/26 21:16
 * Email:923998007@qq.com
 *
 * @author lin
 */
@Entity(nameInDb = "NineBoxMain")
public class NineBoxHolderEntity {

    @Id(autoincrement = true)
    private Long id;

    /**
     * 每日生成的唯一标识符，只与日期和用户有关
     */
    private String recordKey;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;


    @Generated(hash = 1907666074)
    public NineBoxHolderEntity(Long id, String recordKey, Date createTime,
                               Date updateTime) {
        this.id = id;
        this.recordKey = recordKey;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }

    @Generated(hash = 1523645318)
    public NineBoxHolderEntity() {
    }


    public String getRecordKey() {
        return this.recordKey;
    }

    public void setRecordKey(String recordKey) {
        this.recordKey = recordKey;
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

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return this.id;
    }

    @Override
    public String toString() {
        return "NineBoxHolderEntity{" +
                "id=" + id +
                ", recordKey='" + recordKey + '\'' +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                '}';
    }
}
