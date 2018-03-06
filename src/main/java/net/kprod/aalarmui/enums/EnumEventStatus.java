package net.kprod.aalarmui.enums;

/**
 * Created by kemkem on 3/1/18.
 */
public enum EnumEventStatus {
    open(EnumEventType.doorSensor),
    close(EnumEventType.doorSensor),
    offline(EnumEventType.alarm),
    idle(EnumEventType.alarm),
    online(EnumEventType.alarm),
    breach(EnumEventType.alarm),
    warning(EnumEventType.alarm),
    alert(EnumEventType.alarm),
    motion(EnumEventType.camera),
    unknown(EnumEventType.unknown);

    private EnumEventType sensorType;

    EnumEventStatus(EnumEventType sensorType) {
        this.sensorType = sensorType;
    }

    public EnumEventType getSensorType() {
        return sensorType;
    }
}
