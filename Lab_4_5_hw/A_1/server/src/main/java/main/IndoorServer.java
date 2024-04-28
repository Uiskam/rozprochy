package main;

import com.zeroc.Ice.Identity;
import smarthomeImpl.ProgrammableDeviceI;
import smarthomeImpl.*;

public class IndoorServer {
    public void run(String[] args) {
        try(com.zeroc.Ice.Communicator communicator = com.zeroc.Ice.Util.initialize(args))
        {
            com.zeroc.Ice.ObjectAdapter adapter = communicator.createObjectAdapter("AdapterIndoor");

            AdvancedHeaterI advancedHeater = new AdvancedHeaterI();
            adapter.add(advancedHeater, new Identity("advancedHeater", "heater"));

            LightI lightSaloon = new LightI();
            LightI lightBathroom = new LightI();
            adapter.add(lightSaloon, new Identity("lightSaloon", "light"));
            adapter.add(lightBathroom, new Identity("lightBathroom", "light"));

            FridgeI fridge = new FridgeI();
            adapter.add(fridge, new Identity("fridge", "fridge"));

            adapter.activate();
            communicator.waitForShutdown();
        }
    }

}
