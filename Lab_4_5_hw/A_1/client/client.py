import sys, Ice
import smarthome
import init.indoor_devices as indoor_devices
import init.outdoor_devices as outdoor_devices
import os

with Ice.initialize(sys.argv) as communicator:
    devices = indoor_devices.getIndoorDevices(communicator)
    devices += outdoor_devices.getOutdoorDevices(communicator)
    devices.sort(key=lambda x: x[0])
    while True:
        print("Select device to configure:")
        for i, device in enumerate(devices):
            print(f"\t{i}: {device[0]}")
        print("\tq: Quit")
        selection = input("Selection (id or q): ")
        os.system("clear")
        if selection == "q":
            break
        if not selection.isdigit():
            print(f"Invalid selection: {selection}")
            continue
        selection = int(selection)
        if selection < 0 or selection >= len(devices):
            print(f"Invalid selection: {selection}, must be in range [0, {len(devices)})")
            continue
        _, handler = devices[selection]
        handler.handleCommand()



