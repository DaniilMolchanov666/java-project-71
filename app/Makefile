
.DEFAULT_GOAL := build-run

clean:
	gradle clean

build:
	gradle clean build

install:
	gradle clean install

run-dist:
	./build/install/app/bin/app

run:
	gradle run

test:
	gradle test

lint:
	gradle checkstyleMain checkstyleTest

build-run: build run

.PHONY: build
