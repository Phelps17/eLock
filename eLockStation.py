# ================ IMPORTS ===============

# ========================================

def unlock_door() :
	pass

def open_access() :
	# open access for pubnub requests
	pass

def close_access() :
	# close access for pubnub requests
	pass

def print_passcode() :
	global password
	print "PASSCODE:", password

def stop_station() :
	print "eLockStation Stopping..."
	global running 
	running = False

def print_help() :
	global door_name

	print "eLockStation Starting..."
	print "Door Name: ", door_name
	print "================ COMMANDS ==============="
	print "STOP     - Stop the station"
	print "OPEN     - Open access to eLock"
	print "CLOSE    - Close access to eLock"
	print "PASSCODE - Print door passcode"
	print "UNLOCK   - Unlock the door"
	print "HELP     - Print this info again"
	print "========================================="

def process_command(command) :
	if (command == "STOP") :
		stop_station()
	elif (command == "OPEN") :
		open_access()
	elif (command == "CLOSE") :
		close_access()
	elif (command == "PASSCODE") :
		print_passcode()
	elif (command == "UNLOCK") :
		unlock_door()
	elif (command == "HELP") :
		print_help()
	else :
		print "COMMAND -", command, "- was not found."

# ================ SET UP ================

print "eLOCK STATION VERSION 1.0"
running = False
# generate door name code
door_name = 123456789;

# get pass code
print "Enter Door Passcode:"
password = raw_input()

# ask to start
print "Press Enter to Start"
pause_the_start = raw_input()
running = True

# ========================================

print_help()
#TODO: launch a background service here

while (running) :
	# process commands or display
	input_string = raw_input()
	process_command(input_string)

#TODO close background service here
exit()
