/* rsort.c */
   
/* This program uses dynamically allocated memory to store a record_list,
   a list of records that contain the firstname, lastname, and score of a
   person. The program continually prompts for input from the user where they
   can choose to add or sort. Adding is done by typing "add fname lname score",
   and sort is done by typing sort, with the optional switches of -n +n -s +s. 
   The program is exited by entering the EOF key, and memory is freed on exit.
   */

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
#include "sort.h"
#include "add.h"

void show_prompt();

int get_input(char line[]);

/* Continually prompts the user for an add or sort command,
   then executes it. Added record's are stored in the record_list.
   Memory for the records_list is deallocated on exit. */
int main(void) {
	char        input[LINESIZE];
	int         command;
	record_list records;

	list_init(&records);
	
	while (1) {
		show_prompt();
				
		if((command = get_input(input))) {
			if (command == 1) {
				add(input, &records);
			} else if (command == 2) {
				sort(input, &records);
			} else if (command == 4) {
				break;
			}
		}
	}
	
	list_destroy(&records);
	
	return 0;
}

/* Prompts the user with what valid commands they can input */
void show_prompt() {
	fprintf(stderr, "\n%s\n", "Enter a command:");
	fprintf(stderr, "%s\n", "1) add");
	fprintf(stderr, "%s\n", "2) sort");
}

/* Reads in a line from the user via stdin. If the line starts with add, 1 is
   returned. If the line starts with sort, 2 is returned. 
   Any other first word causes the function to return 0 (false). */
int get_input(char line[]) {
    char type[10];
	/* reads in a line from the user via stdin */
	if (fgets(line, LINESIZE, stdin)) {
		/* checks whether the line starts with the command "add ",
		   or "sort " */
		if(sscanf(line, "%s %*s %*s %*d", type) == 1) {
			if (strcmp("add", type) == 0) {
				return 1;
			} else if (strcmp("sort", type) == 0) {
				return 2;
			}
		}
	} else {
		return 4;
	}
	/* the line didn't start with add or sort, so return 0 (false) */
	return 0;
}