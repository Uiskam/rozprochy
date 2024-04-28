package smarthomeImpl;

import com.zeroc.Ice.Current;
import smarthome.SchedulableDevice;
import smarthome.DayOfTheWeek;
import smarthome.WorkTimeInterval;
import java.util.Map;
import smarthome.InvalidInput;

public class SchedulableDeviceI extends ProgrammableDeviceI implements SchedulableDevice{
    final private Map<DayOfTheWeek, WorkTimeInterval> workTime = new java.util.HashMap<DayOfTheWeek, WorkTimeInterval>();

    private boolean isValidTime(String time) {
        String[] parts = time.split(":");
        try {
            int hours = Integer.parseInt(parts[0]);
            int minutes = Integer.parseInt(parts[1]);
            int seconds = Integer.parseInt(parts[2]);

            if (hours < 0 || hours > 23) {
                return false;
            }
            if (minutes < 0 || minutes > 59) {
                return false;
            }
            if (seconds < 0 || seconds > 59) {
                return false;
            }
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    @Override
    public void setWorkSchedule(DayOfTheWeek day, WorkTimeInterval interval, Current current) throws InvalidInput{
        String beginWork = interval.beginWork;
        String endWork = interval.endWork;
        if (!isValidTime(beginWork) || !isValidTime(endWork)) {
            throw new InvalidInput("Invalid time format. Please use HH:MM:SS format.");
        }
        this.workTime.put(day, interval);
    }

    @Override
    public Map<DayOfTheWeek, WorkTimeInterval> getWorkSchedule(DayOfTheWeek day, Current current) {
        return this.workTime;
    }

    @Override
    public void clearWorkSchedule(DayOfTheWeek day, Current current) {
        this.workTime.remove(day);
    }
}

