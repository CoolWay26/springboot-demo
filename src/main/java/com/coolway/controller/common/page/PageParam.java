package com.coolway.controller.common.page;

import com.github.pagehelper.IPage;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 注解@Accessors实现 Entity伪Build 如: entity.setX(x).setY(y)
 *
 * @param <T>
 */
@Data
@Accessors(chain = true)
public class PageParam<T> implements IPage {

    /** description = "页码", defaultValue =  1 */
    private Integer pageNum = 1;

    /** description = "页数", defaultValue = 20 */
    private Integer pageSize = 20;

    /** description = "排序", example = "id desc" */
    private String orderBy;

    /** description = "查询条件参数" */
    private T param;

    /**此处可优化 优化详情且看解析*/
    public PageParam<T> setOrderBy(String orderBy) {
        this.orderBy = orderBy;
        return this;
    }
}