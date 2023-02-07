package cc.mrbird.febs.cos.service;

import cc.mrbird.febs.cos.entity.EnterpriseInfo;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import org.apache.ibatis.annotations.Param;

import java.util.LinkedHashMap;
import java.util.List;

/**
 * @author FanK
 */
public interface IEnterpriseInfoService extends IService<EnterpriseInfo> {

    /**
     * 分页获取企业信息
     *
     * @param page 分页对象
     * @param enterpriseInfo 企业信息
     * @return 结果
     */
    IPage<LinkedHashMap<String, Object>> selectEnterprisePage(Page<EnterpriseInfo> page, EnterpriseInfo enterpriseInfo);

    /**
     * 根据专家编号获取推荐企业
     *
     * @param expertCode 专家编号
     * @return 结果
     */
    List<EnterpriseInfo> selectEnterpriseRecommend(String expertCode);
}
