package cc.mrbird.febs.cos.service;

import cc.mrbird.febs.cos.entity.DifficultiesInfo;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import org.apache.ibatis.annotations.Param;

import java.util.LinkedHashMap;

/**
 * @author FanK
 */
public interface IDifficultiesInfoService extends IService<DifficultiesInfo> {

    /**
     * 分页获取技术难点信息
     *
     * @param page 分页对象
     * @param difficultiesInfo 技术难点信息
     * @return 结果
     */
    IPage<LinkedHashMap<String, Object>> selectDiffcultiesPage(Page<DifficultiesInfo> page, DifficultiesInfo difficultiesInfo);
}
