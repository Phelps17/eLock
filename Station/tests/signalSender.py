from pubnub import Pubnub

PUBNUB_PUBLISH_KEY = "demo"
PUBNUB_SUBSCRIBE_KEY = "demo"

CHANNEL = "eLock Server"

if __name__ == "__main__":
    pubnub = Pubnub(publish_key=PUBNUB_PUBLISH_KEY,
                    subscribe_key=PUBNUB_SUBSCRIBE_KEY,
                    cipher_key='',
                    ssl_on=False
                    )

    while (1) :
    	print ">",
    	input_string = raw_input()
    	pubnub.publish(CHANNEL, input_string)