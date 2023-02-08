package cc.mrbird.febs.cos.controller;


import cc.mrbird.febs.common.utils.FileDownloadUtils;
import cc.mrbird.febs.common.utils.R;
import cc.mrbird.febs.cos.entity.ExpertInfo;
import cc.mrbird.febs.cos.service.IExpertInfoService;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.List;

/**
 * @author FanK
 */
@RestController
@RequestMapping("/cos/expert-info")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ExpertInfoController {

    private final IExpertInfoService expertInfoService;

    /**
     * 分页获取专家信息
     *
     * @param page       分页对象
     * @param expertInfo 专家信息
     * @return 结果
     */
    @GetMapping("/page")
    public R page(Page<ExpertInfo> page, ExpertInfo expertInfo) {
        return R.ok(expertInfoService.selectExpertPage(page, expertInfo));
    }

    /**
     * 下载模板
     */
    @PostMapping("/template")
    public void downloadTemplate(HttpServletResponse response) {
        try {
            FileDownloadUtils.downloadTemplate(response, "专家基础数据.xlsx");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 导入专家信息列表
     */
    @PostMapping("/import")
    public R importExcel(@RequestParam("file") MultipartFile file) {
        try {
            String errorMsg = expertInfoService.importExcel(file);
            if (StrUtil.isNotEmpty(errorMsg)) {
                return R.error(errorMsg);
            }
            return R.ok();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return R.error("导入异常");
    }

    /**
     * 根据企业编号获取推荐专家
     *
     * @param enterpriseCode 企业编号
     * @return 结果
     */
    @GetMapping("/recommend/{enterpriseCode}")
    public R selectExpertRecommend(@PathVariable("enterpriseCode") String enterpriseCode) {
        return R.ok(expertInfoService.selectExpertRecommend(enterpriseCode));
    }

    @GetMapping("/{id}")
    public R detail(@PathVariable("id") Integer id) {
        return R.ok(expertInfoService.getById(id));
    }

    @GetMapping("/list")
    public R list() {
        return R.ok(expertInfoService.list());
    }

    /**
     * 新增专家信息
     *
     * @param expertInfo 专家信息
     * @return 结果
     */
    @PostMapping
    public R save(ExpertInfo expertInfo) {
        expertInfo.setCreateDate(DateUtil.formatDateTime(new Date()));
        expertInfo.setOpenFlag(1);
        expertInfo.setHasExist(1);
        return R.ok(expertInfoService.save(expertInfo));
    }

    /**
     * 修改专家信息
     *
     * @param expertInfo 专家信息
     * @return 结果
     */
    @PutMapping
    public R edit(ExpertInfo expertInfo) {
        return R.ok(expertInfoService.updateById(expertInfo));
    }

    /**
     * 删除专家信息
     *
     * @param ids ids
     * @return 专家信息
     */
    @DeleteMapping("/{ids}")
    public R deleteByIds(@PathVariable("ids") List<Integer> ids) {
        return R.ok(expertInfoService.removeByIds(ids));
    }

}
