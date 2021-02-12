# Compiler
CC = javac
RC = java

# Directory
APP_NAME = WikiManager
COMPILE_OUT = ./compiled

# Target

WikiManagerCompile :
	$(CC) -d $(COMPILE_OUT) WikiManager.java UserManager.java User.java StringItem.java Printable.java Log.java LogPackage.java List.java ItemNode.java Item.java DocumentManager.java Document.java

WikiManager : $(WikiManagerCompile)
	$(RC) -classpath $(COMPILE_OUT) $(APP_NAME)

WikiManagerTestCompile : $(WikiManagerCompile)
	$(CC) -d $(COMPILE_OUT) -cp .:junit-platform-console-standalone-1.6.0.jar WikiManagerTest.java UserTest.java UserManagerTest.java StringItemTest.java LogTest.java LogPackageTest.java ListTest.java ItemNodeTest.java DocumentTest.java DocumentManagerTest.java

WikiManagerTest : $(WikiManagerTestCompile)
	$(RC) -jar junit-platform-console-standalone-1.6.0.jar --class-path $(COMPILE_OUT) --scan-class-path

clear :
	rm -rf $(COMPILE_OUT)/*.class

