package com.hongdian.plat.term.entity;

import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 行政区划信息
 * </p>
 *
 * @author xlhua
 * @since 2018-08-01
 */
@TableName("biz_dept")
public class BizDept extends Model<BizDept> {

    private static final long serialVersionUID = 1L;

    /**
     * 行政区划ID
     */
    @TableId(value = "ID", type = IdType.AUTO)
    private String id;
    /**
     * 所属上级行政区划ID
     */
    @TableField("PID")
    private String pid;
    /**
     * 行政区划名
     */
    @TableField("NAME")
    private String name;
    /**
     * 总人口数
     */
    @TableField("POPULATION")
    private String population;
    /**
     * 总家庭数
     */
    @TableField("HOUSEHOLDS")
    private String households;
    /**
     * 外出人口数
     */
    @TableField("OUTFLOWPOPULATION")
    private String outflowpopulation;
    /**
     * 房屋数
     */
    @TableField("HOUSES")
    private String houses;
    /**
     * 农田数
     */
    @TableField("FARMLAND")
    private String farmland;
    /**
     * 历史洪水线下家庭数
     */
    @TableField("FLOODHOUSEHOLDS")
    private String floodhouseholds;
    /**
     * 历史洪水线房屋数
     */
    @TableField("FLOODHOUSES")
    private String floodhouses;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPopulation() {
        return population;
    }

    public void setPopulation(String population) {
        this.population = population;
    }

    public String getHouseholds() {
        return households;
    }

    public void setHouseholds(String households) {
        this.households = households;
    }

    public String getOutflowpopulation() {
        return outflowpopulation;
    }

    public void setOutflowpopulation(String outflowpopulation) {
        this.outflowpopulation = outflowpopulation;
    }

    public String getHouses() {
        return houses;
    }

    public void setHouses(String houses) {
        this.houses = houses;
    }

    public String getFarmland() {
        return farmland;
    }

    public void setFarmland(String farmland) {
        this.farmland = farmland;
    }

    public String getFloodhouseholds() {
        return floodhouseholds;
    }

    public void setFloodhouseholds(String floodhouseholds) {
        this.floodhouseholds = floodhouseholds;
    }

    public String getFloodhouses() {
        return floodhouses;
    }

    public void setFloodhouses(String floodhouses) {
        this.floodhouses = floodhouses;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "BizDept{" +
        "id=" + id +
        ", pid=" + pid +
        ", name=" + name +
        ", population=" + population +
        ", households=" + households +
        ", outflowpopulation=" + outflowpopulation +
        ", houses=" + houses +
        ", farmland=" + farmland +
        ", floodhouseholds=" + floodhouseholds +
        ", floodhouses=" + floodhouses +
        "}";
    }
}
