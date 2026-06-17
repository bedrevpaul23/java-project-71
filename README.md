### Hexlet tests and linter status:
[![Actions Status](https://github.com/bedrevpaul23/java-project-71/actions/workflows/hexlet-check.yml/badge.svg)](https://github.com/bedrevpaul23/java-project-71/actions)

### Build status:
[![Build](https://github.com/bedrevpaul23/java-project-71/actions/workflows/main.yml/badge.svg)](https://github.com/bedrevpaul23/java-project-71/actions/workflows/main.yml)

### Maintainability and test coverage:
[![Quality Gate Status](https://sonarcloud.io/api/project_badges/measure?project=bedrevpaul23_java-project-71&metric=alert_status)](https://sonarcloud.io/summary/new_code?id=bedrevpaul23_java-project-71)
[![Coverage](https://sonarcloud.io/api/project_badges/measure?project=bedrevpaul23_java-project-71&metric=coverage)](https://sonarcloud.io/summary/new_code?id=bedrevpaul23_java-project-71)

# Difference Calculator

Difference Calculator is a command-line utility that compares two configuration files and shows the difference.

Supported input formats:

- JSON
- YAML

Supported output formats:

- stylish
- plain
- json

## Requirements

- Java 21
- Gradle

## Build

```bash
make build
```

## Run

```bash
./gradlew installDist
./app/build/install/app/bin/app app/src/test/resources/file1.json app/src/test/resources/file2.json
```

## Usage

```bash
./app/build/install/app/bin/app [-f=format] filepath1 filepath2
```

The default output format is `stylish`.

## Stylish output

```bash
./app/build/install/app/bin/app app/src/test/resources/file1.json app/src/test/resources/file2.json
```

```text
{
  - follow: false
    host: hexlet.io
  - proxy: 123.234.53.22
  - timeout: 50
  + timeout: 20
  + verbose: true
}
```

## Plain output

```bash
./app/build/install/app/bin/app -f plain app/src/test/resources/nested_file1.json app/src/test/resources/nested_file2.json
```

```text
Property 'chars2' was updated. From [complex value] to false
Property 'checked' was updated. From false to true
Property 'default' was updated. From null to [complex value]
Property 'id' was updated. From 45 to null
Property 'key1' was removed
Property 'key2' was added with value: 'value2'
Property 'numbers2' was updated. From [complex value] to [complex value]
Property 'numbers3' was removed
Property 'numbers4' was added with value: [complex value]
Property 'obj1' was added with value: [complex value]
Property 'setting1' was updated. From 'Some value' to 'Another value'
Property 'setting2' was updated. From 200 to 300
Property 'setting3' was updated. From true to 'none'
```

## JSON output

```bash
./app/build/install/app/bin/app -f json app/src/test/resources/nested_file1.json app/src/test/resources/nested_file2.json
```

```json
[ {
  "status" : "unchanged",
  "key" : "chars1",
  "value" : [ "a", "b", "c" ]
}, {
  "status" : "changed",
  "key" : "chars2",
  "oldValue" : [ "d", "e", "f" ],
  "newValue" : false
} ]
```
