package cc.mrbird.febs.cos.entity;

import java.time.LocalDateTime;
import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * 专家推荐
 *
 * @author FanK
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class ExpertRecommend implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 姓名
     */
    private String name;

    /**
     * 民族
     */
    private String nationality;

    /**
     * 个人照片
     */
    private String images;

    /**
     * 性别
     */
    private String sex;

    /**
     * 政治面貌
     */
    private String politicalStatus;

    /**
     * 籍贯
     */
    private String nativePlace;

    /**
     * 工作单位
     */
    private String employer;

    /**
     * 职务
     */
    private String position;

    /**
     * 通讯地址
     */
    private String address;

    /**
     * 手机号
     */
    private String phone;

    /**
     * 专业方向一级
     */
    private String levelOne;

    /**
     * 专业方向二级
     */
    private String levelTwo;

    /**
     * 出生日期
     */
    private String birthDate;

    /**
     * 职称
     */
    private String jobTitle;

    /**
     * 特殊称谓
     */
    private String specialAppellation;

    /**
     * 邮箱
     */
    private String mail;

    /**
     * 固定电话
     */
    private String fixedTelephone;

    /**
     * 个人简介
     */
    private String profile;

    /**
     * 创建时间
     */
    private String createDate;

    /**
     * 推荐人
     */
    private String recommendName;

    /**
     * 审核状态（1.正在审核 2.审核通过 3.驳回）
     */
    private Integer status;

    /**
     * 专家编号
     */
    private String code;

    private Integer userId;
}
