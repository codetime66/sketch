#!/bin/bash
#TODO: para buildar, instale o submodule
# ssh-keygen
# git submodule update --init
#
#winpty docker run -it --name devj8tmp -v //c/Users/carlos.fernandes/projects/:/home/projects/ -w //home/projects/link-terminal-seller-job devj:8 //bin/bash
winpty docker run --rm --name devj8 -v //c/Users/carlos.fernandes/projects/:/home/projects/ -w //home/projects/link-terminal-seller-job devj:8 mvn clean install
