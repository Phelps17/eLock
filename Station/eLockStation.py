# ================ IMPORTS ===============
from pubnub import Pubnub
from threading import Thread
import os

CHANNEL = "eLockServer"
PUBNUB_PUBLISH_KEY = "pub-c-a8732a53-6069-4292-8981-a1a9a230172f"
PUBNUB_SUBSCRIBE_KEY = "sub-c-a27f6252-e02e-11e6-989b-02ee2ddab7fe"
# ========================================

def unlock_door() :
	#TODO PySerial Code will go here
	print "UNLOCKING"

def process_pubnub_message(message, channel) :
	#just alerting for now
	print message

def pubnub_server() :
	global PUBNUB
	PUBNUB.subscribe(CHANNEL, process_pubnub_message)

def open_access() :
	# open access for pubnub requests
	global server_thread

	server_thread = Thread(target= pubnub_server)
	server_thread.setDaemon(True)
	server_thread.start()
	print "eLock is now accessible."
	#server_thread.join()

def close_access() :
	# close access for pubnub requests
	global server_thread
	PUBNUB.unsubscribe(CHANNEL)
	print "eLock is no longer accessible."

def print_passcode() :
	global password
	print "PASSCODE:", password

def stop_station() :
	print "eLockStation Stopping..."
	global running 
	running = False

def print_help() :
	global door_name

	print "================ COMMANDS ==============="
	print "EXIT     - Stop the station"
	print "START    - Open access to eLock"
	print "STOP     - Close access to eLock"
	print "PASSCODE - Print door passcode"
	print "UNLOCK   - Unlock the door"
	print "HELP     - Print this info again"
	print "========================================="

def process_command(command) :
	command = command.upper()

	if (command == "EXIT") :
		stop_station()
	elif (command == "START") :
		open_access()
	elif (command == "STOP") :
		close_access()
	elif (command == "PASSCODE") :
		print_passcode()
	elif (command == "UNLOCK") :
		unlock_door()
	elif (command == "HELP") :
		print_help()
	else :
		print "Command not found."

# ================ SET UP ================

# generate door name code
door_name = "HJMqOT2NSh}}|n=)291B$b7/{ThL*{754HTF{QB20<IC34=[!%$U;A<l#FSCV";

print "eLOCK STATION VERSION 1.0"
running = False

# get pass code
print "Enter Door Passcode:",
password = raw_input()

print "eLockStation Starting..."

PUBNUB = Pubnub(publish_key=PUBNUB_PUBLISH_KEY,
                    subscribe_key=PUBNUB_SUBSCRIBE_KEY,
                    cipher_key='',
                    ssl_on=False
                    )
server_thread = None

running = True

# ========================================

print_help()

while (running) :
	input_string = raw_input()
	process_command(input_string)

close_access()
os._exit(0)
