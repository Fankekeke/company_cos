package cc.mrbird.febs.cos.service.impl;

import cc.mrbird.febs.cos.dao.EnterpriseInfoMapper;
import cc.mrbird.febs.cos.dao.ExpertRecommendMapper;
import cc.mrbird.febs.cos.entity.EnterpriseInfo;
import cc.mrbird.febs.cos.entity.ExpertInfo;
import cc.mrbird.febs.cos.dao.ExpertInfoMapper;
import cc.mrbird.febs.cos.entity.ExpertProduct;
import cc.mrbird.febs.cos.entity.ExpertRecommend;
import cc.mrbird.febs.cos.entity.vo.ExpertRecommendVo;
import cc.mrbird.febs.cos.service.IExpertInfoService;
import cc.mrbird.febs.cos.service.IExpertProductService;
import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * @author FanK
 */
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ExpertInfoServiceImpl extends ServiceImpl<ExpertInfoMapper, ExpertInfo> implements IExpertInfoService {

    private final EnterpriseInfoMapper enterpriseInfoMapper;

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

    /**
     * 根据企业编号获取推荐专家
     *
     * @param enterpriseCode 企业编号
     * @return 结果
     */
    @Override
    public List<ExpertInfo> selectExpertRecommend(String enterpriseCode) {
        // 获取企业信息
        EnterpriseInfo enterpriseInfo = enterpriseInfoMapper.selectOne(Wrappers.<EnterpriseInfo>lambdaQuery().eq(EnterpriseInfo::getCode, enterpriseCode));
        if (enterpriseInfo == null) {
            return Collections.emptyList();
        }
        // 查找推荐专家
        return this.list(Wrappers.<ExpertInfo>lambdaQuery()
                .like(StrUtil.isNotEmpty(enterpriseInfo.getIndustry()), ExpertInfo::getLevelOne, enterpriseInfo.getIndustry())
                .or()
                .like(StrUtil.isNotEmpty(enterpriseInfo.getIndustry()), ExpertInfo::getLevelTwo, enterpriseInfo.getIndustry())
                .eq(ExpertInfo::getOpenFlag, 1));
    }
}
