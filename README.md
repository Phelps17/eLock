# eLock Version 1.0
4 Day Dev Challenge : PubNub Powered IOT Lock

---
##About
Because I tend to forget my keys, and my apartment building has an intercom system that allows someone inside to buzz you up, I decided to build an app that would allow me to buzz myself up and unlock the door from my smart phone.

##eLock Station
A python script and an Arduino script. You need to have pyserial installed and run the setup.py script in the Station folder to update file permissions before running. You also need to adjust the value of 'serial_port' in Settings.json to match the port you connected the Arduino to. My Arduino schematic and Arduino script are both in the Arduino folder and should be plug-and-play if replicated. If you do not have an Arduino, you can run the Station in demo mode by simply not connecting an Arduino (or giving 'port_serial' the wrong port). In demo mode, the console will just display the lock status.

To start the station run 
```
./eLockStation.py
```

Another note: This does assume your localhost port 8000 is open. If not, you can change that within the eLockStation.py file. It is easy to find.

The Web Interface is launched upon running eLockStation. It currently only generates a QR code for adding eLocks to your phone's database. Had I had more time, I was looking into building it up to have a small dashboard of basic commands and perhaps a live eLock access log to view what devices connected at what times.

##Android Application
The Android App can be opened in Android Studio and launched to a device. Upon opening the application you can select the plus button to add a new eLock. Launching the eLockStation should present you with a QR code. Scanning that code with your phone will add the lock to your local database. You can now add, delete, and rename saved eLocks. To unlock one, simply enter the password into the password field and select the 'UNLOCK' button. PubNub magic handles the rest.

##iOS Application
The iOS App was a project stretch goal that I started but was unable to successfully implement within my few days working on the project. If you open the workspace, it is still obviously in the early stages of development. You can probably ignore testing it as it lacks most of it's functionality.

##Acknowledgements
- https://www.pubnub.com/
- https://github.com/greenrobot/greenDAO
- https://developers.google.com/vision/barcodes-overview
- https://www.varvet.com/blog/android-qr-code-reader-made-easy/
- https://pypi.python.org/pypi/pyserial
- https://cocoapods.org/
