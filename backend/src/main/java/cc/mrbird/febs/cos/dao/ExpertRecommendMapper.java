package cc.mrbird.febs.cos.dao;

import cc.mrbird.febs.cos.entity.ExpertRecommend;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;

import java.util.LinkedHashMap;

/**
 * @author FanK
 */
public interface ExpertRecommendMapper extends BaseMapper<ExpertRecommend> {

    /**
     * 分页获取专家推荐信息
     *
     * @param page 分页对象
     * @param recommend 专家推荐信息
     * @return 结果
     */
    IPage<LinkedHashMap<String, Object>> selectExpertRecommendPage(Page<ExpertRecommend> page, @Param("recommend") ExpertRecommend recommend);
}
