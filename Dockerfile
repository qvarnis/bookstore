FROM tomcat:9.0
COPY build/libs/vfuportalen.war $CATALINA_HOME/webapps/ROOT.war
#ENV CATALINA_OPTS="-Xdebug -Xrunjdwp:transport=dt_socket,address=*:5005,server=y,suspend=y"
# EXPOSE 5005
EXPOSE 8080