package smarthomeImpl;

import com.zeroc.Ice.Current;
import smarthome.Light;
import smarthome.LightStatus;
import smarthome.InvalidInput;
import  smarthome.Color;

public class LightI extends SchedulableDeviceI implements Light{
    private LightStatus lightStatus = new LightStatus(125, Color.WHITE);

    @Override
    public LightStatus getStatus(Current current) {
        return this.lightStatus;
    }

    @Override
    public void setStatus(LightStatus status, Current current) throws InvalidInput {
        if (status.brightness < 0 || status.brightness > 255) {
            throw new InvalidInput("Brightness out of range. Please use a value between 0 and 255.");
        }
        this.lightStatus = status;
    }
}
