#!/usr/bin/env python

# ================ IMPORTS ===============
from pubnub import Pubnub
from threading import Thread
import BaseHTTPServer
import CGIHTTPServer
import cgitb; cgitb.enable()
import os
import webbrowser
import json

CHANNEL = ""
PUBNUB_PUBLISH_KEY = ""
PUBNUB_SUBSCRIBE_KEY = ""
DOOR_NAME = ""
PASSCODE = ""
# ========================================

def unlock_door() :
	#TODO PySerial Code will go here
	print "UNLOCKING"

def process_pubnub_message(message, channel) :
	global PASSCODE

	try :
		message_data = message.split(' ')
		if (message_data[1] == DOOR_NAME) :
			if (message_data[2] == PASSCODE) :
				unlock_door()
			else :
				print "Invalid passcode from", message_data[0]
		else :
			print "Invalid identifier from", message_data[0]
	except BaseException :
		print "Invalid Connection Attempt"

def pubnub_server() :
	global PUBNUB
	PUBNUB.subscribe(CHANNEL, process_pubnub_message)

def open_access() :
	# open access for pubnub requests
	global server_thread

	pubnub_server_thread = Thread(target= pubnub_server)
	pubnub_server_thread.setDaemon(True)
	pubnub_server_thread.start()
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

def launch_web_server() :
	web_server_thread = Thread(target= web_server)
	web_server_thread.setDaemon(True)
	web_server_thread.start()
	print "Web interface launched at http://localhost:8000/eLockWeb.py"
	webbrowser.open('http://localhost:8000/eLockWeb.py', new=2)

def web_server() :
	server = BaseHTTPServer.HTTPServer
	handler = CGIHTTPServer.CGIHTTPRequestHandler
	server_address = ("", 8000)
	handler.cgi_directories = ["/"]
 
	httpd = server(server_address, handler)
	httpd.serve_forever()

def print_help() :
	print "================ COMMANDS ==============="
	print "EXIT     - Stop the station"
	print "START    - Open access to eLock"
	print "STOP     - Close access to eLock"
	print "PASSCODE - Print door passcode"
	print "UNLOCK   - Unlock the door"
	print "HELP     - Print this info again"
	print "========================================="

def parse_in_settings() :
	with open('Settings.json') as data_file:    
		data = json.load(data_file)

		global CHANNEL
		global PUBNUB_PUBLISH_KEY
		global PUBNUB_SUBSCRIBE_KEY
		global DOOR_NAME
		DOOR_NAME = data['door_name']
		CHANNEL = data['channel']
		PUBNUB_PUBLISH_KEY = data['pub_key']
		PUBNUB_SUBSCRIBE_KEY = data['sub_key']

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

print "eLOCK STATION VERSION 1.0"
parse_in_settings()
running = False

# get pass code

while (True) :
	print "Enter eLock Passcode (No Spaces):",
	input_password = raw_input()
	if (len(input_password.split(' ')) == 1) :
		break;

PASSCODE = input_password
print "eLockStation Starting..."

launch_web_server()

PUBNUB = Pubnub(publish_key=PUBNUB_PUBLISH_KEY,
                    subscribe_key=PUBNUB_SUBSCRIBE_KEY,
                    cipher_key='',
                    ssl_on=False
                    )
server_thread = None
running = True

# ========================================

open_access()
print_help()

while (running) :
	input_string = raw_input()
	process_command(input_string)

close_access()
os._exit(0)
