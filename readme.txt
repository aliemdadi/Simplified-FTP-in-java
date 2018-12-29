1.when you run ftpserver, server starts on port20 (create socket soc and open port 20 for it) then waits for a connection
2 .then start ftpclient . it connects to server and both of them create an object of transferfile class which is used for transferring commands and data!
3.server constructor makes data input and output streamer for transferring commands and data and starts run function .
4. client constructor of transferfile class makes streamer for transferring commands and data between server and client through the socket. and makes  bufferreader object for reading our input from console. 
5 .in run function server waits for a command in a while loop and client write string command ,reads
from bufferreader, and server read commands and call our function!
6 .in other functions sth like 5 happen
 
