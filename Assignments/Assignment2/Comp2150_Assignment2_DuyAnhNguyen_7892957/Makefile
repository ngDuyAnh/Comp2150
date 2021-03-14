CC = clang++
CFLAGS = -Wall --std=c++11

OBJ_FILES = ArrivalEvent.o CompleteCPUEvent.o CompleteIOEvent.o CPU.o CPUTimeoutEvent.o Event.o ExitProcessEvent.o IntegerItem.o IO.o ListItem.o Node.o PriorityQueue.o Process.o ProcessingUnit.o Queue.o Simulation.o StartCPUEvent.o StartIOEvent.o
TEST_FILES = PriorityQueue.o Queue.o Node.o ListItem.o IntegerItem.o

A2main: A2main.o $(OBJ_FILES)
	$(CC) $(CFLAGS) -o main $< $(OBJ_FILES)

TestFile: TestFile.o $(TEST_FILES)
	$(CC) $(CFLAGS) -o test $< $(TEST_FILES)

# Build .o file from .cpp file
%.o: %.cpp
	$(CC) -c -o $@ $< $(CFLAGS)
