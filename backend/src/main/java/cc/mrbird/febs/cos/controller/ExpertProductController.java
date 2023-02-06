package cc.mrbird.febs.cos.controller;


import cc.mrbird.febs.common.utils.R;
import cc.mrbird.febs.cos.entity.ExpertProduct;
import cc.mrbird.febs.cos.service.IExpertProductService;
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
@RequestMapping("/cos/expert-product")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ExpertProductController {

    private final IExpertProductService expertProductService;

    /**
     * 分页获取项目成果信息
     *
     * @param page 分页对象
     * @param expertProduct 项目成果信息
     * @return 结果
     */
    @GetMapping("/page")
    public R page(Page<ExpertProduct> page, ExpertProduct expertProduct) {
        return R.ok();
    }

    @GetMapping("/{id}")
    public R detail(@PathVariable("id") Integer id) {
        return R.ok(expertProductService.getById(id));
    }

    @GetMapping("/list")
    public R list() {
        return R.ok(expertProductService.list());
    }

    /**
     * 新增项目成果信息
     *
     * @param expertProduct 项目成果信息
     * @return 结果
     */
    @PostMapping
    public R save(ExpertProduct expertProduct) {
        expertProduct.setCreateDate(DateUtil.formatDateTime(new Date()));
        return R.ok(expertProductService.save(expertProduct));
    }

    /**
     * 修改项目成果信息
     *
     * @param expertProduct 项目成果信息
     * @return 结果
     */
    @PutMapping
    public R edit(ExpertProduct expertProduct) {
        return R.ok(expertProductService.updateById(expertProduct));
    }

    /**
     * 删除项目成果信息
     *
     * @param ids ids
     * @return 项目成果信息
     */
    @DeleteMapping("/{ids}")
    public R deleteByIds(@PathVariable("ids") List<Integer> ids) {
        return R.ok(expertProductService.removeByIds(ids));
    }

}
