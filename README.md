# JIT - Just In Train - Orario Pendolari Trenitalia

This is the Official Repository of the Server used by the JIT app. [Read LICENSE (GNU)](https://github.com/JustInTrain/server_spring/blob/master/LICENSE.md)

It's built on Spring Framework and it's used as a bridge between Trenitalia's official API and the mobile apps. Its main purpose is to wrap and add more semantics and common sense to the native API since they lack of clarity and structure.

### Covered areas
The services offered so far are:
- Train Information
- Journey Information
- News
- Partial support to stations

### Offered endpoints
If you'd like to know more about the Endpoints that are offered you can do it by clicking here: [![Run in Postman](https://run.pstmn.io/button.svg)](https://app.getpostman.com/run-collection/951a59a0db4100c75583)

### Run it on your server
If you'd like to try this code out on you're own server or you want to improve you can do it by cloning this very repository

`git clone https://github.com/JustInTrain/justintrain-rest-server.git`

then just go the downloaded directory and build/run it with gradle (*JAVA 8 REQUIRED*)

`cd justintrain-rest-server`

`./gradlew bootRun`

### TODO 
- [ ] Authentication
- [ ] Requests logging
- [ ] API documentation
