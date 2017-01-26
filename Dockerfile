FROM tomcat:8.0-jre8
ENV TIMETRACK_URL="http://timetrack-legacy:8080/records"
ADD timetrack.war /usr/local/tomcat/webapps/
CMD ["catalina.sh", "run"]