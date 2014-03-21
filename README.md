## seq_http

This is a small HTTP service that generates simple mathematical sequences. Its
purpose is two-fold:

1. Demonstrate basics of a Compojure application

2. Integrate easily into the docker demo project 'docker_seq': https://github.com/aslag/docker_seq

### Environment

Below are a few leiningen (cf. http://leiningen.org/) commands used to work with the project. To set up a development environment in docker, see https://github.com/aslag/docker_seq.

Starting the application:

    lein ring server-headless 3000

This will start an auto-reloading development server in Jetty.

### Packaging

Packaging the application for distribution:

    lein ring uberjar

### Known Issues

+ The application doesn't handle exceptions properly: it reveals them in
  stacktraces to the user. Find a good way to fix this.

+ Compojure doesn't seem to provide content negotiation out of the box.
  Find out how others do this or find a way to work support for this in.
