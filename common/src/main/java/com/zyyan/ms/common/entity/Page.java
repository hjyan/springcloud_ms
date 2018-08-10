package com.zyyan.ms.common.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * 分页对象
 *
 * @author Hui Wang
 */
@SuppressWarnings("rawtypes")
public class Page {

    /**
     * 正序
     */
    public static final String ASC = "ASC";
    /**
     * 倒序
     */
    public static final String DESC = "DESC";
    /**
     * 当前第几页，默认值为1，既第一页.
     */
    protected int page = 1;
    /**
     * 每页最大记录数，默认值为0，表示total不可用.
     */
    protected int pageSize = 0;
    /**
     * 排序字段名.
     */
    protected String orderBy = null;
    /**
     * 使用正序还是倒序.
     */
    protected String order = ASC;
    /**
     * 查询结果.
     */
    protected Object rows = null;
    /**
     * 总记录数，默认值为-1，表示records不可用.
     */
    protected int records = -1;
    /**
     * 是否计算数据库中的记录总数.
     */
    protected boolean autoCount = false;
    /**
     * 当前页第一条记录的索引，默认值为0，既第一页第一条记录.
     */
    protected int start = 0;
    /**
     * 总页数，默认值为-1，表示total不可用.
     */
    protected int total = -1;

    /**
     * 构造方法.
     */
	public Page() {
        records = 0;
        rows = new ArrayList();
    }

    /**
     * 构造方法.
     *
     * @param rows Object
     * @param records long
     */
    public Page(Object rows, int records) {
        this.rows = rows;
        this.records = records;
    }

    /**
     * 构造方法.
     *
     * @param page 页号
     * @param pageSize 每页记录数
     * @param orderBy String
     * @param order String
     */
    public Page(int page, int pageSize, String orderBy, String order) {
        this.page = page;
        this.pageSize = pageSize;
        this.orderBy = orderBy;
        this.setOrder(order);
        this.calculateStart();
    }

    /**
     * 是否为正序排序.
     *
     * @return boolean
     */
    public boolean isAsc() {
        return !DESC.equalsIgnoreCase(order);
    }

    /**
     * 取得倒转的排序方向.
     *
     * @return 如果dir=='ASC'就返回'DESC'，如果dir='DESC'就返回'ASC'
     */
    public String getInverseOrder() {
        if (DESC.equalsIgnoreCase(order)) {
            return ASC;
        } else {
            return DESC;
        }
    }

    /**
     * 页面显示最大记录数是否可用.
     *
     * @return pageSize > 0
     */
    public boolean isPageSizeEnabled() {
        return pageSize > 0;
    }

    /**
     * 页面第一条记录的索引是否可用.
     *
     * @return start
     */
    public boolean isStartEnabled() {
        return start >= 0;
    }

    /**
     * 排序是否可用.
     *
     * @return orderBy是否非空
     */
    public boolean isOrderEnabled() {
        return (orderBy != null) && (orderBy.trim().length() != 0);
    }

    /**
     * 是否有前一页.
     *
     * @return boolean
     */
    public boolean isPreviousEnabled() {
        return page > 1;
    }

    /**
     * 是否有后一页.
     *
     * @return boolean
     */
    public boolean isNextEnabled() {
        return page < total;
    }

    /**
     * 总页数是否可用.
     *
     * @return boolean
     */
    public boolean isTotalEnabled() {
        return total >= 0;
    }

    /**
     * 计算本页第一条记录的索引.
     */
    private void calculateStart() {
        if ((page < 1) || (pageSize < 1)) {
            start = -1;
        } else {
            start = (page - 1) * pageSize;
        }
    }

    /**
     * 计算最大页数.
     */
    public void calculateTotal() {
        if ((records < 0) || (pageSize < 1)) {
            total = -1;
        } else {
            total = ((records - 1) / pageSize) + 1;
        }
    }

    /**
     * 获取页号
     *
     * @return page.
     */
    public int getPage() {
        return page;
    }

    /**
     * 设置页号
     *
     * @param page
     */
    public void setPage(int page) {
        this.page = page;
        this.calculateStart();
    }

    /**
     * 获取每页记录数
     *
     * @return total.
     */
    public int getPageSize() {
        return pageSize;
    }

    /**
     * 设置每页记录数
     *
     * @param pageSize long.
     */
    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
        this.calculateStart();
        this.calculateTotal();
    }

    /**
     * @return orderBy.
     */
    public String getOrderBy() {
        return orderBy;
    }

    /**
     * @param orderBy String.
     */
    public void setOrderBy(String orderBy) {
        this.orderBy = orderBy;
    }

    /**
     * @return order.
     */
    public String getOrder() {
        return order;
    }

    /**
     * @param order String.
     */
    public void setOrder(String order) {
        if (ASC.equalsIgnoreCase(order) || DESC.equalsIgnoreCase(order)) {
            this.order = order.toUpperCase(Locale.CHINA);
        } else {
            throw new IllegalArgumentException(
                    "order should be 'DESC' or 'ASC'");
        }
    }

    /**
     * 获取查询结果
     *
     * @return rows.
     */
    public Object getRows() {
        return rows;
    }

    /**
     * 设置查询结果
     *
     * @param rows Object.
     */
    public void setRows(Object rows) {
        this.rows = rows;
    }

    /**
     * 获取总记录数
     *
     * @return records.
     */
    public int getRecords() {
        return records;
    }

    /**
     * 设置总记录数
     *
     * @param records long.
     */
    public void setRecords(int records) {
        this.records = records;
        this.calculateTotal();
    }

    /**
     * @return autoCount.
     */
    public boolean isAutoCount() {
        return autoCount;
    }

    /**
     * @param autoCount boolean.
     */
    public void setAutoCount(boolean autoCount) {
        this.autoCount = autoCount;
    }

    /**
     * @return start.
     */
    public int getStart() {
        return start;
    }

    /**
     * @return pageCount.
     */
    public int getTotal() {
        return total;
    }

    /**
     * 封装page对象，add by zlzuo 140304
     *
     * @param list
     * @param totalCount
     * @param pageNo
     * @param pageSize
     */
    public void setPageData(List list, int totalCount, int pageNo, int pageSize) {
        this.setRows(list);
        this.setRecords(totalCount);
        this.setPageSize(pageSize);
        this.setPage(pageNo);
    }
    public void setPageData(List list, int totalCount) {
        this.setRows(list);
        this.setRecords(totalCount);
    }
}
