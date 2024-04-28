package main;

import com.zeroc.Ice.Identity;
import smarthomeImpl.*;

public class OutdoorServer {
    public void run(String[] args) {
        try(com.zeroc.Ice.Communicator communicator = com.zeroc.Ice.Util.initialize(args))
        {
            com.zeroc.Ice.ObjectAdapter adapter = communicator.createObjectAdapter("AdapterOutdoor");

            SchedulableDeviceI basicHeater = new SchedulableDeviceI();
            adapter.add(basicHeater, new Identity("basicHeater", "heater"));

            DecorativeLightI decorativeLightGarden = new DecorativeLightI();
            DecorativeLightI decorativeLightRoad = new DecorativeLightI();
            adapter.add(decorativeLightGarden, new Identity("decorativeLightGarden", "light"));
            adapter.add(decorativeLightRoad, new Identity("decorativeLightRoad", "light"));

            adapter.activate();
            communicator.waitForShutdown();
        }
    }
}
