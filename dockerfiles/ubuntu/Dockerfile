FROM ubuntu
MAINTAINER kkt <kangtae49@gmail.com>

RUN apt-get update

# locale
RUN apt-get install -y locales locales-all
ENV LC_ALL ko_KR.UTF-8
ENV LANG ko_KR.UTF-8
ENV LANGUAGE ko_KR.UTF-8

RUN apt-get install -y vim
#RUN apt-get install -y python

# SSH
RUN apt-get install -y openssh-server
EXPOSE 22
RUN ssh-keygen -q -t rsa -N '' -f ~/.ssh/id_rsa
ADD authorized_keys /root/.ssh/
RUN chmod 644 /root/.ssh/authorized_keys

# MYSQL
#RUN apt-get install -y mysql
#EXPOSE 3306

VOLUME ["/apps"]

WORKDIR /apps
