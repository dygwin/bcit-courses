/* add.c */
   
/* Provides the methods used to add records to a record_list, extracting
   data from a line, and initializing, destroying, and dynamically allocating
   memory for the record_list. Also contains data validation methods. */

#define LINESIZE 512
#define NAMESIZE 20
#ifndef BLOCK
#define BLOCK 2
#endif
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <ctype.h>
#include "record.h"

void to_lower_case(char line[]);
void add(const char line[], record_list *list);
void list_init(record_list *list);
void list_destroy(record_list *list);

int validate_add(const char firstName[], const char lastName[], int score);
int hyphen_check(const char name[]);

/* Adds a record to the record_list, first ensuring that the data is valid and
   contains the correct amount of arguments (at least 3 not including add),
   then converting the first and last names to lower case, then formatting
   and adding the record to the record_list . Extra arguments are ignored. */
void add(const char line[], record_list *list) {
	char   firstName[NAMESIZE];
	char   lastName[NAMESIZE];
	int    score;
	float  testScore;
	record rec;
	
	if(sscanf(line, "%*s %s %s %d", firstName, lastName, &score) == 3) {
		
		/* Ensures the number is an integer and not a float */
		if(sscanf(line, "%*s %*s %*s %*d %f",  &testScore) == 1) {
			return;
		}
		
		/* Validates the rest of the data */
		if (validate_add(firstName, lastName, score)) {
			to_lower_case(firstName);
			to_lower_case(lastName);
		} else {
			return;
		}
		
		strcpy(rec.name.last, lastName);
		strcpy(rec.name.first, firstName);
		rec.score = score;
		
		if (list->nused == list->nalloc) {
			record *tmp = realloc(list->data, (list->nalloc + BLOCK) * sizeof(record));
			
			if (tmp == 0) {
				return;
			}
			
			#ifdef DEBUG
				printf("#\n");
			#endif
			
			list->data = tmp;
			list->nalloc += BLOCK;
		}
		list->data[list->nused++] = rec;
			
		printf("%s\n", "OK");
	}	
}

/* Validates that the firstName, lastName, and score all meet their
   requirements. If any of them don't meet them, 0 is returned (false).
   Otherwise, 1 is returned. */
int validate_add(const char firstName[], const char lastName[], int score) {
	if (strlen(firstName) > 19 || strlen(firstName) < 2) {
		return 0;
	}
	
	if (strlen(lastName) > 19 || strlen(lastName) < 2) {
		return 0;
	}
	
	if (score < 0 || score > 100) {
		return 0;
	}
	
	if (!hyphen_check(firstName)) {
		return 0;
	}
	
	if (!hyphen_check(lastName)) {
		return 0;
	}
	
	return 1;
}

/* Checks the string if it contains alphabetic characters or hyphens. Hyphens
   are not allowed in the beginning or end of the string. If these criteria are
   met, 1 is returned. Otherwise, 0 is returned (failed). */
int hyphen_check(const char name[]) {
	size_t i;
	
	if (name[0] == '-' || name[strlen(name) - 1] == '-') {
		return 0;
	}
	
	for (i = 0; i < strlen(name); i++) {
		if (!isalpha((unsigned char)name[i])) {
			if (name[i] != '-') {
				return 0;
			}
		}
	}
	
	return 1;
}

/* Converts a string to entirely lower-case characters */
void to_lower_case(char line[]) {
	size_t i;
	
	for (i = 0; i < strlen(line); i++) {
		line[i] = tolower((unsigned char)line[i]);
	}
}

/* Initializes a records_list by setting all the values to null */
void list_init(record_list *list) {
	list->data   = 0;
	list->nalloc = 0;
	list->nused  = 0;
	
	#ifdef DEBUG
		printf("#\n");
	#endif
}

/* Destroys the records_list by freeing the data and nullifying it */
void list_destroy(record_list *list) {
	free(list->data);
	list->data   = 0;
	list->nalloc = 0;
	list->nused  = 0;
	
	#ifdef DEBUG
		printf("@\n");
	#endif
}