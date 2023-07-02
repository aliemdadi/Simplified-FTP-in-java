# Simplified-FTP-in-java
Simplified FTP . commands : list ,cd(change directory ) , ret (retrieve) in java



1. Run ftpserver, server starts on port 20 (it will create socket soc and open port 20 for it) then waits for a connection
   
2. Run ftpclient. Then client will be connected to the server and both of them will create an object of transferfile class which is used for transferring commands and data.
   
3. Server constructor makes data input and output streamer for transferring commands and data and starts run function.
   
4. Client constructor of transferfile class makes streamer for transferring commands and data between server and client through the socket. and makes  bufferreader object for reading our input from command line.
   
5. Inside run function, server waits for a command in a while loop and client writes string commands ,reads
from bufferreader, and on the other hand, server read commands and call our function!
6. The same process happens in other functions.
 
**Test ret command:**
-
[ MENU ]
cd for changing directory

ret for retrieve

list for listing files an folders exists in current path

exit for close connection

Enter Choice :

ret

Enter File Name :ftptext.txt

Receiving File ...

File Receive Successfully
 
 
**Test cd command:**
-
Enter Choice :

cd

Back to parent directory?  y/n?n

Enter directory path :newdir

Directory changed

**Test list command:**
-
Enter Choice :

list

Files and Folders listed in current path are:

.classpath

.project

CD

FTPServer.class

FTPServer.java

ftptext.txt

newdir

transferfile.class
