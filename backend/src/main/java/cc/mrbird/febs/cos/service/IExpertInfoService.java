package cc.mrbird.febs.cos.service;

import cc.mrbird.febs.cos.entity.ExpertInfo;
import cc.mrbird.febs.cos.entity.vo.ExpertRecommendVo;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import org.apache.ibatis.annotations.Param;

import java.util.LinkedHashMap;
import java.util.List;

/**
 * @author FanK
 */
public interface IExpertInfoService extends IService<ExpertInfo> {

    /**
     * 分页获取专家信息
     *
     * @param page 分页对象
     * @param expertInfo 专家信息
     * @return 结果
     */
    IPage<LinkedHashMap<String, Object>> selectExpertPage(Page<ExpertInfo> page, ExpertInfo expertInfo);

    /**
     * 根据企业编号获取推荐专家
     *
     * @param enterpriseCode 企业编号
     * @return 结果
     */
    List<ExpertInfo> selectExpertRecommend(String enterpriseCode);
}
