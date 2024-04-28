import smarthome
import devices.schedulable_device as schedulable_device
import os

def getColor():
    vaildColors = ["RED", "GREEN", "BLUE", "YELLOW", "WHITE", "ORANGE", 'PURPLE', 'PINK', 'CYAN','BLACK']
    color = input(f"Enter color {vaildColors}: ")
    if color not in vaildColors:
        print(f"Invalid color: {color}")
        return None
    if color == "RED":
        return smarthome.Color.RED
    elif color == "GREEN":
        return smarthome.Color.GREEN
    elif color == "BLUE":
        return smarthome.Color.BLUE
    elif color == "YELLOW":
        return smarthome.Color.YELLOW
    elif color == "WHITE":
        return smarthome.Color.WHITE
    elif color == "ORANGE":
        return smarthome.Color.ORANGE
    elif color == "PURPLE":
        return smarthome.Color.PURPLE
    elif color == "PINK":
        return smarthome.Color.PINK
    elif color == "CYAN":
        return smarthome.Color.CYAN
    elif color == "BLACK":
        return smarthome.Color.BLACK


class Light(schedulable_device.SchedulableDevice):
    def __init__(self, objName, obj) -> None:
        super().__init__(objName, obj)

    def setStatus(self):
        brightness = input("Enter brightness (0-255): ")
        color = getColor()
        if color is None:
            return
        self.obj.setStatus(smarthome.LightStatus(int(brightness), color))
        return None

    def getStatus(self):
        print(f"Result {self.obj.getStatus()}")
        return None

    def handleCommand(self):
        while True:
            try:
                print(f"Configruing {self.name}\
                \nSelect command:\
                \n\t1. getWorkMode -> returns the current work mode\
                \n\t2. setWorkMode -> sets the work mode\
                \n\t3. setWorkSchedule -> sets the work schedule\
                \n\t4. getWorkSchedule -> returns the work schedule\
                \n\t5. clearWorkSchedule -> clears the work schedule\
                \n\t6. setStatus -> sets the status\
                \n\t7. getStatus -> returns the status\
                \n\tq. Quit")
                command = input("Selection (id): ")
                os.system("clear")
                if command == "1":
                    self.getWorkMode()
                elif command == "2":
                    self.setWorkMode()
                elif command == "3":
                    self.setWorkSchedule()
                elif command == "4":
                    self.getWorkSchedule()
                elif command == "5":
                    self.clearWorkSchedule()
                elif command == "6":
                    self.setStatus()
                elif command == "7":
                    self.getStatus()
                elif command == "q":
                    os.system("clear")
                    break
                else:
                    print(f"Invalid command: {command}")
                    return None
            except smarthome.InvalidInput as ex:
                print(ex)

