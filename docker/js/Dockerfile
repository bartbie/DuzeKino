FROM nginx:latest
MAINTAINER bartbie37@gmail.com

ARG NGINX_CONFIG_PATH

COPY ./src/frontend/* /usr/share/nginx/html/.

COPY ${NGINX_CONFIG_PATH} /etc/nginx/conf.d/default.conf

EXPOSE 63342
