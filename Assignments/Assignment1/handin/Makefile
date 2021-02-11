# Compiler
CC = javac
RUN = java

# Files
ALL_FILES = ./allFiles/*.java
SRC_FILES = ./srcFiles/*.java

# Target
WikiManagerCompile : $(SRC_FILES)
	$(CC) -d ./. $(SRC_FILES)

WikiManager : $(WikiManagerCompile)
	$(RUN) WikiManager

