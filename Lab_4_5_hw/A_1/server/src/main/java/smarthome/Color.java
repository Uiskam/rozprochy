//
// Copyright (c) ZeroC, Inc. All rights reserved.
//
//
// Ice version 3.7.10
//
// <auto-generated>
//
// Generated from file `smarthome.ice'
//
// Warning: do not edit this file.
//
// </auto-generated>
//

package smarthome;

public enum Color implements java.io.Serializable
{
    RED(0),
    GREEN(1),
    BLUE(2),
    YELLOW(3),
    WHITE(4),
    ORANGE(5),
    PURPLE(6),
    PINK(7),
    CYAN(8),
    BLACK(9);

    public int value()
    {
        return _value;
    }

    public static Color valueOf(int v)
    {
        switch(v)
        {
        case 0:
            return RED;
        case 1:
            return GREEN;
        case 2:
            return BLUE;
        case 3:
            return YELLOW;
        case 4:
            return WHITE;
        case 5:
            return ORANGE;
        case 6:
            return PURPLE;
        case 7:
            return PINK;
        case 8:
            return CYAN;
        case 9:
            return BLACK;
        }
        return null;
    }

    private Color(int v)
    {
        _value = v;
    }

    public void ice_write(com.zeroc.Ice.OutputStream ostr)
    {
        ostr.writeEnum(_value, 9);
    }

    public static void ice_write(com.zeroc.Ice.OutputStream ostr, Color v)
    {
        if(v == null)
        {
            ostr.writeEnum(smarthome.Color.RED.value(), 9);
        }
        else
        {
            ostr.writeEnum(v.value(), 9);
        }
    }

    public static Color ice_read(com.zeroc.Ice.InputStream istr)
    {
        int v = istr.readEnum(9);
        return validate(v);
    }

    public static void ice_write(com.zeroc.Ice.OutputStream ostr, int tag, java.util.Optional<Color> v)
    {
        if(v != null && v.isPresent())
        {
            ice_write(ostr, tag, v.get());
        }
    }

    public static void ice_write(com.zeroc.Ice.OutputStream ostr, int tag, Color v)
    {
        if(ostr.writeOptional(tag, com.zeroc.Ice.OptionalFormat.Size))
        {
            ice_write(ostr, v);
        }
    }

    public static java.util.Optional<Color> ice_read(com.zeroc.Ice.InputStream istr, int tag)
    {
        if(istr.readOptional(tag, com.zeroc.Ice.OptionalFormat.Size))
        {
            return java.util.Optional.of(ice_read(istr));
        }
        else
        {
            return java.util.Optional.empty();
        }
    }

    private static Color validate(int v)
    {
        final Color e = valueOf(v);
        if(e == null)
        {
            throw new com.zeroc.Ice.MarshalException("enumerator value " + v + " is out of range");
        }
        return e;
    }

    private final int _value;
}
