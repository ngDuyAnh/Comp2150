
in Server.h


class Server
{
private:
    const char* const URL;
    bool running = false;
    
protected:
    bool isRunning();

public:
    Server(const char* const URL);
    void start();
	void stop();
	virtual bool connect(char* const userID) = 0;
}



in Server.cpp

Server::Server(const char* const SERVER_URL) : URL(SERVER_URL)
{
	this->running = false;
}

void Server::start()
{
	if (this->running == false)
	{
		this->running = true;
	}
}

void Server::stop()
{
	if (this->running == true)
	{
		this->running == false;
	}
}

bool Server::isRunning()
{
	return this->running;
}



in WebServer.h

class WebServer : public Server
{
public:
    WebServer(const char* const URL);
    bool connect(char* const userID) override;
}


in WebServer.cpp

WebServer::WebServer(const char* const WEB_URL) : Server::Server(WEB_URL)
{
	
}

bool WebServer::connect(char* const userID)
{
	// Local variable dictionary
	bool userConnect = false;
	
	// Check if the server if online
	if (Server::isRunning())
	{
		userConnect = true;
	}
	
	// Return the user connect to the server
	return userConnect;
}
