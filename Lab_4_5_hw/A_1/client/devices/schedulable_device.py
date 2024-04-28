import smarthome
import devices.programmable_device as programmable_device
import os

def getDayOfTheWeek():
    validDays = ["MON", "TUE", "WED", "THU", "FRI", "SAT", "SUN"]
    day = input(f"Enter day of the week {validDays}: ")
    if day not in validDays:
        print(f"Invalid day: {day}")
        return None
    if day == "MON":
        return smarthome.DayOfTheWeek.MON
    elif day == "TUE":
        return smarthome.DayOfTheWeek.TUE
    elif day == "WED":
        return smarthome.DayOfTheWeek.WED
    elif day == "THU":
        return smarthome.DayOfTheWeek.THU
    elif day == "FRI":
        return smarthome.DayOfTheWeek.FRI
    elif day == "SAT":
        return smarthome.DayOfTheWeek.SAT
    elif day == "SUN":
        return smarthome.DayOfTheWeek.SUN





class SchedulableDevice(programmable_device.ProgrammableDevice):
    def __init__(self, objName, obj) -> None:
        super().__init__(objName, obj)

    def setWorkSchedule(self):
        day = getDayOfTheWeek()
        if day is None:
            return
        start = input("Enter start time (HH:MM:SS): ")
        end = input("Enter end time (HH:MM:SS): ")
        interval = smarthome.WorkTimeInterval(start, end)
        self.obj.setWorkSchedule(day, interval)
        return None

    def getWorkSchedule(self):
        day = getDayOfTheWeek()
        if day is None:
            return
        print(f"Result {self.obj.getWorkSchedule(day)}")
        return None

    def clearWorkSchedule(self):
        day = getDayOfTheWeek()
        if day is None:
            return
        self.obj.clearWorkSchedule(day)
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
                elif command == "q":
                    os.system("clear")
                    break
                else:
                    print(f"Invalid command: {command}")
                    return None
            except smarthome.InvalidInput as ex:
                print(ex)

