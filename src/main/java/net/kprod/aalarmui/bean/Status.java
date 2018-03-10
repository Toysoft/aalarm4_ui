package net.kprod.aalarmui.bean;

import net.kprod.aalarmui.enums.EnumEventStatus;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Created by kemkem on 3/1/18.
 */
public class Status {
    private EnumEventStatus status;
    private String context;

    public EnumEventStatus getStatus() {
        return status;
    }

    public Status setStatus(EnumEventStatus status) {
        this.status = status;
        return this;
    }

    public String getContext() {
        return context;
    }

    public Status setContext(String context) {
        this.context = context;
        return this;
    }
}
