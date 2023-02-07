package cc.mrbird.febs.cos.service.impl;

import cc.mrbird.febs.cos.dao.ExpertInfoMapper;
import cc.mrbird.febs.cos.entity.EnterpriseInfo;
import cc.mrbird.febs.cos.dao.EnterpriseInfoMapper;
import cc.mrbird.febs.cos.entity.ExpertInfo;
import cc.mrbird.febs.cos.service.IEnterpriseInfoService;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * @author FanK
 */
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class EnterpriseInfoServiceImpl extends ServiceImpl<EnterpriseInfoMapper, EnterpriseInfo> implements IEnterpriseInfoService {

    private final ExpertInfoMapper expertInfoMapper;

    /**
     * 分页获取企业信息
     *
     * @param page 分页对象
     * @param enterpriseInfo 企业信息
     * @return 结果
     */
    @Override
    public IPage<LinkedHashMap<String, Object>> selectEnterprisePage(Page<EnterpriseInfo> page, EnterpriseInfo enterpriseInfo) {
        return baseMapper.selectEnterprisePage(page, enterpriseInfo);
    }

    /**
     * 根据专家编号获取推荐企业
     *
     * @param expertCode 专家编号
     * @return 结果
     */
    @Override
    public List<EnterpriseInfo> selectEnterpriseRecommend(String expertCode) {
        // 获取专家信息
        ExpertInfo expertInfo = expertInfoMapper.selectOne(Wrappers.<ExpertInfo>lambdaQuery().eq(ExpertInfo::getCode, expertCode));
        if (expertInfo == null) {
            return Collections.emptyList();
        }
        // 根据专业一级，专业二级推荐
        return this.list(Wrappers.<EnterpriseInfo>lambdaQuery()
                .like(StrUtil.isNotEmpty(expertInfo.getLevelOne()), EnterpriseInfo::getIndustry, expertInfo.getLevelOne())
                .or()
                .like(StrUtil.isNotEmpty(expertInfo.getLevelTwo()), EnterpriseInfo::getIndustry, expertInfo.getLevelTwo()));
    }
}
