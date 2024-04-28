import smarthome
import devices.advanced_heater as advanced_heater
import devices.light as light
import devices.fridge as fridge_front

def getIndoorDevices(communicator):
    serverAddress = ":tcp -h 127.0.0.2 -p 15000 -z -t 10000 : udp -h 127.0.0.2 -p 15000 -z"

    advHeaterName = "heater/advancedHeater"
    advancedHeater = smarthome.AdvancedHeaterPrx.checkedCast(
        communicator.stringToProxy(f"{advHeaterName}{serverAddress}"))
    if not advancedHeater: raise RuntimeError("Invalid proxy")

    lightSaloonName = "light/lightSaloon"
    lightSaloon = smarthome.LightPrx.checkedCast(
        communicator.stringToProxy(f"{lightSaloonName}{serverAddress}"))
    if not lightSaloon: raise RuntimeError("Invalid proxy")

    lightBathroomName = "light/lightBathroom"
    lightBathroom = smarthome.LightPrx.checkedCast(
        communicator.stringToProxy(f"{lightBathroomName}{serverAddress}"))
    if not lightBathroom: raise RuntimeError("Invalid proxy")

    fridgeName = "fridge/fridge"
    fridge = smarthome.FridgePrx.checkedCast(
        communicator.stringToProxy(f"{fridgeName}{serverAddress}"))
    if not fridge: raise RuntimeError("Invalid proxy")

    return [(advHeaterName, advanced_heater.AdvancedHeater(advHeaterName, advancedHeater)),
            (lightSaloonName, light.Light(lightSaloonName, lightSaloon)),
            (lightBathroomName, light.Light(lightBathroomName, lightBathroom)),
            (fridgeName, fridge_front.Fridge(fridgeName, fridge))]