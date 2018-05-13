FROM ubuntu
MAINTAINER kkt <kangtae49@gmail.com>

RUN apt-get update
RUN apt-get install openssh-server -y

ENTRYPOINT ssh mysql start

EXPOSE 22
