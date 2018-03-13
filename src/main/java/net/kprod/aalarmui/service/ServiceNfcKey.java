package net.kprod.aalarmui.service;

import net.kprod.aalarmui.bean.NfcKey;

import java.util.List;
import java.util.Optional;

/**
 * Created by kemkem on 3/1/18.
 */
public interface ServiceNfcKey {
    void registerKey(String uid);
    void revokeKey(String uid);
    void enableKey(String uid);
    void disableKey(String uid);
    void setKeyName(String uid, String name);
    Optional<NfcKey> getKey(String uid);
    List<NfcKey> listKey();
    boolean verifyKey(String uid);
}
