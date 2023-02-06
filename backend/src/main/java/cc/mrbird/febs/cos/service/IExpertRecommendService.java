package cc.mrbird.febs.cos.service;

import cc.mrbird.febs.cos.entity.ExpertRecommend;
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
     * @param page 分页对象
     * @param recommend 专家推荐信息
     * @return 结果
     */
    IPage<LinkedHashMap<String, Object>> selectExpertRecommendPage(Page<ExpertRecommend> page, ExpertRecommend recommend);
}
