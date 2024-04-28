package smarthomeImpl;

import com.zeroc.Ice.Current;
import smarthome.AdvancedHeater;
import smarthome.InvalidInput;

public class AdvancedHeaterI extends ProgrammableDeviceI implements AdvancedHeater {
    private int temperature = 0;

    @Override
    public void setTemperature(int temperature, Current current) throws InvalidInput{
        if (temperature < -20 || temperature > 40) {
            throw new InvalidInput("Temperature out of range. Please use a value between -20 and 40.");
        }
        this.temperature = temperature;
    }

    @Override
    public int getTemperature(Current current) {
        return this.temperature;
    }
}
