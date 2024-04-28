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

public class InvalidInput extends com.zeroc.Ice.UserException
{
    public InvalidInput()
    {
        this.message = "";
    }

    public InvalidInput(Throwable cause)
    {
        super(cause);
        this.message = "";
    }

    public InvalidInput(String message)
    {
        this.message = message;
    }

    public InvalidInput(String message, Throwable cause)
    {
        super(cause);
        this.message = message;
    }

    public String ice_id()
    {
        return "::smarthome::InvalidInput";
    }

    public String message;

    /** @hidden */
    @Override
    protected void _writeImpl(com.zeroc.Ice.OutputStream ostr_)
    {
        ostr_.startSlice("::smarthome::InvalidInput", -1, true);
        ostr_.writeString(message);
        ostr_.endSlice();
    }

    /** @hidden */
    @Override
    protected void _readImpl(com.zeroc.Ice.InputStream istr_)
    {
        istr_.startSlice();
        message = istr_.readString();
        istr_.endSlice();
    }

    /** @hidden */
    public static final long serialVersionUID = -7520218108581166538L;
}