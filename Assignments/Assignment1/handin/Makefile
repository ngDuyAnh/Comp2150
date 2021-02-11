# Compiler
CC = javac
RUN = java

# Files
ALL_FILES = ./allFiles/*.java
SRC_FILES = ./srcFiles/WikiManager.java ./srcFiles/*.java

# Target
WikiManagerCompile : $(SRC_FILES)
	$(CC) $(SRC_FILES)

WikiManager : $(WikiManagerCompile)
	$(RUN) WikiManager

