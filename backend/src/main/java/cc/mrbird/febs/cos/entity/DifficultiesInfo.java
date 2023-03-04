package cc.mrbird.febs.cos.entity;

import java.time.LocalDateTime;
import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * 技术难点
 *
 * @author FanK
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class DifficultiesInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 技术难点
     */
    private String name;

    /**
     * 技术类型
     */
    private String type;

    /**
     * 内容
     */
    private String content;

    /**
     * 企业编号
     */
    private String enterpriseCode;

    /**
     * 所属账户
     */
    private Integer userId;

    /**
     * 创建时间
     */
    private String createDate;


}
