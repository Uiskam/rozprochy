import smarthome
import devices.light as light
import os

class DecorativeLight(light.Light):
    def __init__(self, objName, obj) -> None:
        super().__init__(objName, obj)

    def setPattern(self):
        validPatterns = ['BLINK', 'FADE', 'RANDOM']
        pattern = input(f"Enter pattern {validPatterns}: ")
        if pattern not in validPatterns:
            print(f"Invalid pattern: {pattern}")
            return
        if pattern == 'BLINK':
            self.obj.setPattern(smarthome.Pattern.BLINK)
        elif pattern == 'FADE':
            self.obj.setPattern(smarthome.Pattern.FADE)
        elif pattern == 'RANDOM':
            self.obj.setPattern(smarthome.Pattern.RANDOM)

    def getPattern(self):
        print(f"Result {self.obj.getPattern()}")
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
                \n\t8. setPattern -> sets the pattern\
                \n\t9. getPattern -> returns the pattern\
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
                elif command == "8":
                    self.setPattern()
                elif command == "9":
                    self.getPattern()
                elif command == "q":
                    os.system("clear")
                    break
                else:
                    print(f"Invalid command: {command}")
                    return None
            except smarthome.InvalidInput as ex:
                print(ex)