#!/bin/sh
./gradlew clean assemble
sudo docker build . -t todo
echo
echo
echo "To run the docker container execute:"
echo "    $ docker run -p 8080:8080 todo"
