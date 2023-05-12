### Hexlet tests and linter status:
[![Actions Status](https://github.com/DaniilMolchanov666/java-project-71/workflows/hexlet-check/badge.svg)](https://github.com/DaniilMolchanov666/java-project-71/actions)
<a href="https://codeclimate.com/github/DaniilMolchanov666/java-project-71/maintainability"><img src="https://api.codeclimate.com/v1/badges/957e21189bfd573bd455/maintainability" /></a>
<a href="https://codeclimate.com/github/DaniilMolchanov666/java-project-71/test_coverage"><img src="https://api.codeclimate.com/v1/badges/957e21189bfd573bd455/test_coverage" /></a>

## About

Difference calculator is a program that perceives the difference between two data structures. 
This is a popular task for which there are many online services, for example: http://www.jsondiff.com/. probably used in test output or automatic usage tracking in config files.

## Utility features:

- support for different input formats: yaml and json 
- report generation in plain text, style and json

## Usage example:
```sh
# формат plain
./app --format plain path/to/file.yml another/path/file.json

Property 'follow' was added with value: false
Property 'baz' was updated. From 'bas' to 'bars'
Property 'group2' was removed

# формат stylish
./app filepath1.json filepath2.json

{
  + follow: false
  + numbers: [1, 2, 3]
    setting1: Value 1
  - setting2: 200
  - setting3: true
  + setting3: {key=value}
  + setting4: blah blah
}
```

## Start:
```sh
make
```
## Setup:
```sh
make build
```
## Run:
```sh
make run
```
## Run tests:
``` sh
make test
```
## Run checkstyle:
``` sh
make lint
```
## Run jacoco report:
``` sh
make report
```

## Project demonstration:

Stylish format:

https://asciinema.org/a/Spm7elLOeysiFqPiT1NQnksTw

Plain format:

https://asciinema.org/a/ijfPgmF28G39VcNQbDOonAOq1

Json format:

https://asciinema.org/a/wNiJp4zQbwPQcmT5kQegy6vDt
