package cc.mrbird.febs.cos.dao;

import cc.mrbird.febs.cos.entity.ExpertInfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;

import java.util.LinkedHashMap;

/**
 * @author FanK
 */
public interface ExpertInfoMapper extends BaseMapper<ExpertInfo> {

    /**
     * 分页获取专家信息
     *
     * @param page 分页对象
     * @param expertInfo 专家信息
     * @return 结果
     */
    IPage<LinkedHashMap<String, Object>> selectExpertPage(Page<ExpertInfo> page, @Param("expertInfo") ExpertInfo expertInfo);
}
