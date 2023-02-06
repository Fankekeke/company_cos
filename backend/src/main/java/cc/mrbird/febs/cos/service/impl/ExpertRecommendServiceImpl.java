package cc.mrbird.febs.cos.service.impl;

import cc.mrbird.febs.cos.entity.ExpertRecommend;
import cc.mrbird.febs.cos.dao.ExpertRecommendMapper;
import cc.mrbird.febs.cos.service.IExpertRecommendService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;

/**
 * @author FanK
 */
@Service
public class ExpertRecommendServiceImpl extends ServiceImpl<ExpertRecommendMapper, ExpertRecommend> implements IExpertRecommendService {

    /**
     * 分页获取专家推荐信息
     *
     * @param page 分页对象
     * @param recommend 专家推荐信息
     * @return 结果
     */
    @Override
    public IPage<LinkedHashMap<String, Object>> selectExpertRecommendPage(Page<ExpertRecommend> page, ExpertRecommend recommend) {
        return baseMapper.selectExpertRecommendPage(page, recommend);
    }
}
