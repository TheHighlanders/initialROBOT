void setup()
{
 Serial.begin(9600);
}
void loop()
{
 if (analogRead(A0) > 700) Serial.println("No object!");
 else Serial.println("Object detected!");
 delay(100);
}

