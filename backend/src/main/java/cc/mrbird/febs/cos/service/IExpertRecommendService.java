package cc.mrbird.febs.cos.service;

import cc.mrbird.febs.cos.entity.ExpertRecommend;
import cc.mrbird.febs.cos.entity.vo.ExpertRecommendVo;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import org.apache.ibatis.annotations.Param;

import java.util.LinkedHashMap;

/**
 * @author FanK
 */
public interface IExpertRecommendService extends IService<ExpertRecommend> {

    /**
     * 分页获取专家推荐信息
     *
     * @param page      分页对象
     * @param recommend 专家推荐信息
     * @return 结果
     */
    IPage<LinkedHashMap<String, Object>> selectExpertRecommendPage(Page<ExpertRecommend> page, ExpertRecommend recommend);

    /**
     * 专家推荐审核
     *
     * @param auditId 审核ID
     * @param status  审核结果
     * @return 结果
     */
    boolean auditExpertRecommend(Integer auditId, Integer status);

    /**
     * 新增专家推荐信息
     *
     * @param expertRecommend 专家推荐信息
     * @return 结果
     */
    boolean recommendBatch(ExpertRecommendVo expertRecommend);

    /**
     * 审核通过后发布
     *
     * @param auditId 审核ID
     * @return 结果
     */
    boolean release(Integer auditId);
}
