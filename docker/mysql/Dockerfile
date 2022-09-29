FROM mysql
MAINTAINER bartbie37@gmail.com

# base MySQL image runs all SQL files from this directory
COPY sql/* /docker-entrypoint-initdb.d/

# password for MySQL db
ENV MYSQL_ROOT_PASSWORD root

# default MySQL port
EXPOSE 3306
