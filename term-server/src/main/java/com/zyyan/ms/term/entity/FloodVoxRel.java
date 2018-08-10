package com.zyyan.ms.term.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 泄洪预警手动语音报警设备信息表
 * </p>
 *
 * @author xlhua
 * @since 2018-07-19
 */
@TableName("flood_vox_rel")
public class FloodVoxRel extends Model<FloodVoxRel> {

    private static final long serialVersionUID = 1L;

    /**
     * 播放随机数
     */
    @TableId("ID")
    private int id;
    /**
     * 网关编码
     */
    @TableField("GATE")
    private String gate;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getGate() {
        return gate;
    }

    public void setGate(String gate) {
        this.gate = gate;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "FloodVoxRel{" +
        "id=" + id +
        ", gate=" + gate +
        "}";
    }
}
