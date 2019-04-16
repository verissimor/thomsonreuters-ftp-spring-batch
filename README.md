# Thomson Reuters - Spring batch

This project is an example using an example csv data source from Thomson Reuters. The csv file is storage on ftp server. Technologies used were springboot and spring batch to read the csv file, sql server database and jpa/hibernate for the data layer. Some end-points rest were also generated using the spring RestController, and were documented using the swagger. For tests was applied BDD cucumber.

## 1 - The data source

The CSV file was found in the oquad project on thomsonreuters github (https://github.com/thomsonreuters/oqad/blob/master/example/output/alpha.dockets.qad.data.csv).

![010 - data csv](https://user-images.githubusercontent.com/2000159/56194563-2e68ef80-602b-11e9-92bd-3a6a9e5cea92.png)

## 2 - Database sql server

Created a sql server database, with force some transformations on csv data:

![020 - model er](https://user-images.githubusercontent.com/2000159/56196458-c6b4a380-602e-11e9-8cea-437fbd8b06b9.png)


## 3 - Ftp local to storage the file

Using `pure-ftpd`, an ftp server is created and the csv file is starred on this server.

![030 - ftp server](https://user-images.githubusercontent.com/2000159/56197233-4abb5b00-6030-11e9-85f5-3cad8e14709b.png)


## 4 - Spring batch

The spring batch is used to:

1. Download the csv file from ftp server
2. Read the csv lines using `FlatFileItemReaderBuilder` to a dto `DocketLineDTO`
3. Process and transform the data `ItemProcessor<DocketLineDTO, Docket>` converting to three entities
4. Persist the data using `ItemWriter<Docket>`

## 5 - Rest End points

Before start the project, is possible use access the swagger `http://localhost:8082/swagger-ui.html`. The ports are: 8082 running by compose; and 8081 running by eclipse.

![040 - swagger](https://user-images.githubusercontent.com/2000159/56197637-2318c280-6031-11e9-8b8c-2c642d6e5542.png)

## 6 - Cucumber tests

The tests were written in cucumber.

```cucumber
Feature: Docket
  Business rules for the Docket entity and import process 

  Scenario: The field price eod shoud be positive
    Given a docket with price eod "-1.0"
    When save the docket
    Then an error is showed about price eod 

Feature: Company
  Business rules for the Company entity and import process 

  Scenario: Success to save a new companies
    Given the following companies:
      | companyCod | name                             | phone        | ceo                 |
      |            | AIR PRODUCTS AND CHEMICALS, INC. | 447563444783 | William Albuquerque |
      |            | AUTOZONE, INC.                   | 447823464731 | Kaio Grealo         |
      |            | BAXTER INTERNATIONAL, INC.       | 422223224185 | Juan Ramiro         |
    When save the company
    Then there are 3 companies saved 

[...]

```

To run the testes `mvn test`, or, `Run as > Junit Test` on file `/ftpbatch/src/test/java/com/verissimo/thomsonreuters/ftpbatch/FtpbatchApplicationCucumberTest.java`.


![050 - cucumber](https://user-images.githubusercontent.com/2000159/56198749-72f88900-6033-11e9-8d9e-456836d4625c.png)


## 7 - Docker image and running the project

The image is created using maven plugin. 

Run `./mvnw install dockerfile:build` to create a docker image. The build step run tests, make executable jar and build a docker image.

To run the project use `docker-compose up -d`.
