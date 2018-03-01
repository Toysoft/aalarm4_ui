package net.kprod.aalarmui.db.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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

    private String path;

    public EntityEventMotion() {
    }

    public EntityEventMotion(LocalDateTime dateEvent, String path) {
        this.dateEvent = dateEvent;
        this.path = path;
    }
}
