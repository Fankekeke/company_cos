package cc.mrbird.febs.cos.controller;


import cc.mrbird.febs.common.utils.R;
import cc.mrbird.febs.cos.entity.DifficultiesInfo;
import cc.mrbird.febs.cos.service.IDifficultiesInfoService;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

/**
 * @author FanK
 */
@RestController
@RequestMapping("/cos/difficulties-info")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class DifficultiesInfoController {

    private final IDifficultiesInfoService difficultiesInfoService;

    /**
     * 分页获取技术难点信息
     *
     * @param page 分页对象
     * @param difficultiesInfo 技术难点信息
     * @return 结果
     */
    @GetMapping("/page")
    public R page(Page<DifficultiesInfo> page, DifficultiesInfo difficultiesInfo) {
        return R.ok(difficultiesInfoService.page(page, Wrappers.<DifficultiesInfo> lambdaQuery()
                .like(StrUtil.isNotEmpty(difficultiesInfo.getName()), DifficultiesInfo::getName, difficultiesInfo.getName())
                .like(StrUtil.isNotEmpty(difficultiesInfo.getType()), DifficultiesInfo::getType, difficultiesInfo.getType())
                .eq(difficultiesInfo.getUserId() != null, DifficultiesInfo::getUserId, difficultiesInfo.getUserId())));
    }

    @GetMapping("/{id}")
    public R detail(@PathVariable("id") Integer id) {
        return R.ok(difficultiesInfoService.getById(id));
    }

    @GetMapping("/list")
    public R list() {
        return R.ok(difficultiesInfoService.list());
    }

    /**
     * 新增技术难点信息
     *
     * @param difficultiesInfo 技术难点信息
     * @return 结果
     */
    @PostMapping
    public R save(DifficultiesInfo difficultiesInfo) {
        difficultiesInfo.setCreateDate(DateUtil.formatDateTime(new Date()));
        return R.ok(difficultiesInfoService.save(difficultiesInfo));
    }

    /**
     * 修改技术难点信息
     *
     * @param difficultiesInfo 技术难点信息
     * @return 结果
     */
    @PutMapping
    public R edit(DifficultiesInfo difficultiesInfo) {
        return R.ok(difficultiesInfoService.updateById(difficultiesInfo));
    }

    /**
     * 删除技术难点信息
     *
     * @param ids ids
     * @return 技术难点信息
     */
    @DeleteMapping("/{ids}")
    public R deleteByIds(@PathVariable("ids") List<Integer> ids) {
        return R.ok(difficultiesInfoService.removeByIds(ids));
    }

}
