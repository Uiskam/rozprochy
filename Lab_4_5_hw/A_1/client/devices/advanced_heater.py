import smarthome
import devices.programmable_device as programmable_device
import os

class AdvancedHeater(programmable_device.ProgrammableDevice):
    def __init__(self, objName, obj) -> None:
        super().__init__(objName, obj)

    def getTemperature(self):
        print(f"Result {self.obj.getTemperature()}")
        return None

    def setTemperature(self):
        temperature = input("Enter temperature: ")
        try:
            temperature = int(temperature)
        except ValueError:
            print(f"Invalid temperature (expected int): {temperature}")
            return
        self.obj.setTemperature(temperature)

    def handleCommand(self):
        while True:
            try:
                print(f"Configruing {self.name}\
                \nSelect command:\
                \n\t1. getWorkMode -> returns the current work mode\
                \n\t2. setWorkMode -> sets the work mode\
                \n\t3. getTemperature -> returns the current temperature\
                \n\t4. setTemperature -> sets the temperature\
                \n\tq. Quit")
                command = input("Selection (id): ")
                os.system("clear")
                if command == "1":
                    self.getWorkMode()
                elif command == "2":
                    self.setWorkMode()
                elif command == "3":
                    self.getTemperature()
                elif command == "4":
                    self.setTemperature()
                elif command == "q":
                    os.system("clear")
                    break
                else:
                    print(f"Invalid command: {command}")
                    return None
            except smarthome.InvalidInput as ex:
                print(ex)

