package cc.mrbird.febs.cos.entity.vo;

import cc.mrbird.febs.cos.entity.ExpertProduct;
import cc.mrbird.febs.cos.entity.ExpertRecommend;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * 专家推荐
 *
 * @author FanK
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class ExpertRecommendVo extends ExpertRecommend {

    List<ExpertProduct> productList;
}
