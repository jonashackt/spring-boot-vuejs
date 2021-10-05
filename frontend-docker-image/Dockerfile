FROM nginx

# COPY ./nginx.conf /etc/nginx/nginx.conf

WORKDIR /app

# COPY nginx.conf /etc/nginx
COPY target/app /usr/share/nginx/html

EXPOSE 80 443

CMD ["nginx", "-g", "daemon off;"]
