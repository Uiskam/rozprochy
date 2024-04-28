
#ifndef SMARTHOME_ICE
#define SMARTHOME_ICE

module smarthome
{
  exception InvalidInput {
    string message;
  };

  enum DayOfTheWeek { MON, TUE, WED, THU, FRI, SAT, SUN };
  enum WorkMode { ON, OFF, PROGRAM };
  struct WorkTimeInterval {
    string beginWork; //HH:MM:SS
    string endWork; //legal vals from 0000 to 2359
  };
  interface ProgrammableDevice
  {
    idempotent WorkMode getWorkMode();
    idempotent void setWorkMode(WorkMode state);
  };

  dictionary <DayOfTheWeek, WorkTimeInterval> WorkTime;
  interface SchedulableDevice extends ProgrammableDevice
  {
      idempotent void setWorkSchedule(DayOfTheWeek day, WorkTimeInterval time) throws InvalidInput; //done
      idempotent WorkTime getWorkSchedule(DayOfTheWeek day);
      idempotent void clearWorkSchedule(DayOfTheWeek day);
  };

  interface AdvancedHeater extends ProgrammableDevice
  {
      idempotent void setTemperature(int temp) throws InvalidInput; //done
      idempotent int getTemperature();
  };

  enum Color { RED, GREEN, BLUE, YELLOW, WHITE, ORANGE, PURPLE, PINK, CYAN, BLACK };
  struct LightStatus {
    int brightness;
    Color color;
  };
  interface Light extends SchedulableDevice
  {
    idempotent void setStatus(LightStatus settings) throws InvalidInput; //done
    idempotent LightStatus getStatus();
  };

  enum Pattern { BLINK, FADE, RANDOM };
  interface DecorativeLight extends Light
  {
      void setPattern(Pattern pattern);
      Pattern getPattern();
  };

  struct GroceriesItem {
    string name;
    string quantity;
  };

  struct FridgeStatus {
    int temperature;
    int humidity;
    bool doorClosed;
  };

  sequence<GroceriesItem> GroceriesList;
  interface Fridge
  {
    idempotent FridgeStatus getStatus();
    idempotent void setStatus(FridgeStatus FridgeStatus) throws InvalidInput;
    void addGroceriesItem(GroceriesItem GroceriesList);
    idempotent GroceriesList getGroceries();
    void removeGroceriesItem(GroceriesItem GroceriesList);
  };

};

#endif
