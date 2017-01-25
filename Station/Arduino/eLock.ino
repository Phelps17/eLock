int red = 12;
int blue = 11;
int green = 10;
int lock = 8;

void setup() {
  pinMode(red, OUTPUT);  
  pinMode(blue, OUTPUT);
  pinMode(green, OUTPUT); 
  pinMode(lock, OUTPUT);
  
  //Begins the serial connection
  Serial.begin(9600);
}

void loop(){
  
 if(Serial.available() > 0){      
        if (Serial.read() == '1') {
          //unlock
          digitalWrite(green, HIGH);
          digitalWrite(lock, HIGH);
          delay(1000);
          digitalWrite(green, LOW);
          digitalWrite(lock, LOW);
        }
        else 
        {
          //lock
          digitalWrite(blue, HIGH);
          delay(1000);
          digitalWrite(blue, LOW);
        }
        
        Serial.flush();
  }
  
}
