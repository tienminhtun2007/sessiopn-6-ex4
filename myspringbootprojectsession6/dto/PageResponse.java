package com.example.myspringbootprojectsession6.dto;

import java.util.List;

public class PageResponse<T> {

    private List<T> items;
    private Integer page;
    private Integer size;
    private Long totalItems;
    private Integer totalPages;
    private Boolean isLast;

    public PageResponse(List<T> items, Integer page, Integer size, Long totalItems,
                        Integer totalPages, Boolean isLast) {
        this.items = items;
        this.page = page;
        this.size = size;
        this.totalItems = totalItems;
        this.totalPages = totalPages;
        this.isLast = isLast;
    }

    public List<T> getItems() {
        return items;
    }

    public Integer getPage() {
        return page;
    }

    public Integer getSize() {
        return size;
    }

    public Long getTotalItems() {
        return totalItems;
    }

    public Integer getTotalPages() {
        return totalPages;
    }

    public Boolean getIsLast() {
        return isLast;
    }
}
