package main;

import com.zeroc.Ice.Identity;
import smarthomeImpl.ProgrammableDeviceI;
import smarthomeImpl.*;

public class IndoorServer {
    public void run(String[] args) {
        try(com.zeroc.Ice.Communicator communicator = com.zeroc.Ice.Util.initialize(args))
        {
            com.zeroc.Ice.ObjectAdapter adapter = communicator.createObjectAdapter("AdapterIndoor");

            AdvancedHeaterI advancedHeaterI = new AdvancedHeaterI();
            adapter.add(advancedHeaterI, new Identity("advancedHeater", "heater"));

            LightI lightISaloon = new LightI();
            LightI lightIBathroom = new LightI();
            LightI lightISleepingRoom = new LightI();
            LightI lightIKitchen = new LightI();
            adapter.add(lightISaloon, new Identity("lightSaloon", "light"));
            adapter.add(lightIBathroom, new Identity("lightBathroom", "light"));
            adapter.add(lightISleepingRoom, new Identity("lightSleepingRoom", "light"));
            adapter.add(lightIKitchen, new Identity("lightKitchen", "light"));

            FridgeI fridgeI = new FridgeI();
            adapter.add(fridgeI, new Identity("fridge", "fridge"));

            adapter.activate();
            communicator.waitForShutdown();
        }
    }

}
