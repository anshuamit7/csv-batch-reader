# csv-batch-reader
Reading csv file through spring data +spring boot+Multithreading  and dumping to in memory database

Application name - csv-batch-reader
Description - Reading the input csv orders through batch processing ,
                applying logic to get country and store in h2 database


**Techstack & Framework used**

    Spring boot
    Spring batch
    jpa
    H2 database
    Junit5
    Mockito Framework


**Used Dependencies**

    Core
    Spring
    Spring Boot
    Spring Batch
    Spring Web
    Spring Data
    Spring Data JPA
    Database
    h2
    Lombok

**Execution Screenshots:**

    2 folders is added in project having execution screenshots and postman screenshots to submit job.
    Helpful to understand quickly.

**Steps to run:-**

1. Import project as existing maven project
2. set the jdk version for compilation and execution if required
3. run the File named - CsvBatchReaderApplication.java

Application will run on embedded tomcat on port 8081, This can be changed through application.properties
(take a reference screenshot present in screenshots folder)

**4. Now open H2 database on browser(screenshot attached)**

    url - http://localhost:8081/h2-console
    Just click on connect button appear

**5.  Explore Rest APIs(I used Postman for this)**

     Method	      Url	                                               Description
    GET	     http://localhost:8081/batch/importorderjob	         Import csv orders to db

Now you need to hit the Get request (url mentioned above) and wait for the process to complete

After completion it will show the total time taken in whole process in seconds
(TOTAL TIME = complete csv data reading time + processing time + complete db insertion time)

NOTE- To increase the performance based on environment, no of cores, available resources.
 we can tweek 2 settings.
    1. In BatchConfiguration.java there is setting of chunk size(currently set 1000) and
    2. other component is concurrencyLimit(currently set 5)






