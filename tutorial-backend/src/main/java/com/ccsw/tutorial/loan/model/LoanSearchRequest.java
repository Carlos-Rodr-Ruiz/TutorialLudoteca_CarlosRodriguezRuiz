// LoanSearchRequest.java
package com.ccsw.tutorial.loan.model;

import com.ccsw.tutorial.common.pagination.PageableRequest;

public class LoanSearchRequest {

    private PageableRequest pageable;
    private LoanSearchDto filters;

    public PageableRequest getPageable() {
        return pageable;
    }

    public void setPageable(PageableRequest pageable) {
        this.pageable = pageable;
    }

    public LoanSearchDto getFilters() {
        return filters;
    }

    public void setFilters(LoanSearchDto filters) {
        this.filters = filters;
    }
}
