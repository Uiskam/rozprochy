package smarthomeImpl;


import com.zeroc.Ice.Current;
import smarthome.DecorativeLight;
import smarthome.Pattern;

public class DecorativeLightI extends LightI implements DecorativeLight{
    private Pattern pattern = Pattern.FADE;

    @Override
    public Pattern getPattern(Current current) {
        return this.pattern;
    }

    @Override
    public void setPattern(Pattern pattern, Current current) {
        this.pattern = pattern;
    }

}
