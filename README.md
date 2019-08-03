# Rob
This is for gree robot demo.

It contains a server file and an App file. There are two demo for servers, one is for modbus communication and the other is for App commnunication.

To start up, download the App apk file in an Android phone, running the server by running the Appserver file.

The server may need to use pip to install flask and modbus package.

After you run the server on the computer run the App on the phone

If the server missing package, on windows run:   pip install flask

If the App crashes, it might because of the IP address changed. Check for the current Ip address by running: ipconfig 

and change the IP on APP's code.
