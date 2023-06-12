package com.coolway.common.page;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * @param <Param>  泛型request
 * @param <Result> 泛型response
 */
public interface PageService<Param, Result> {
    /**
     * 分页查询
     * default关键字直接声明默认实现
     *
     * @param param 请求参数DTO
     * @return 分页集合
     */
    default PageInfo<Result> page(PageParam<Param> param) {
        return PageHelper.startPage(param).doSelectPageInfo(() -> list(param.getParam()));
    }

    /**
     * 集合查询
     *
     * @param param 查询参数
     * @return 查询响应
     */
    List<Result> list(Param param);
}
