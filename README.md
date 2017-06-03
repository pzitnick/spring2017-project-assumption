[![Build Status](https://travis-ci.org/cpe305Spring17/spring2017-project-assumption.svg?branch=master)](https://travis-ci.org/cpe305Spring17/spring2017-project-assumption) [![Quality Gate](https://sonarqube.com/api/badges/gate?key=Assumption%3AAssumption-JPTracer)](https://sonarqube.com/dashboard/index/Assumption%3AAssumption-JPTracer)

# JPTracer
JPTracer is a Monte Carlo path tracer written in Java that utilizes JOCL, a Java binding for OpenCL, to offload calculations to an OpenCL compatible device (including Intel CPUs, Nvidia GPUs, AMD GPUs, etc). Path tracing is a brute force graphics rendering method that aims to produce unbiased and realistic renderings of basic 3D scenes.

## Getting Started
First clone this repository.
```bash
$ git clone https://github.com/cpe305Spring17/spring2017-project-assumption.git
```

Then build the project with:
```bash
$ mvn clean install
```

And finally run the project with:
```bash
$ java -jar Assumption-JPTracer-3.0-SNAPSHOT.jar
```
or
```bash
$ mvn exec:java -Dexec.mainClass=base.Application
```

More information can be found on [Github Pages](https://cpe305spring17.github.io/spring2017-project-assumption/).
