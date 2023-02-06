package cc.mrbird.febs.cos.service.impl;

import cc.mrbird.febs.cos.entity.ExpertInfo;
import cc.mrbird.febs.cos.dao.ExpertInfoMapper;
import cc.mrbird.febs.cos.service.IExpertInfoService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;

/**
 * @author FanK
 */
@Service
public class ExpertInfoServiceImpl extends ServiceImpl<ExpertInfoMapper, ExpertInfo> implements IExpertInfoService {

    /**
     * 分页获取专家信息
     *
     * @param page 分页对象
     * @param expertInfo 专家信息
     * @return 结果
     */
    @Override
    public IPage<LinkedHashMap<String, Object>> selectExpertPage(Page<ExpertInfo> page, ExpertInfo expertInfo) {
        return baseMapper.selectExpertPage(page, expertInfo);
    }
}
