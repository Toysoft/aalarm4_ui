package net.kprod.aalarmui.bean;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import net.kprod.aalarmui.utils.JsonDateSerializer;

import java.time.LocalDateTime;

/**
 * Created by kemkem on 3/1/18.
 */
public class NfcKey {
    private String uid;
    private String name;
    private LocalDateTime dateCreated;
    private boolean enabled;

    public String getUid() {
        return uid;
    }

    public NfcKey setUid(String uid) {
        this.uid = uid;
        return this;
    }

    public String getName() {
        return name;
    }

    public NfcKey setName(String name) {
        this.name = name;
        return this;
    }

    @JsonSerialize(using = JsonDateSerializer.class)
    public LocalDateTime getDateCreated() {
        return dateCreated;
    }

    public NfcKey setDateCreated(LocalDateTime dateCreated) {
        this.dateCreated = dateCreated;
        return this;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public NfcKey setEnabled(boolean enabled) {
        this.enabled = enabled;
        return this;
    }
}
