@echo off
title lhj-blog
chcp 65001
java -Dfile.encoding=utf-8 -jar D:/lhj-blog/发布/lhj-blog-api-1.0-SNAPSHOT.jar --spring.config.location=D:/lhj-blog/发布/application.properties
