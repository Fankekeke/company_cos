package cc.mrbird.febs.cos.entity;

import java.time.LocalDateTime;
import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * 专家成果
 *
 * @author FanK
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class ExpertProduct implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 专家编号
     */
    private String expertCode;

    /**
     * 项目名称
     */
    private String productName;

    /**
     * 内容研究
     */
    private String research;

    /**
     * 关键技术
     */
    private String technology;

    /**
     * 项目内容
     */
    private String content;

    /**
     * 图册
     */
    private String images;

    /**
     * 发布时间
     */
    private String createDate;

    @TableField(exist = false)
    private String expertName;

}
