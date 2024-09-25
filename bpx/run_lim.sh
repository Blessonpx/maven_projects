
java -jar  target/bpx-1-spring-boot.jar  --spring.datasource.url="jdbc:mysql://localhost:3306/testdb_1" --spring.datasource.username="blesson" --spring.datasource.password="BPXmysql*247" --spring.datasource.driver-class-name="com.mysql.cj.jdbc.Driver" --spring.jpa.hibernate.ddl-auto="none" --spring.jpa.database-platform=org.hibernate.dialect.MySQLDialect  
#--spring.jpa.hibernate.naming.physical-strategy=org.hibernate.boot.model.naming.PhysicalNamingStrategyStandardImpl 

#--spring.jpa.hibernate.naming_strategy=org.hibernate.cfg.EJB3NamingStrategy 
#--spring.jpa.hibernate.dialect = org.hibernate.dialect.MySQL5Dialect  
#--spring.jpa.hibernate.naming_strategy = org.hibernate.cfg.ImprovedNamingStrategy
