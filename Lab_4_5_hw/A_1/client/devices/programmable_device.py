import smarthome
import os

class ProgrammableDevice():
    def __init__(self, objName, obj) -> None:
        self.obj = obj
        self.name = objName

    def getWorkMode(self):
        print(f"Result {self.obj.getWorkMode()}")
        return None

    def setWorkMode(self):
        workMode = input("Enter work mode ['ON', 'OFF', 'PROGRAM']: ")
        if workMode not in ['ON', 'OFF', 'PROGRAM']:
            print(f"Invalid work mode: {workMode}")
            return
        mode = None
        if workMode == 'PROGRAM':
            mode = smarthome.WorkMode.PROGRAM
        elif workMode == 'OFF':
            mode = smarthome.WorkMode.OFF
        elif workMode == 'ON':
            mode = smarthome.WorkMode.ON
        self.obj.setWorkMode(mode)

    def handleCommand(self):
        while True:
            print(f"Configruing {self.name}\
                \nSelect command:\
                \n\t1. getWorkMode -> returns the current work mode\
                \n\t2. setWorkMode -> sets the work mode\
                \n\tq. Quit")
            command = input("Selection (id): ")
            os.system("clear")
            if command == "1":
                self.getWorkMode()
            elif command == "2":
                self.setWorkMode()
            elif command == "q":
                os.system("clear")
                break
            else:
                print(f"Invalid command: {command}")
                return None
