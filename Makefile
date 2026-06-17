.PHONY: build test lint run-dist

build:
	./gradlew clean build

test:
	./gradlew test

lint:
	./gradlew checkstyleMain checkstyleTest

run-dist:
	./app/build/install/app/bin/app
