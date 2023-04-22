package cc.mrbird.febs.cos.service.impl;

import cc.mrbird.febs.cos.entity.DifficultiesInfo;
import cc.mrbird.febs.cos.dao.DifficultiesInfoMapper;
import cc.mrbird.febs.cos.service.IDifficultiesInfoService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;

/**
 * @author FanK
 */
@Service
public class DifficultiesInfoServiceImpl extends ServiceImpl<DifficultiesInfoMapper, DifficultiesInfo> implements IDifficultiesInfoService {

    /**
     * 分页获取技术难点信息
     *
     * @param page 分页对象
     * @param difficultiesInfo 技术难点信息
     * @return 结果
     */
    @Override
    public IPage<LinkedHashMap<String, Object>> selectDiffcultiesPage(Page<DifficultiesInfo> page, DifficultiesInfo difficultiesInfo) {
        return baseMapper.selectDiffcultiesPage(page, difficultiesInfo);
    }
}
