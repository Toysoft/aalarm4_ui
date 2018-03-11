package net.kprod.aalarmui.bean;

import net.kprod.aalarmui.enums.EnumEventStatus;

/**
 * Created by kemkem on 3/1/18.
 */
public class Status {
    private EnumEventStatus status;
    private String uiStyle;

    public EnumEventStatus getStatus() {
        return status;
    }

    public Status setStatus(EnumEventStatus status) {
        this.status = status;
        return this;
    }

    public String getUiStyle() {
        return uiStyle;
    }

    public Status setUiStyle(String uiStyle) {
        this.uiStyle = uiStyle;
        return this;
    }
}
