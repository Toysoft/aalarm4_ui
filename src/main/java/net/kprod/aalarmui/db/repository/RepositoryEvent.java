package net.kprod.aalarmui.db.repository;

import net.kprod.aalarmui.db.entity.EntityEvent;
import net.kprod.aalarmui.enums.EnumEventType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by kemkem on 5/1/17.
 */
@Repository
public interface RepositoryEvent extends JpaRepository<EntityEvent, Long> {
    EntityEvent findFirstByIdIsNotNullOrderByIdDesc();
    EntityEvent findFirstByEnumEventTypeIsOrderByIdDesc(EnumEventType enumEventType);
    List<EntityEvent> findAllByDateEventBetween(LocalDateTime dateFrom, LocalDateTime dateTo);
}
