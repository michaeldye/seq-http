## seq_http

This is a small HTTP service that generates simple mathematical sequences. Its
purpose is two-fold:

1. Demonstrate basics of a Compojure application

2. Integrate easily into the docker demo project 'docker_seq': https://github.com/michaeldye/docker_seq

### Environment

Below are a few leiningen (cf. http://leiningen.org/) commands used to work with the project. To deploy in a development environment in docker, see https://github.com/michaeldye/docker_seq.

Starting the application:

    lein ring server-headless 3000

This will start an auto-reloading development server in Jetty.

### Packaging

Packaging the application for distribution:

    lein ring uberjar
