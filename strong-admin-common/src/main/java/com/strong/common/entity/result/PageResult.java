package com.strong.common.entity.result;

import java.io.Serializable;
import java.util.List;

/**
 * @author charlie
 * @date 2025/3/17 17:07
 **/
public class PageResult<T> implements Serializable {

    private static final long serialVersionUID = 11044623130242636L;

    private int pageNum;
    private int pageSize;
    private long totalSize;
    private int totalPages;
    private List<T> content;

    public PageResult() {
    }

    public int getPageNum() {
        return this.pageNum;
    }

    public int getPageSize() {
        return this.pageSize;
    }

    public long getTotalSize() {
        return this.totalSize;
    }

    public int getTotalPages() {
        return this.totalPages;
    }

    public List<T> getContent() {
        return this.content;
    }

    public void setPageNum(final int pageNum) {
        this.pageNum = pageNum;
    }

    public void setPageSize(final int pageSize) {
        this.pageSize = pageSize;
    }

    public void setTotalSize(final long totalSize) {
        this.totalSize = totalSize;
    }

    public void setTotalPages(final int totalPages) {
        this.totalPages = totalPages;
    }

    public void setContent(final List<T> content) {
        this.content = content;
    }

    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        } else if (!(o instanceof PageResult)) {
            return false;
        } else {
            PageResult<?> other = (PageResult)o;
            if (!other.canEqual(this)) {
                return false;
            } else if (this.getPageNum() != other.getPageNum()) {
                return false;
            } else if (this.getPageSize() != other.getPageSize()) {
                return false;
            } else if (this.getTotalSize() != other.getTotalSize()) {
                return false;
            } else if (this.getTotalPages() != other.getTotalPages()) {
                return false;
            } else {
                Object this$content = this.getContent();
                Object other$content = other.getContent();
                if (this$content == null) {
                    if (other$content != null) {
                        return false;
                    }
                } else if (!this$content.equals(other$content)) {
                    return false;
                }

                return true;
            }
        }
    }

    protected boolean canEqual(final Object other) {
        return other instanceof PageResult;
    }

    public int hashCode() {
        int result = 1;
        result = result * 59 + this.getPageNum();
        result = result * 59 + this.getPageSize();
        long $totalSize = this.getTotalSize();
        result = result * 59 + (int)($totalSize >>> 32 ^ $totalSize);
        result = result * 59 + this.getTotalPages();
        Object $content = this.getContent();
        result = result * 59 + ($content == null ? 43 : $content.hashCode());
        return result;
    }

    public String toString() {
        return "PageResult(pageNum=" + this.getPageNum() + ", pageSize=" + this.getPageSize() + ", totalSize=" + this.getTotalSize() + ", totalPages=" + this.getTotalPages() + ", content=" + this.getContent() + ")";
    }
}


