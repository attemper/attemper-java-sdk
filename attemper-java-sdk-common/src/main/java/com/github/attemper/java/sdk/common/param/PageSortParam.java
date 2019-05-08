package com.github.attemper.java.sdk.common.param;

import com.github.attemper.java.sdk.common.constant.SdkCommonConstants;

/**
 * 分页参数
 * @author ldang
 */
public class PageSortParam implements CommonParam {

    /**
     * 页号
     */
    protected int currentPage;

    /**
     * 每页条数
     */
    protected int pageSize;

    /**
     * 排序字段
     */
    protected String sort;

    public PageSortParam(){

    }

    public String validate() {
        if(this.pageSize > SdkCommonConstants.MAX_PAGE_SIZE){
            return "1502";
        }
        return null;
    }

    public void preHandle() {
        //preview handle currentPage
        if(this.currentPage == 0){
            this.currentPage = SdkCommonConstants.DEF_CURRENT_PAGE;
        }
        //preview handle pageSize
        if(this.pageSize == 0){
            this.pageSize = SdkCommonConstants.DEF_PAGE_SIZE;
        }
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public PageSortParam setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
        return this;
    }

    public int getPageSize() {
        return pageSize;
    }

    public PageSortParam setPageSize(int pageSize) {
        this.pageSize = pageSize;
        return this;
    }

    public String getSort() {
        return sort;
    }

    public PageSortParam setSort(String sort) {
        this.sort = sort;
        return this;
    }
}
