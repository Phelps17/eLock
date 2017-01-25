//
//  ViewController.swift
//  eLock
//
//  Created by Tyler Phelps on 1/24/17.
//  Copyright © 2017 Tyler Phelps. All rights reserved.
//

import UIKit
import PubNub

class ViewController: UIViewController {
    @IBOutlet weak var elockID: UITextField!
    @IBOutlet weak var pubKey: UITextField!
    @IBOutlet weak var subKey: UITextField!
    @IBOutlet weak var channelName: UITextField!
    @IBOutlet weak var password: UITextField!
    
    
    override func viewDidLoad() {
        super.viewDidLoad()
        // Do any additional setup after loading the view, typically from a nib.
        
    }

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }


    @IBAction func unlockButtonPressed(_ sender: Any) {
        let eLockIDString = elockID.text ?? "N/A"
        let pubKeyString = pubKey.text ?? "N/A"
        let subKeyString = subKey.text ?? "N/A"
        let channelNameString = channelName.text ?? "N/A"
        let passwordString = password.text ?? "N/A"
        
        print("eLock ID:", eLockIDString)
        print("pubKey:", pubKeyString)
        print("subKey:", subKeyString)
        print("channelName:", channelNameString)
        print("Password:", passwordString)
        
        if ((eLockIDString != "") && (pubKeyString != "") && (subKeyString != "") && (channelNameString != "")) {
            
            let config = PNConfiguration(publishKey: pubKeyString, subscribeKey: subKeyString)
            let client = PubNub.client(with: config)
        
            client!.publish("Hello from the PubNub Swift SDK", toChannel: channelNameString,
                       compressed: false, withCompletion: { (publishStatus) -> Void in
                        
                            if !publishStatus!.isError {
                                print("SUCCESS!")
                                // Message successfully published to specified channel.
                            }
                            else {
                                print("ERROR!!!")
                            /**
                             Handle message publish error. Check 'category' property to find out
                             possible reason because of which request did fail.
                             Review 'errorData' property (which has PNErrorData data type) of status
                             object to get additional information about issue.
                             
                             Request can be resent using: publishStatus.retry()
                             */
                            }
            })
        
            print("DONE")
        }
        else {
            print("NO")
        }
    }
    
    
    @IBAction func tryQrPressed(_ sender: Any) {
        
    }
    
}

