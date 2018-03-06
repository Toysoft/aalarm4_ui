package net.kprod.aalarmui.service;

import net.kprod.aalarmui.enums.EnumEventStatus;
import net.kprod.aalarmui.exception.ServiceException;

/**
 * Created by kemkem on 3/1/18.
 */
public interface ServiceRemote {
    EnumEventStatus getCurrentStatus() throws ServiceException;
    void toggleStatus() throws  ServiceException;
}
