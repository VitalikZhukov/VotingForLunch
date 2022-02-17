Where to lunch?
Java Enterprise project with registration / authorization and role-based access rights (USER, ADMIN).
Admin could create / edit / delete users, restaurants and menus, users - manage your profile (vote for the restaurant) via UI (AJAX) and REST with basic authorization.
All REST interface covered with JUnit tests by Spring MVC Test и Spring Security Test.
You can vote once a day. At 14-00, the number of votes for the restaurant is automatically reset and it becomes possible to vote again.


App on heroku: http://my-lunch.herokuapp.com/login


Technology stack:
- automated build and version control: Maven
- servlet: Apache Tomcat
- logging: SLF4J and Logback
- database: PostgreSQL, HSQLDB, Hibernate
- cache:  EHCACHE
- serialization: Json Jackson
- test: JUnit5, AssertJ, Hamcrest
- spring: core, MVC, data JPA, security
- web: jQuery, jQuery plugins, Bootstrap, DataTables, WebJars, JSTL, JSP