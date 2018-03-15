package net.kprod.aalarmui.service;

import net.kprod.aalarmui.bean.NfcKey;
import net.kprod.aalarmui.db.entity.EntityNfcKey;
import net.kprod.aalarmui.db.repository.RepositoryEvent;
import net.kprod.aalarmui.db.repository.RepositoryNfcKey;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Created by kemkem on 3/1/18.
 */
@Service
public class ServiceNfcKeyImpl implements ServiceNfcKey {
    private Logger LOG = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private RepositoryNfcKey repositoryNfcKey;

    @Override
    public void registerKey(String uid) {
        EntityNfcKey entityNfcKey = new EntityNfcKey();
        entityNfcKey.setUid(uid);
        entityNfcKey.setEnabled(true);
        entityNfcKey.setDateCreated(LocalDateTime.now());
        entityNfcKey.setName("none");

        repositoryNfcKey.save(entityNfcKey);
    }

    @Override
    public void revokeKey(String uid) {
        repositoryNfcKey.delete(uid);
    }

    @Override
    public void enableKey(String uid) {
        Optional<EntityNfcKey> optEntityNfcKey = this.getEntityNfcKey(uid);
        if(optEntityNfcKey.isPresent()) {
            optEntityNfcKey.get().setEnabled(true);
            repositoryNfcKey.save(optEntityNfcKey.get());
        }
    }

    @Override
    public void disableKey(String uid) {
        Optional<EntityNfcKey> optEntityNfcKey = this.getEntityNfcKey(uid);
        if(optEntityNfcKey.isPresent()) {
            optEntityNfcKey.get().setEnabled(false);
            repositoryNfcKey.save(optEntityNfcKey.get());
        }
    }

    @Override
    public void setKeyName(String uid, String name) {
        Optional<EntityNfcKey> optEntityNfcKey = this.getEntityNfcKey(uid);
        if(optEntityNfcKey.isPresent()) {
            optEntityNfcKey.get().setName(name);
            repositoryNfcKey.save(optEntityNfcKey.get());
        }
    }

    @Override
    public Optional<NfcKey> getKey(String uid) {

        Optional<EntityNfcKey> optEntityNfcKey = this.getEntityNfcKey(uid);

        if(optEntityNfcKey.isPresent()) {
            return Optional.of(mapToNfcKey(optEntityNfcKey.get()));
        } else {
            return Optional.empty();
        }
    }


    private Optional<EntityNfcKey> getEntityNfcKey(String uid) {

        EntityNfcKey entityNfcKey = repositoryNfcKey.findOne(uid);

        if(entityNfcKey != null) {
            return Optional.of(entityNfcKey);
        } else {
            return Optional.empty();
        }
    }

    @Override
    public List<NfcKey> listKey() {
        return repositoryNfcKey.findAll().stream()
                .map(k -> mapToNfcKey(k))
                .collect(Collectors.toList());
    }

    @Override
    public boolean verifyKey(String uid) {
        Optional<NfcKey> optNfcKey = this.getKey(uid);
        if(optNfcKey.isPresent() && optNfcKey.get().isEnabled()) {
            return true;
        }
        return false;
    }

    private NfcKey mapToNfcKey(EntityNfcKey entityNfcKey) {
        return new NfcKey()
                .setDateCreated(entityNfcKey.getDateCreated())
                .setEnabled(entityNfcKey.isEnabled())
                .setName(entityNfcKey.getName())
                .setUid(entityNfcKey.getUid());
    }
}
