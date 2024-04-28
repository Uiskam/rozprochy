package smarthomeImpl;

import smarthome.Fridge;
import com.zeroc.Ice.Current;
import smarthome.FridgeStatus;
import smarthome.GroceriesItem;
import java.util.ArrayList;
import smarthome.InvalidInput;

public class FridgeI implements Fridge{
    private FridgeStatus fridgeStatus = new FridgeStatus(0, 0, true);
    final private ArrayList<GroceriesItem> groceries = new ArrayList<GroceriesItem>();

    @Override
    public FridgeStatus getStatus(Current current) {
        return this.fridgeStatus;
    }

    @Override
    public void setStatus(FridgeStatus status, Current current) throws InvalidInput {
        if (this.fridgeStatus.temperature < -20 || this.fridgeStatus.temperature > 8) {
            throw new InvalidInput("Temperature out of range. Please use a value between -20 and 20.");
        }
        if (this.fridgeStatus.humidity < 0 || this.fridgeStatus.humidity > 100) {
            throw new InvalidInput("Humidity out of range. Please use a value between 0 and 100.");
        }
        this.fridgeStatus = status;
    }

    @Override
    public void addGroceriesItem(GroceriesItem item, Current current) {
        this.groceries.add(item);
    }

    @Override
    public void removeGroceriesItem(GroceriesItem item, Current current) {
        this.groceries.remove(item);
    }

    @Override
    public GroceriesItem[] getGroceries(Current current) {
        return this.groceries.toArray(new GroceriesItem[0]);
    }

}
