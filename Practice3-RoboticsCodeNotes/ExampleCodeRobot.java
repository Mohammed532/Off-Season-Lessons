//This is just example code. 
//!!!PLEASE DONT RUN!!!

//All of these import are from the robot library that will be provided to us 

import edu.wpi.first.wpilibj.Joystick; //Used to get input from Joystick
import edu.wpi.first.wpilibj.Spark; //Used to control the motor controller for the motor
import edu.wpi.first.wpilibj.TimedRobot; //Don't worry about this
import edu.wpi.first.wpilibj.Timer; //Imports a timer into the program
import edu.wpi.first.wpilibj.drive.DifferentialDrive; //I explain this further down

public class ExampleCodeRobot extends TimedRobot {
   ////////////////////////ROBOT OBJECTS////////////////////////
    /*
        The code below is an example of how it looks like to create
        an object. If you did Practice 2, you would be familiar with this, 
        with the Scanner object. Just like you would use a real life object, when coding in 
        java, you can create then "use" objects throughout your code. For example, you can create a
        motor controller object(Spark), or at least a coded representation of the real thing.
        Each time you create an object, you follow this format:
        objectType objectName = new objectType(parameter);
        Where objectType is the object you're creating (like Spark), objectName is the name you're giving
        it, and parameter is information the object needs to build itself.
        Don't worry about the private and final keyword at the start, i'll explain that later.
    */
    private final Spark motor1 = new Spark(0); //Creates a new Spark(motor controller) object. Takes in a parameter of the PWM address(I'll explain in robotics)
    private final Spark motor2 = new Spark(1); //Same as above
    private final DifferentialDrive m_robotDrive
        = new DifferentialDrive(motor1, motor2);
    /*
        Time to explain this. So, this object takes two motor controllers and groups them in a special way. It groups them in a way that
        makes it so we can tell them how to drive forward and backwards, and turn left and right. I'll give an exact explanation of how this works
        in person
    */
    private final Joystick m_stick = new Joystick(0); //Creates joystick object, which takes input from the joystick. Takes the computer port it is connected to as a parameter.
    private final Timer m_timer = new Timer(); //Creates timer object. Used to count seconds passed by
////////////////////////////////////////////////////////////////////////////

//////////////////////////////////////////VARIBLES/////////////////////////////

    /*
        You can also code varibles in here.
    */

    double speedInM = 50.4;
    int feetFromWall = 10;
    boolean scorePoint = false;

////////////////////////////////////////////////////////////////////////////////


  
    @Override
    public void robotInit() {
          /**
            * This function is run when the robot is first started up and should be
            * used for any initialization code.
            */
    }

    
    @Override
    public void autonomousInit() {
        /**
        * This function is run once each time the robot enters autonomous mode.
        */

        m_timer.reset(); //Resets timer to zero
        m_timer.start(); //Starts the timer
    }

  
    @Override
    public void autonomousPeriodic() {
        /**
        * This function is called periodically during autonomous.
        */

        // Drive for 2 seconds
        if (m_timer.get() < 2.0) { //If the timer is less than 2 seconds
        m_robotDrive.arcadeDrive(0.5, 0.0); // drive forwards half speed
        } else { //If timer reaches 2 seconds
        m_robotDrive.stopMotor(); // stop robot
        }
        //arcadeDrive takes two parameters: first is speed, with 1.0 being full speed forward, 0.5 half, 0.0 full stop.
        //It also goes in the other direction, with -1.0 full speed BACKWARD, and so on.
        //The second parameter is how far it turns. 1.0 is turn right with full speed, 0.5 with half speed, 0.0 don't turn.
        //-1.0 is turn left full speed, and so on.
    }

    
    @Override
    public void teleopInit() {
        /**
        * This function is called once each time the robot enters teleoperated mode.
        */
    }

    /**
    * This function is called periodically during teleoperated mode.
    */
    @Override
    public void teleopPeriodic() {
        m_robotDrive.arcadeDrive(m_stick.getY(), m_stick.getX());
        //m_stick is the joystick object. getY() gets the Y axis value of the stick, which ranges from -1.0 to 1.0, and assigns
        //it to the forward/backward speed (Going fully forward will make it go full speed, Going back vice versa)
        //getX gets the X axis value of the stick, which also ranges from -1.0 to 1.0, and assigns it to the turning speed
        //(Going fully right will make it turn right full speed, vice versa)
    }

   
    @Override
    public void test0Periodic() {
        /**
        * This function is called periodically during test mode.
        */
    }
}
