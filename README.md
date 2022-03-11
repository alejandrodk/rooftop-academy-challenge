# Seniority Boost Challenge (Text Analyzer)

## Rooftop Academy

The text analyzer divides the sent text into the number of characters specified and counts how many times each group of letters is repeated within the sentence.

- the minimum group has two characters.
- if the number of characters exceeds the text sent, the analyzer will return all the text.

[Full chanllenge description](https://docs.google.com/document/d/1IOor92V_W-HRqPFw6fhvh9O7tEBZ1Wry8Wj81Izcrrk/edit#heading=h.zh9kijrslsmq)
  
### Endpoints


|method|description|url|params|
|--|--|--|--|
|POST|create Text|/text|-|
|GET |list Texts|/text|chars, page, rpp (results per page)|
|GET|Text detail|/text/:id|-|
|DELETE|Remove Text|/text/:id|-|

##### Sample creation body

```json
// POST: /text/
{
  "text": "sample text",
  "chars" 4 // optional parameter (default 2)
}
```

##### Sample creation response

```json
{
  "id": 1,
  "url": "/text/1"
}
```

##### Sample Text

```json
// text: "test"
// chars: 2

{
    "id": 1,
    "hash": "3167b7a0317bfa5ae6a36454dc4dd0bc",
    "chars": 2,
    "result": {
        "te": 1,
        "es": 1,
        "st": 1,
    }
}
```
### How to run project?

1- Create *.jar* file in target folder

```bash
mvn clean install
```

2- Execute .jar file created before

```bash
java -jar ./target/javawebapp.jar
```

3- Test api

```bash
GET: localhost:8080/ping
```