from pubnub import Pubnub

PUBNUB_PUBLISH_KEY = "pub-c-a8732a53-6069-4292-8981-a1a9a230172f"
PUBNUB_SUBSCRIBE_KEY = "sub-c-a27f6252-e02e-11e6-989b-02ee2ddab7fe"

CHANNEL = "eLockServer"

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