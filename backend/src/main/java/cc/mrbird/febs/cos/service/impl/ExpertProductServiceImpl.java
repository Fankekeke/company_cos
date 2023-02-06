package cc.mrbird.febs.cos.service.impl;

import cc.mrbird.febs.cos.entity.ExpertProduct;
import cc.mrbird.febs.cos.dao.ExpertProductMapper;
import cc.mrbird.febs.cos.service.IExpertProductService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;

/**
 * @author FanK
 */
@Service
public class ExpertProductServiceImpl extends ServiceImpl<ExpertProductMapper, ExpertProduct> implements IExpertProductService {

    /**
     * 分页获取项目成果信息
     *
     * @param page 分页对象
     * @param expertProduct 项目成果信息
     * @return 结果
     */
    @Override
    public IPage<LinkedHashMap<String, Object>> selectExpertProductPage(Page<ExpertProduct> page, ExpertProduct expertProduct) {
        return baseMapper.selectExpertProductPage(page, expertProduct);
    }
}
