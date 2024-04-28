SERVER_OUT_PATH="../server/src/main/java/"
SERVER_OUT_PATH_FULL="../server/src/main/java/smarthome"
CLIENT_OUT_PATH="../client"
mkdir -p $SERVER_OUT_PATH
if [ -d "$SERVER_OUT_PATH_FULL" ]; then
    rm -rf "$SERVER_OUT_PATH_FULL"/*
fi
slice2java smarthome.ice --output-dir $SERVER_OUT_PATH
mkdir -p $CLIENT_OUT_PATH
slice2py smarthome.ice --output-dir $CLIENT_OUT_PATH
