package com.comelystreet.services.util;

import com.comelystreet.mongodb.types.Pagination;

public final class PaginationUtility {

    public static Pagination getOptimizedPagination(final Pagination pagination) {

        if (pagination == null)
            return null;

        if (pagination.getPageSize() > 10 || pagination.getPageSize() <= 0) {
            pagination.setPageSize(10);
        }

        long pageNumber = pagination.getOffSet() / pagination.getPageSize();
        Double totalPages = 0.0;
        if (pagination.getTotalCount() > pagination.getPageSize())
            totalPages = Math.ceil((double) pagination.getTotalCount() / (double) pagination.getPageSize());
        else
            totalPages = (double) pagination.getTotalCount() / pagination.getPageSize();

        System.out.println(totalPages);
        System.out.println(pageNumber);
        if (pageNumber > 0) {
            pagination.setShowPrevButton(true);
        } else {
            pagination.setShowPrevButton(false);
        }
        if (totalPages.longValue() > pageNumber + 1) {
            pagination.setShowNextButton(true);
        } else {
            pagination.setShowNextButton(false);
        }

        // if (PaginationAction.PREV.equals(pagination.getPaginationAction())) {
        // if (pageNumber > 0) {
        // pagination.setShowPrevButton(true);
        // } else {
        // pagination.setShowPrevButton(false);
        // }
        // if (totalPages.longValue() > pageNumber + 1) {
        // pagination.setShowNextButton(true);
        // } else {
        // pagination.setShowNextButton(false);
        // }
        // } else {
        // System.out.println(totalPages.longValue());
        // System.out.println(pageNumber);
        // if (totalPages.longValue() > pageNumber + 1) {
        // pagination.setShowNextButton(true);
        // } else {
        // pagination.setShowNextButton(false);
        // }
        // if (pageNumber > 0) {
        // pagination.setShowPrevButton(true);
        // } else {
        // pagination.setShowPrevButton(false);
        // }
        // }
        return pagination;
    }
}
