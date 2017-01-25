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
        print("eLock ID:", elockID.text ?? "N/A")
        print("pubKey:", pubKey.text ?? "N/A")
        print("subKey:", subKey.text ?? "N/A")
        print("channelName:", channelName.text ?? "N/A")
        print("Password:", password.text ?? "N/A")
    }
    
    
    @IBAction func tryQrPressed(_ sender: Any) {
        
    }
    
}

