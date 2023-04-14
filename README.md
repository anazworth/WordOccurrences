# Word Occurrences - Text Analyzer

This is an assigment for Software Development 1

### Assignment Description

Write a text analyzer that reads a file and outputs statistics about that file. It should output the word frequencies of all words in the file, sorted by the most frequently used word. The output should be a set of pairs, each pair containing a word and how many times it occurred in the file.

### Usage

Run the following command to install Maven dependencies and bootstrap the MySQL database via Docker Compose:
```bash
$ ./mvnw clean install && docker compose up -d
```
### Examples

![UI Example](https://github.com/anazworth/WordOccurrences/blob/main/screenshots/uiExample.png)
![Output Example](https://github.com/anazworth/WordOccurrences/blob/main/screenshots/theRavenExample.png)