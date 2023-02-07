package cc.mrbird.febs.cos.controller;


import cc.mrbird.febs.common.utils.R;
import cc.mrbird.febs.cos.entity.NotifyInfo;
import cc.mrbird.febs.cos.service.INotifyInfoService;
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
@RequestMapping("/cos/notify-info")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class NotifyInfoController {

    private final INotifyInfoService notifyInfoService;

    /**
     * 分页获取消息通知信息
     *
     * @param page       分页对象
     * @param notifyInfo 消息通知信息
     * @return 结果
     */
    @GetMapping("/page")
    public R page(Page<NotifyInfo> page, NotifyInfo notifyInfo) {
        return R.ok(notifyInfoService.selectNotifyPage(page, notifyInfo));
    }

    /**
     * 根据用户ID获取通知消息
     *
     * @param userId 用户ID
     * @return 结果
     */
    @GetMapping("/list/{userId}")
    public R selectNotifyByUserId(@PathVariable("userId") Integer userId) {
        return R.ok(notifyInfoService.selectNotifyByUserId(userId));
    }

    @GetMapping("/{id}")
    public R detail(@PathVariable("id") Integer id) {
        return R.ok(notifyInfoService.getById(id));
    }

    @GetMapping("/list")
    public R list() {
        return R.ok(notifyInfoService.list());
    }

    /**
     * 新增消息通知信息
     *
     * @param notifyInfo 消息通知信息
     * @return 结果
     */
    @PostMapping
    public R save(NotifyInfo notifyInfo) {
        notifyInfo.setCreateDate(DateUtil.formatDateTime(new Date()));
        return R.ok(notifyInfoService.save(notifyInfo));
    }

    /**
     * 修改消息通知信息
     *
     * @param notifyInfo 消息通知信息
     * @return 结果
     */
    @PutMapping
    public R edit(NotifyInfo notifyInfo) {
        return R.ok(notifyInfoService.updateById(notifyInfo));
    }

    /**
     * 删除消息通知信息
     *
     * @param ids ids
     * @return 消息通知信息
     */
    @DeleteMapping("/{ids}")
    public R deleteByIds(@PathVariable("ids") List<Integer> ids) {
        return R.ok(notifyInfoService.removeByIds(ids));
    }

}
