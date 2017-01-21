# ================ IMPORTS ===============

# ========================================

def unlock_door() :
	pass

def open_access() :
	pass

def close_access() :
	pass

def print_passcode() :
	pass

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
	else :
		print command

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

while (running) :
	# process commands or display
	input_string = raw_input()
	process_command(input_string)

exit()