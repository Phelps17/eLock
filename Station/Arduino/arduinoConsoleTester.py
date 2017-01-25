import serial
import time

ser = serial.Serial('/dev/tty.usbmodem1411', 9600)

time.sleep(1)

print ser.name

while(1):
  word = raw_input('> ')

  if(word == "end"):
    break;
  elif(word == "yes"):
  	print "UNLOCK"
  	ser.write('1')
  else:
  	print "DENY"
  	ser.write('0')

#Closes the connection
ser.close()
