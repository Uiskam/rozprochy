package smarthomeImpl;

import smarthome.ProgrammableDevice;
import smarthome.WorkMode;
import com.zeroc.Ice.Current;

public class ProgrammableDeviceI implements ProgrammableDevice{
    private WorkMode workMode = WorkMode.ON;

    @Override
    public WorkMode getWorkMode(Current current) {
        return this.workMode;
    }

    @Override
    public void setWorkMode(WorkMode state, Current current) {
        this.workMode = state;
    }

}
