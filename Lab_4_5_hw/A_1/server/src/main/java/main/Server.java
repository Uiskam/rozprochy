package main;

import smarthomeImpl.ProgrammableDeviceI;
public class Server
{
    public static void main(String[] args)
    {
        if (args.length != 2)
        {
            System.err.println("Usage: Server <config> <serverName>");
            return;
        }
        String[] config = {args[0]};
        if (args[1].equals("in"))
        {
            IndoorServer server = new IndoorServer();

            server.run(config);
        }
        else if (args[1].equals("out"))
        {
            OutdoorServer server = new OutdoorServer();
            server.run(config);
        }
        else
        {
            System.err.println("Unknown server name: " + args[0]);
        }

    }
}
