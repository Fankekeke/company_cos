package cc.mrbird.febs.cos.service;

import cc.mrbird.febs.cos.entity.ChatInfo;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import org.apache.ibatis.annotations.Param;

import java.util.LinkedHashMap;

/**
 * @author FanK
 */
public interface IChatInfoService extends IService<ChatInfo> {

    /**
     * 分页获取技术沟通信息
     *
     * @param page     分页对象
     * @param chatInfo 技术沟通信息
     * @return 结果
     */
    IPage<LinkedHashMap<String, Object>> selectChatPage(Page<ChatInfo> page, ChatInfo chatInfo);
}
