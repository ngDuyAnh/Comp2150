# Compiler
CC = javac
RC = java

# Information
APP_NAME = WhoDunIt
COMPILE_OUT = ./compiled

# Target

WhoDunItCompile :
	$(CC) -d $(COMPILE_OUT) WhoDunIt.java Model.java HumanPlayer.java ComputerPlayer.java IPlayer.java Card.java Guess.java

WhoDunIt : $(WhoDunItCompile)
	$(RC) -classpath $(COMPILE_OUT) $(APP_NAME)

ComputerPlayerTestCompile : $(WhoDunItCompile)
	$(CC) -d $(COMPILE_OUT) -cp .:junit-platform-console-standalone-1.6.0.jar ComputerPlayerTest.java

ComputerPlayerTest : $(ComputerPlayerTestCompile)
	$(RC) -jar junit-platform-console-standalone-1.6.0.jar --class-path $(COMPILE_OUT) --scan-class-path

clear :
	rm -rf $(COMPILE_OUT)/*.class

