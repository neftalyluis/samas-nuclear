./liquibase --driver=com.mysql.jdbc.Driver \
        --url=jdbc:mysql://localhost:3306/samas?useSSL=false  \
        --username=samas \
        --password=test \
    diffChangeLog \
        --referenceUrl=jdbc:mysql://samas.cllibwzh1wfc.us-west-2.rds.amazonaws.com:3306/samas?useSSL=false  \
        --referenceUsername=samas \
        --referencePassword=Abcd1234#
