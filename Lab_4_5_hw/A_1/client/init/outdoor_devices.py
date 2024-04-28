import smarthome
import devices.programmable_device as programmable_device
import devices.advanced_heater as advanced_heater
import devices.schedulable_device as schedulable_device
import devices.decorative_light as decorative_light

def getOutdoorDevices(communicator):
    serverAddress = ":tcp -h 127.0.0.2 -p 10000 -z -t 10000 : udp -h 127.0.0.2 -p 10000 -z"
    basicHeaterName = "heater/basicHeater"
    basicHeater = smarthome.SchedulableDevicePrx.checkedCast(
        communicator.stringToProxy(f"{basicHeaterName}{serverAddress}"))
    if not basicHeater: raise RuntimeError("Invalid proxy")

    decorativeLightGardenName = "light/decorativeLightGarden"
    decorativeLightGarden = smarthome.DecorativeLightPrx.checkedCast(
        communicator.stringToProxy(f"{decorativeLightGardenName}{serverAddress}"))
    if not decorativeLightGarden: raise RuntimeError("Invalid proxy")

    decorativeLightRoadName = "light/decorativeLightRoad"
    decorativeLightRoad = smarthome.DecorativeLightPrx.checkedCast(
        communicator.stringToProxy(f"{decorativeLightRoadName}{serverAddress}"))
    if not decorativeLightRoad: raise RuntimeError("Invalid proxy")

    return [(basicHeaterName, schedulable_device.SchedulableDevice(basicHeaterName, basicHeater)),
            (decorativeLightGardenName, decorative_light.DecorativeLight(decorativeLightGardenName, decorativeLightGarden)),
            (decorativeLightRoadName, decorative_light.DecorativeLight(decorativeLightRoadName, decorativeLightRoad))]
