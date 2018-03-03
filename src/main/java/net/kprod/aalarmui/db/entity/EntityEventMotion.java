package net.kprod.aalarmui.db.entity;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * Created by kemkem on 11/12/17.
 */
@Entity
public class EntityEventMotion {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private LocalDateTime dateEvent;
    private String filename;

    @OneToOne
    private EntityEvent entityEvent;

    public EntityEventMotion() {
    }

    public EntityEventMotion(EntityEvent entityEvent, LocalDateTime dateEvent, String filename) {
        this.entityEvent = entityEvent;
        this.dateEvent = dateEvent;
        this.filename = filename;
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

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }
}
