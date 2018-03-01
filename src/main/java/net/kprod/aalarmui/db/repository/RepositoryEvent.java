package net.kprod.aalarmui.db.repository;

import net.kprod.aalarmui.db.entity.EntityEvent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by kemkem on 5/1/17.
 */
@Repository
public interface RepositoryEvent extends JpaRepository<EntityEvent, Long> {
}
