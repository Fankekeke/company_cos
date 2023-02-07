package cc.mrbird.febs.cos.service.impl;

import cc.mrbird.febs.cos.dao.ExpertInfoMapper;
import cc.mrbird.febs.cos.entity.ExpertInfo;
import cc.mrbird.febs.cos.entity.ExpertProduct;
import cc.mrbird.febs.cos.entity.ExpertRecommend;
import cc.mrbird.febs.cos.dao.ExpertRecommendMapper;
import cc.mrbird.febs.cos.entity.vo.ExpertRecommendVo;
import cc.mrbird.febs.cos.service.IExpertInfoService;
import cc.mrbird.febs.cos.service.IExpertProductService;
import cc.mrbird.febs.cos.service.IExpertRecommendService;
import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * @author FanK
 */
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ExpertRecommendServiceImpl extends ServiceImpl<ExpertRecommendMapper, ExpertRecommend> implements IExpertRecommendService {

    private final IExpertProductService expertProductService;

    private final IExpertInfoService expertInfoService;

    private final ExpertRecommendMapper expertRecommendMapper;

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

    /**
     * 专家推荐审核
     *
     * @param auditId 审核ID
     * @param status  审核结果
     * @return 结果
     */
    @Override
    public boolean auditExpertRecommend(Integer auditId, Integer status) {
        return this.update(Wrappers.<ExpertRecommend>lambdaUpdate().set(ExpertRecommend::getStatus, status).eq(ExpertRecommend::getId, auditId));
    }

    /**
     * 新增专家推荐信息
     *
     * @param expertRecommendVo 专家推荐信息
     * @return 结果
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean recommendBatch(ExpertRecommendVo expertRecommendVo) {
        // 添加专家信息
        ExpertInfo expertInfo = BeanUtil.copyProperties(expertRecommendVo, ExpertInfo.class);
        expertInfo.setOpenFlag(0);
        expertInfo.setHasExist(0);
        expertInfo.setCode("EX-" + System.currentTimeMillis());

        List<ExpertProduct> expertProductList = expertRecommendVo.getProductList();
        // 添加项目成果
        if (CollectionUtil.isNotEmpty(expertProductList)) {
            expertProductList.forEach(e -> {
                e.setExpertCode(expertInfo.getCode());
                e.setCreateDate(DateUtil.formatDateTime(new Date()));
            });
            expertProductService.saveBatch(expertProductList);
        }
        expertInfoService.save(expertInfo);

        // 添加推荐信息
        ExpertRecommend expertRecommend = BeanUtil.copyProperties(expertRecommendVo, ExpertRecommend.class);
        expertRecommend.setCode(expertInfo.getCode());
        expertRecommend.setCreateDate(DateUtil.formatDateTime(new Date()));
        expertRecommend.setStatus(1);
        return expertRecommendMapper.insert(expertRecommend) > 0;
    }

    /**
     * 审核通过后发布
     *
     * @param auditId 审核ID
     * @return 结果
     */
    @Override
    public boolean release(Integer auditId) {
        // 查询推荐信息
        ExpertRecommend expertRecommend = this.getById(auditId);
        // 设置专家信息开放
        return expertInfoService.update(Wrappers.<ExpertInfo>lambdaUpdate().set(ExpertInfo::getOpenFlag, 1).eq(ExpertInfo::getCode, expertRecommend.getCode()));
    }
}
