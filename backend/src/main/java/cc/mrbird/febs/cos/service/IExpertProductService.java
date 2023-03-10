package cc.mrbird.febs.cos.service;

import cc.mrbird.febs.cos.entity.ExpertProduct;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import org.apache.ibatis.annotations.Param;

import java.util.LinkedHashMap;
import java.util.List;

/**
 * @author FanK
 */
public interface IExpertProductService extends IService<ExpertProduct> {

    /**
     * 分页获取项目成果信息
     *
     * @param page 分页对象
     * @param expertProduct 项目成果信息
     * @return 结果
     */
    IPage<LinkedHashMap<String, Object>> selectExpertProductPage(Page<ExpertProduct> page, ExpertProduct expertProduct);

    /**
     * 根据专家编号获取项目成果
     *
     * @param expertCode 专家编号
     * @return 结果
     */
    List<ExpertProduct> selectProductByExpert(String expertCode);
}
