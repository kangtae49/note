FROM ubuntu
MAINTAINER kkt <kangtae49@gmail.com>

RUN apt-get update

# locale
RUN apt-get install -y locales locales-all
ENV LC_ALL ko_KR.UTF-8
ENV LANG ko_KR.UTF-8
ENV LANGUAGE ko_KR.UTF-8

RUN apt-get install -y vim
RUN apt-get install -y net-tools

# SSH
RUN apt-get install -y openssh-server
EXPOSE 22
RUN ssh-keygen -q -t rsa -N '' -f ~/.ssh/id_rsa
ADD authorized_keys /root/.ssh/
RUN chmod 644 /root/.ssh/authorized_keys

RUN apt-get install -y curl
RUN apt-get install -y unzip
RUN apt-get install -y telnet
RUN apt-get install -y git

# python
RUN apt-get install -y python

# haskell
RUN apt-get install -y haskell-platform
RUN curl -sSL https://get.haskellstack.org/ | sh

# java8
RUN apt-get install -y software-properties-common
RUN \
  echo oracle-java8-installer shared/accepted-oracle-license-v1-1 select true | debconf-set-selections && \
  add-apt-repository -y ppa:webupd8team/java && \
  apt-get update && \
  apt-get install -y oracle-java8-installer && \
  rm -rf /var/lib/apt/lists/* && \
  rm -rf /var/cache/oracle-jdk8-installer

# MYSQL
RUN apt-get update && \
        apt-get -y install mysql-server && \
        mkdir -p /var/lib/mysql && \
        mkdir -p /var/run/mysqld && \
        mkdir -p /var/log/mysql && \
        chown -R mysql:mysql /var/lib/mysql && \
        chown -R mysql:mysql /var/run/mysqld && \
        chown -R mysql:mysql /var/log/mysql

COPY mysqld_charset.cnf /etc/mysql/conf.d/mysqld_charset.cnf


# mariadb
#RUN apt-get update && \
#        apt-get -y install mariadb-server && \
#        mkdir -p /var/lib/mysql && \
#        mkdir -p /var/run/mysqld && \
#        mkdir -p /var/log/mysql && \
#        chown -R mysql:mysql /var/lib/mysql && \
#        chown -R mysql:mysql /var/run/mysqld && \
#        chown -R mysql:mysql /var/log/mysql

#COPY mysql.cnf /etc/mysql/conf.d/mysql.cnf

VOLUME ["/apps", "/var/lib/mysql"]

WORKDIR /apps
