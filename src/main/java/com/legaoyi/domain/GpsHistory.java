package com.legaoyi.domain;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * GPS数据信息对象 t_gps_history
 *
 * @author sxwh
 * @date 2022-04-18
 */
@Data
public class GpsHistory
{
    private static final long serialVersionUID = 1L;
    /** id */
    private String id;

    /** $column.columnComment */
    private String excrypt;


    /** 车牌号 */
    private String plateNumber;

    /** 驾驶员 */
    private String driver;


    private String driverId;

    /** 驾驶员电话 */
    private String driverPhone;

    /** 所属部门名称 */
    private String deptName;


    /** 上传点位时间  */
    private Date uploadtime;


    /** 点位置 */
    private String pointAddress;

    /** 公里桩 */
    private String kmPile;

    /** 日期 */
    private String date;

    /** 时间 */
    private String time;

    /** 经度 */
    private BigDecimal lon;

    /** 纬度 */
    private BigDecimal lat;

    private String nowPoint;


    /** 速度 除以100得到km/h*/
    private String speed;

    /** VEC1 */
    private Integer vec1;

    /** VEC2 */
    private Integer vec2;

    /** VEC3 */
    private Integer vec3;

    /** 方向 */
    private Integer direction;

    /** 高度 */
    private Integer altitude;

    /** 状态,点火情况 0熄火 1点火 */
    private Integer state;

    /** 报警状态 */
    private Integer alarm;

    /** 车辆编码 */
    private String vehicleNo;

    /** 车辆颜色 */
    private Integer vehicleColor;

    /** 类型 */
    private String type;

    /** 数据来源 */
    private String source;



    /** 里程 单位km*/
    private String dis;

    // 处理数据字段
    /** 车id */
//    @Excel(name = "车id")
    private String carId;

    /** 车辆名称 */
//    @Excel(name = "车辆名称")
    private String carName;



    /** sim卡编号 */
//    @Excel(name = "sim卡编号")
    private String carSerialNumber;

    /** 设备编号 */
//    @Excel(name = "设备编号")
    private String deviceNo;

    /** 上传点位时间 */


    /** 所属部门id */
//    @Excel(name = "所属部门id")
    private String deptId;



    /** 类型巡逻车、运兵车、高速巡查车、施救车 */
//    @Excel(name = "类型巡逻车、运兵车、高速巡查车、施救车")
    private String carType;

    /** 描述 */
//    @Excel(name = "描述")
    private String content;

    /** 车辆状态 车辆状态 0熄火 1驾驶中 2处理事故中 3巡逻中 */
//    @Excel(name = "车辆状态 0熄火 1驾驶中 2处理事故中 3巡逻中")
    private String carState;

}
