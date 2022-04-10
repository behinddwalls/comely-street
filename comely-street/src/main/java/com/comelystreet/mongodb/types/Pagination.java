package com.comelystreet.mongodb.types;

public class Pagination {
    private int offSet;
    private int pageSize;
    private long totalCount;
    private boolean showNextButton;
    private boolean showPrevButton;
    private PaginationAction paginationAction;

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public long getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(long totalCount) {
        this.totalCount = totalCount;
    }

    public boolean isShowNextButton() {
        return showNextButton;
    }

    public void setShowNextButton(boolean showNextButton) {
        this.showNextButton = showNextButton;
    }

    public boolean isShowPrevButton() {
        return showPrevButton;
    }

    public void setShowPrevButton(boolean showPrevButton) {
        this.showPrevButton = showPrevButton;
    }

    public PaginationAction getPaginationAction() {
        return paginationAction;
    }

    public void setPaginationAction(PaginationAction paginationAction) {
        this.paginationAction = paginationAction;
    }

    public int getOffSet() {
        return offSet;
    }

    public void setOffSet(int offSet) {
        this.offSet = offSet;
    }

    @Override
    public String toString() {
        return "Pagination [offSet=" + offSet + ", pageSize=" + pageSize + ", totalCount=" + totalCount
                + ", showNextButton=" + showNextButton + ", showPrevButton=" + showPrevButton + ", paginationAction="
                + paginationAction + "]";
    }
}
