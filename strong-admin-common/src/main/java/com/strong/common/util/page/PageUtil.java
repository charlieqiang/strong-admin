package com.strong.common.util.page;

import com.github.pagehelper.PageInfo;
import com.strong.common.entity.result.PageResult;

import java.util.List;

/**
 * @author charlie
 */
public class PageUtil {

    /**
     * 如果对查询出来的list进行过转换必须调用该方法
     *
     * @param pageInfo 原始的list
     * @param voList 把entity转成vo后的list
     * @return
     */
    public static <T> PageResult<T> getPageResult(PageInfo<?> pageInfo, List<T> voList) {
        PageResult pageResult = new PageResult();
        pageResult.setPageNum(pageInfo.getPageNum());
        pageResult.setPageSize(pageInfo.getPageSize());
        pageResult.setTotalSize(pageInfo.getTotal());
        pageResult.setTotalPages(pageInfo.getPages());
        pageResult.setContent(voList);
        return pageResult;
    }

}