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

    def callback(message, channel):
        print "Channel %s: (%s)" % (channel, message)

    print "Listening on Channel %s" % CHANNEL
    pubnub.subscribe(CHANNEL, callback)