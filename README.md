# DDS Performance Tests

Performance test suite for the `Digital Disclosure Service`, using [performance-test-runner](https://github.com/hmrc/performance-test-runner) under the hood.

## Pre-requisites

### Services

Start Mongo Docker container as follows:

```bash
docker run --rm -d --name mongo -d -p 27017:27017 mongo:4.0
```

Start `DDS_ALL` services as follows:

```bash
sm2 --start DDS_ALL
```

### Logging

The default log level for all HTTP requests is set to `WARN`. Configure [logback.xml](src/test/resources/logback.xml) to update this if required.

## Tests

### Run smoke test

```bash
sbt -Dperftest.runSmokeTest=true -DrunLocal=true gatling:test
```

### Run full test
```
sbt -DrunLocal=true gatling:test
```

### Run the performance tests in Staging

To run a performance test against the staging environment, run the [dds-performance-tests job](https://performance.tools.staging.tax.service.gov.uk/job/dds-performance-tests/) in Jenkins.

## Scalafmt

Check all project files are formatted as expected as follows:

```bash
sbt scalafmtCheckAll scalafmtCheck
```

Format `*.sbt` and `project/*.scala` files as follows:

```bash
sbt scalafmtSbt
```

Format all project files as follows:

```bash
sbt scalafmtAll
```

## License

This code is open source software licensed under the [Apache 2.0 License]("http://www.apache.org/licenses/LICENSE-2.0.html").
