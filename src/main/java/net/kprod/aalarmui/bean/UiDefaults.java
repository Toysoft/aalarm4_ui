package net.kprod.aalarmui.bean;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Created by kemkem on 3/1/18.
 */
public class UiDefaults {
    private String dateFrom;
    private String dateTo;

    public UiDefaults() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

        LocalDateTime defaultDateFrom = LocalDateTime.now().minusDays(1);
        dateFrom = defaultDateFrom.format(formatter);

        LocalDateTime defaultDateTo = LocalDateTime.now();
        dateTo = defaultDateTo.format(formatter);
    }

    public String getDateFrom() {
        return dateFrom;
    }

    public UiDefaults setDateFrom(String dateFrom) {
        this.dateFrom = dateFrom;
        return this;
    }

    public String getDateTo() {
        return dateTo;
    }

    public UiDefaults setDateTo(String dateTo) {
        this.dateTo = dateTo;
        return this;
    }
}
