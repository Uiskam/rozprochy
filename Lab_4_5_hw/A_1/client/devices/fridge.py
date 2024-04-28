import smarthome
import os

class Fridge():
    def __init__(self, objName, obj) -> None:
        self.obj = obj
        self.name = objName

    def getStatus(self):
        print(f"Result {self.obj.getStatus()}")
        return None

    def setStatus(self):
        temperature = input("Enter temperature[(-20)-8]: ")
        humidity = input("Enter humidity [0-100]: ")
        doorClosed = input("Is door closed [True/False]: ")
        if doorClosed == "True":
            doorClosed = True
        elif doorClosed == "False":
            doorClosed = False
        print(f"Setting status to {temperature}, {humidity}")
        self.obj.setStatus(smarthome.FridgeStatus(temperature=int(temperature),
                                                  humidity=int(humidity),
                                                  doorClosed=doorClosed))
        return None

    def addGroceriesItem(self):
        itemName = input("Enter item name: ")
        itemQuantity = input("Enter item quantity: ")
        item = smarthome.GroceriesItem(itemName, itemQuantity)
        self.obj.addGroceriesItem(item)
        return None

    def getGroceries(self):
        print(f"Result {self.obj.getGroceries()}")
        return None

    def removeGroceriesItem(self):
        itemName = input("Enter item name: ")
        self.obj.removeGroceriesItem(itemName)
        return None

    def handleCommand(self):
        while True:
            try:
                print(f"Configruing {self.name}\
                \nSelect command:\
                \n\t1. getStatus -> returns the status\
                \n\t2. setStatus -> sets the status\
                \n\t3. addGroceriesItem -> adds groceries item\
                \n\t4. getGroceries -> returns groceries\
                \n\t5. removeGroceriesItem -> removes groceries item\
                \n\tq. Quit")
                command = input("Selection (id): ")
                os.system("clear")
                if command == "1":
                    self.getStatus()
                elif command == "2":
                    self.setStatus()
                elif command == "3":
                    self.addGroceriesItem()
                elif command == "4":
                    self.getGroceries()
                elif command == "5":
                    self.removeGroceriesItem()
                elif command == "q":
                    os.system("clear")
                    break
                else:
                    print(f"Invalid command: {command}")
                    return None
            except smarthome.InvalidInput as ex:
                print(ex)