package net.kprod.aalarmui.bean;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import net.kprod.aalarmui.utils.JsonDateSerializer;

import java.time.LocalDateTime;

/**
 * Created by kemkem on 3/1/18.
 */
public class Motion {
    private Long id;
    private LocalDateTime dateEvent;
    private String filename;
    private Long idEvent;

    public Long getId() {
        return id;
    }

    public Motion setId(Long id) {
        this.id = id;
        return this;
    }

    @JsonSerialize(using = JsonDateSerializer.class)
    public LocalDateTime getDateEvent() {
        return dateEvent;
    }

    public Motion setDateEvent(LocalDateTime dateEvent) {
        this.dateEvent = dateEvent;
        return this;
    }

    public String getFilename() {
        return filename;
    }

    public Motion setFilename(String filename) {
        this.filename = filename;
        return this;
    }

    public Long getIdEvent() {
        return idEvent;
    }

    public Motion setIdEvent(Long idEvent) {
        this.idEvent = idEvent;
        return this;
    }
}
