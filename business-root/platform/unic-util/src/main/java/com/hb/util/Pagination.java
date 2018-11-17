package com.hb.util;

import java.util.ArrayList;
import java.util.List;

public class Pagination<T> {

    private List<T> data$;
    private Long entityCount$ = Long.valueOf(0L);
    private Integer from = Integer.valueOf(0);
    private Integer limit = Integer.valueOf(-1);

    public Pagination() {
    }

    public Pagination(List<T> data, Long entityCount, Integer from, Integer limit) {
        this.data$ = data;
        this.entityCount$ = entityCount;
        this.from = from;
        if (limit.intValue() > 100)
            this.limit = Integer.valueOf(100);
        else
            this.limit = limit;
    }

    public static <D> Pagination<D> list2Pagination(List<D> data, Integer from, Integer limit) {
        List dataList = null;
        if (data != null) {
            long $entityCount = data.size();
            if (($entityCount > 0L) && (from != null) && (limit != null) && (from.intValue() >= 0) &&
                    (limit
                            .intValue() > 0)) {
                Integer fromIndex = from;
                Integer toIndex = Integer.valueOf(from.intValue() + limit.intValue());
                if (fromIndex.intValue() >= $entityCount) {
                    fromIndex = Integer.valueOf(Long.valueOf($entityCount).intValue() - 1);
                }
                if (toIndex.intValue() > $entityCount) {
                    toIndex = Integer.valueOf(Long.valueOf($entityCount).intValue());
                }
                if ((fromIndex.intValue() < $entityCount) && (toIndex.intValue() <= $entityCount))
                    dataList = data.subList(fromIndex.intValue(), toIndex.intValue());
                else
                    dataList = new ArrayList();
            } else {
                dataList = data;
            }

            Pagination page = new Pagination(dataList, Long.valueOf($entityCount), from, limit);

            return page;
        }

        return null;
    }

    public void addAll(List<T> $data) {
        if (this.data$ == null) {
            this.data$ = new ArrayList();
        }
        this.data$.addAll($data);
        this.limit = Integer.valueOf(this.limit.intValue() + $data.size());
    }

    public List<T> getData$() {
        return this.data$;
    }

    public void setData$(List<T> $data) {
        this.data$ = $data;
    }

    public Long getEntityCount$() {
        return this.entityCount$;
    }

    public void setEntityCount$(Long entityCount) {
        this.entityCount$ = entityCount;
    }

    public Integer getFrom() {
        return this.from;
    }

    public void setFrom(Integer from) {
        this.from = from;
    }

    public Integer getLimit() {
        return this.limit;
    }

    public void setLimit(Integer limit) {
        if (limit.intValue() > 100)
            this.limit = Integer.valueOf(100);
        else
            this.limit = limit;
    }
}
