package net.kprod.aalarmui.db.repository;

import net.kprod.aalarmui.db.entity.EntityEvent;
import net.kprod.aalarmui.enums.EnumEventType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

/**
 * Created by kemkem on 5/1/17.
 */
@Repository
public interface RepositoryPageableEvent extends PagingAndSortingRepository<EntityEvent, Long> {
    Page<EntityEvent> findByDateEventBetween(Pageable pageable, LocalDateTime from, LocalDateTime to);
}
