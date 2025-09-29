/*
--------------------------------------------------
Project: a3q2
File:    myword.c
Author:  Navin Sethi
Version: 2025-09-12
--------------------------------------------------
*/

#include <stdio.h>
#include <string.h>
#include "mystring.h"
#include "myword.h"

#define MAX_LINE_LEN 1000
#define MAX_WORDS 1000



int create_dictionary(FILE *fp, char *dictionary) {
// your code

    char line[1000];
    char delimiters[] = " .,\n\t\r";
    char *token;
    int words_added = 0;
    while (fgets(line, MAX_WORDS, fp) != NULL) {
        str_lower(line);
        str_trim(line);
        token = (char*) strtok(line, delimiters);
        while (token != NULL) {
            strcat(dictionary, token);
            strcat(dictionary, ",");
            token = (char*) strtok(NULL, delimiters);
            words_added++;   
        } 

    }

    return words_added;
}


BOOLEAN contain_word(char *dictionary, char *word) {
    if(strstr(dictionary, word) != NULL) {
        return TRUE;
    }
    return FALSE;
}


WORDSTATS process_words(FILE *fp, WORD *words, char *dictionary) {

    char line[1000];
    char delimiters[] = " .,\n\t\r";
    WORDSTATS ws = {0,0,0};
    char *word_token;
    int word_index = 0;

    while(fgets(line, MAX_LINE_LEN, fp) != NULL) {
        ws.line_count++;
        str_lower(line);
        word_token = (char *) strtok(line, delimiters);
        while (word_token != NULL) {
            ws.word_count++;

            if (contain_word(dictionary, word_token) == FALSE) {
                // search in words array
                int found = 0;
                for (int i = 0; i < word_index; i++) {
                    if (strcmp(words[i].word, word_token) == 0) {
                        words[i].count++;
                        found = 1;
                        break;
                    }
                }
                if (!found && word_index < MAX_WORDS) {
                    strcpy(words[word_index].word, word_token);
                    words[word_index].count = 1;
                    word_index++;
                    ws.keyword_count++;
                }
            }
            word_token = strtok(NULL, delimiters);
        }
    }
    return ws;
} 

