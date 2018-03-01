package net.kprod.aalarmui.enums;

/**
 * Created by kemkem on 3/1/18.
 */
public enum EnumEventStatus {
    open(EnumEventType.doorSensor),
    close(EnumEventType.doorSensor),
    offline(EnumEventType.state),
    idle(EnumEventType.state),
    online(EnumEventType.state),
    breach(EnumEventType.state),
    warning(EnumEventType.state),
    alert(EnumEventType.state);

    private EnumEventType sensorType;

    EnumEventStatus(EnumEventType sensorType) {
        this.sensorType = sensorType;
    }

    public EnumEventType getSensorType() {
        return sensorType;
    }
}
