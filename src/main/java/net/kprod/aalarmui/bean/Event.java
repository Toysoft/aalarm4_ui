package net.kprod.aalarmui.bean;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import net.kprod.aalarmui.enums.EnumEventStatus;
import net.kprod.aalarmui.enums.EnumEventType;
import net.kprod.aalarmui.utils.JsonDateSerializer;

import java.time.LocalDateTime;

/**
 * Created by kemkem on 3/1/18.
 */
public class Event {
    private Long id;
    private String emmiterId;
    private EnumEventType eventType;
    private EnumEventStatus eventStatus;
    private LocalDateTime dateEvent;

    public Long getId() {
        return id;
    }

    public Event setId(Long id) {
        this.id = id;
        return this;
    }

    public String getEmmiterId() {
        return emmiterId;
    }

    public Event setEmmiterId(String emmiterId) {
        this.emmiterId = emmiterId;
        return this;
    }

    public EnumEventType getEventType() {
        return eventType;
    }

    public Event setEventType(EnumEventType eventType) {
        this.eventType = eventType;
        return this;
    }

    @JsonSerialize(using = JsonDateSerializer.class)
    public LocalDateTime getDateEvent() {
        return dateEvent;
    }

    public Event setDateEvent(LocalDateTime dateEvent) {
        this.dateEvent = dateEvent;
        return this;
    }

    public EnumEventStatus getEventStatus() {
        return eventStatus;
    }

    public Event setEventStatus(EnumEventStatus eventStatus) {
        this.eventStatus = eventStatus;
        return this;
    }
}
