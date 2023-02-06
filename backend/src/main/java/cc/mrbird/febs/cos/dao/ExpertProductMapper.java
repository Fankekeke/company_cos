package cc.mrbird.febs.cos.dao;

import cc.mrbird.febs.cos.entity.ExpertProduct;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;

import java.util.LinkedHashMap;

/**
 * @author FanK
 */
public interface ExpertProductMapper extends BaseMapper<ExpertProduct> {

    /**
     * 分页获取项目成果信息
     *
     * @param page 分页对象
     * @param expertProduct 项目成果信息
     * @return 结果
     */
    IPage<LinkedHashMap<String, Object>> selectExpertProductPage(Page<ExpertProduct> page, @Param("expertProduct") ExpertProduct expertProduct);
}
