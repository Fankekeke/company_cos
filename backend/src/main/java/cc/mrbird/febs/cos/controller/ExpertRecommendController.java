package cc.mrbird.febs.cos.controller;


import cc.mrbird.febs.common.utils.R;
import cc.mrbird.febs.cos.entity.ExpertRecommend;
import cc.mrbird.febs.cos.entity.vo.ExpertRecommendVo;
import cc.mrbird.febs.cos.service.IExpertRecommendService;
import cn.hutool.core.date.DateUtil;
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
@RequestMapping("/cos/expert-recommend")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ExpertRecommendController {

    private final IExpertRecommendService expertRecommendService;

    /**
     * 分页获取专家推荐信息
     *
     * @param page            分页对象
     * @param expertRecommend 专家推荐信息
     * @return 结果
     */
    @GetMapping("/page")
    public R page(Page<ExpertRecommend> page, ExpertRecommend expertRecommend) {
        return R.ok(expertRecommendService.selectExpertRecommendPage(page, expertRecommend));
    }

    /**
     * 专家推荐审核
     *
     * @param auditId 审核ID
     * @param status  审核结果
     * @return 结果
     */
    @GetMapping("/audit")
    public R auditExpertRecommend(@RequestParam("auditId") Integer auditId, @RequestParam("status") Integer status) {
        return R.ok(expertRecommendService.auditExpertRecommend(auditId, status));
    }

    /**
     * 审核通过后发布
     *
     * @param auditId 审核ID
     * @return 结果
     */
    @GetMapping("/release/{auditId}")
    public R release(@PathVariable("auditId") Integer auditId) {
        return R.ok(expertRecommendService.release(auditId));
    }

    @GetMapping("/{id}")
    public R detail(@PathVariable("id") Integer id) {
        return R.ok(expertRecommendService.getById(id));
    }

    @GetMapping("/list")
    public R list() {
        return R.ok(expertRecommendService.list());
    }

    /**
     * 新增专家推荐信息
     *
     * @param expertRecommend 专家推荐信息
     * @return 结果
     */
    @PostMapping
    public R save(ExpertRecommendVo expertRecommend) {
        expertRecommend.setCreateDate(DateUtil.formatDateTime(new Date()));
        return R.ok(expertRecommendService.recommendBatch(expertRecommend));
    }

    /**
     * 修改专家推荐信息
     *
     * @param expertRecommend 专家推荐信息
     * @return 结果
     */
    @PutMapping
    public R edit(ExpertRecommend expertRecommend) {
        return R.ok(expertRecommendService.updateById(expertRecommend));
    }

    /**
     * 删除专家推荐信息
     *
     * @param ids ids
     * @return 专家推荐信息
     */
    @DeleteMapping("/{ids}")
    public R deleteByIds(@PathVariable("ids") List<Integer> ids) {
        return R.ok(expertRecommendService.removeByIds(ids));
    }

}
