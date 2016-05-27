# RecRoom

###Description
A platora of games to be played with friends over the Internet. 
This includes a client desktop app (made with JavaFX), and a server that runs on WebSockets.

###Server
Use a web container that provides an implementation of Java's API for WebSockets (JSR 356). We recommend Tomcat or GlassFish. Make sure that the Maven dependencies listed in pom.xml are also on the server.

To set up GlassFish or Tomcat in IntelliJ, go to Run > Edit Configurations. Click the green plus on the upper left side to add a new run configuration and select GlassFish Server or Tomcat Server From the list. For GlassFish, you'll first need to set Server Domain (domain1 by default). Then, under the deployment tab, you'll need to add an artifact for deployment. Sometimes IntelliJ might show you a warning saying "No artifacts marked for deployment" with a button saying "Fix" next to it. Clicking on "Fix" will add the artifact automagically.  This artifact should be a WAR (Web Application Archive or Web Application Exploded) that contains the server compiled classes and the dependencies from Maven. To configure the artifact in IntelliJ, go to File > Project Structure > Artifacts and click on the green plus on the upper left side to add a new artifact or select an existing artifact from the list to configure.
###Client
Clients can be written in any language as long as the WebSockets standard is supported in that language. Our client is written in Java using JavaFX for the graphical elements and [Tyrus](https://tyrus.java.net/) for WebSockets. If you're running the server on your local host, make sure the websocket URIs are pointing to the correct deployment address.

### Credits
Some icons used in the client were made by [Freepik](http://www.freepik.com) and [Madebyoliver](http://www.flaticon.com/authors/madebyoliver) from [www.flaticon.com](http://www.flaticon.com) and are licensed under a [CC 3.0 BY](http://creativecommons.org/licenses/by/3.0/) license.

Fonts are from [Google Fonts](https://www.google.com/fonts)
