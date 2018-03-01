package net.kprod.aalarmui.db.entity;

import net.kprod.aalarmui.enums.EnumEventStatus;
import net.kprod.aalarmui.enums.EnumEventType;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * Created by kemkem on 11/12/17.
 */
@Entity
public class EntityEvent {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private LocalDateTime dateEvent;

    private String emmiterId;

    @Enumerated(EnumType.STRING)
    private EnumEventType enumEventType;

    @Enumerated(EnumType.STRING)
    private EnumEventStatus enumSensorStatus;

    public EntityEvent() {
    }

    public EntityEvent(LocalDateTime dateEvent, String emmiterId, EnumEventStatus enumSensorStatus) {
        this.dateEvent = dateEvent;
        this.emmiterId = emmiterId;
        this.enumSensorStatus = enumSensorStatus;
        this.enumEventType = enumSensorStatus.getSensorType();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getDateEvent() {
        return dateEvent;
    }

    public void setDateEvent(LocalDateTime dateEvent) {
        this.dateEvent = dateEvent;
    }

    public String getEmmiterId() {
        return emmiterId;
    }

    public void setEmmiterId(String emmiterId) {
        this.emmiterId = emmiterId;
    }

    public EnumEventStatus getEnumSensorStatus() {
        return enumSensorStatus;
    }

    public void setEnumSensorStatus(EnumEventStatus enumSensorStatus) {
        this.enumSensorStatus = enumSensorStatus;
    }

    public EnumEventType getEnumEventType() {
        return enumEventType;
    }

    public EntityEvent setEnumEventType(EnumEventType enumEventType) {
        this.enumEventType = enumEventType;
        return this;
    }
}
