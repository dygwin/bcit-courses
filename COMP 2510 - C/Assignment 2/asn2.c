#include <stdio.h>
#include <string.h>
#include <ctype.h>
#define LINESIZE 512

/* asn2.c */
   
/* This program reads and writes records to a text file, the file being
   specified as an argument when the program is run. Upon running, the
   program will display text prompting you to issue a command, being either
   an add command, a display command, or a modify command. The proper way to
   issue these commands are as followed:
   "add (first name) (last name) (score)"
   "display (record number)"
   "modify (record number) (first name or *) (last name or *) (score or *)"
   If a command is issued incorrectly, they are simply prompted for a command
   again. The program is exited when a EOF is simulated, or if input is being
   read from a file, at the real EOF. There are also a number of validations
   the names and scores must pass before being added to the record. */
   
void show_prompt();
void add(const char line[], FILE *fp);
void display(const char line[], FILE *fp);
void modify(const char line[], FILE *fp);
void to_lower_case(char line[]);

int get_record(FILE *fp, int recordNum);
int get_records_after(FILE *fp, int record);
int get_records_before(FILE *fp, int record);
int validate_add(const char firstName[], const char lastName[], int score);
int modify_add(int record, char fName[], char lName[], int score, FILE *fp);
int modify_addast(int record, char fName[], char lName[], char ast, FILE *fp);
int get_old_name(int record, char name[], char pattern[], FILE *fp);
int hyphen_check(const char name[]);
int get_input(char line[]);

/* The main access point. Must be called with a single argument specifying
   the file to read from and write to. The file is opened in binary truncate
   or create read write form (w+b). Commands are continually prompted from the
   user until an EOF character is detected, whether that be the user pressing
   the EOF key, or the actual end of a file. Add, display, and modify commands
   are executed as specified by the input from the user.   */
int main(int argc, char *argv[]) {
	char input[LINESIZE];
	int  command;
	FILE *fp;
	
	/* Open the file in binary w+ mode, if it fails return an error number */
	if (argc > 1) {
		if ((fp = fopen(argv[1], "w+b" )) == 0) {
			return(2);
		}
	} else {
		return(1);
	}

	while (1) {
		show_prompt();
				
		if((command = get_input(input))) {
			if (command == 1) {
				add(input, fp);
			} else if (command == 2) {
				display(input, fp);
			} else if (command == 3) {
				modify(input, fp);
			} else if (command == 4) {
				break;
			}
		}
	}
	fclose(fp);
	
	return 0;
}

/* Prompts the user with what valid commands they can input */
void show_prompt() {
	fprintf(stderr, "\n%s\n", "Enter a command:");
	fprintf(stderr, "%s\n", "1) add");
	fprintf(stderr, "%s\n", "2) display");
	fprintf(stderr, "%s\n\n", "3) modify");
}

/* Reads in a line from the user via stdin. If the line starts with add, 1 is
   returned. If the line starts with display, 2 is returned. Finally, if
   the line starts with modify, 3 is returned. Any other first word causes
   the function to return 0 (false). */
int get_input(char line[]) {
    char type[10];
	/* reads in a line from the user via stdin */
	if (fgets(line, LINESIZE, stdin)) {
		/* checks whether the line starts with the command "add ",
		   "display ", or "modify ". */
		if(sscanf(line, "%s %*s %*s %*d", type) == 1) {
			if (strcmp("add", type) == 0) {
				return 1;
			} else if (strcmp("display", type) == 0) {
				return 2;
			} else if (strcmp("modify", type) == 0) {
				return 3;
			}
		}
	} else {
		return 4;
	}
	/* the line didn't start with add display or modify, so return 0 (false) */
	return 0;
}

/* Adds a line to the specified text file, first ensuring that the line
   contains the correct amount of arguments (at least 3 not including add),
   then converting the first and last names to lower case, then formatting
   and printing the line to the file. Extra arguments are ignored. */
void add(const char line[], FILE *fp) {
	char firstName[20];
	char lastName[20];
	int  score;
	
	if(sscanf(line, "%*s %s %s %d", firstName, lastName, &score) == 3) {
		if (validate_add(firstName, lastName, score)) {
			to_lower_case(firstName);
			to_lower_case(lastName);
			
			fprintf(fp, "%-20s%-20s%-4d", firstName, lastName, score);
			
			printf("%s\n", "OK");
		}
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

/* Displays the record(s) given for the input from the file */
void display(const char line[], FILE *fp) {
	int  record;
	char modifier;
	
	if(sscanf(line, "%*s %d%c", &record, &modifier) == 2) {
		if (modifier == '+') {
			if (get_records_after(fp, record)) {
				printf("%s\n", "OK");
			}
		} else if (modifier == '-') {
			if (get_records_before(fp, record)) {
				printf("%s\n", "OK");
			}
		} else {
			if (get_record(fp, record)) {
				printf("%s\n", "OK");
			}
		}
	}
}

/* Gets a single record from the file and prints it to stdin. Returns 1
   if the record was successfully retrieved and read. Returns 0 on failure. */
int get_record(FILE *fp, int record) {
	char firstName[20];
	char lastName[20];
	int  score;
	
	if (fseek(fp, (record - 1) * 44, SEEK_SET) == 0) {
		if (fscanf(fp, "%s %s %d", firstName, lastName, &score) == 3) {
			printf("%s, %s: %d\n", lastName, firstName, score);
			
			return 1;
		}
	}
	return 0;
}

/* Gets all records from the file starting from the specified record, and 
   prints it to stdin. Returns 1 if the records were successfully retrieved
   and read. Returns 0 on failure (no records being printed). */
int get_records_after(FILE *fp, int record) {
	size_t i = 0;
	
	while (get_record(fp, record + i)) {
		i++;
	}
	
	if (i > 0) {
		return 1;
	}
	
	return 0;
}

/* Gets all records from the file up to the specified record, and 
   prints it to stdin. Returns 1 if the records were successfully retrieved
   and read. Returns 0 on failure (no records being printed). */
int get_records_before(FILE *fp, int record) {
	size_t i;
	
	for (i = 0; i <= (size_t)record; i++) {
		get_record(fp, i);
	}
	
	if (i > 0) {
		return 1;
	}
	
	return 0;
}

/* Retrieves the input from the console when the modify command is used. This
   then calls functions to modify the records, and prints an OK message if the
   records are modified successfully */
void modify(const char line[], FILE *fp) {
	int  record;
	int  score;
	char asterisk;
	char fName[20];
	char lName[20];
	char pattern1[] = "%*s %d %s %s %d";
	char pattern2[] = "%*s %d %s %s %c";
	
	if(sscanf(line, pattern1, &record, fName, lName, &score) == 4) {
		if (modify_add(record, fName, lName, score, fp)) {
			printf("%s\n", "OK");
		}
	} else if (sscanf(line, pattern2, &record, fName, lName, &asterisk) == 4) {
		if (modify_addast(record, fName, lName, asterisk, fp)) {
			printf("%s\n", "OK");
		}
	}
}

/* Modifies a record, called when the score position is a digit. First name
   and last name are also checked to see if they are asterisks, and if they are
   then their old values are retrieved. Then the old records are replaced with
   this new record. */
int modify_add(int record, char fName[], char lName[], int score, FILE *fp) {
	if (strcmp("*", fName) == 0) {
		if (!get_old_name(record, fName, "%s %*s %*d", fp)) {
			return 0;
		}
	}
	
	if (strcmp("*", lName) == 0) {
		if (!get_old_name(record, lName, "%*s %s %*d", fp)) {
			return 0;
		}
	}
	
	if (validate_add(fName, lName, score)) {
		to_lower_case(fName);
		to_lower_case(lName);
		
		if (fseek(fp, (record - 1) * 44, SEEK_SET) == 0) {
			fprintf(fp, "%-20s%-20s%-4d", fName, lName, score);
			
			return 1;
		}
	}
	
	return 0;
}

/* Modifies a record, called when the score position is an asterisk. First name
   and last name are also checked to see if they are asterisks, and if they are
   then their old values are retrieved. Then the old records are replaced with
   this new record. */
int modify_addast(int record, char fName[], char lName[], char ast, FILE *fp) {
	int score;
	
	if (strcmp("*", fName) == 0) {
		if (!get_old_name(record, fName, "%s %*s %*d", fp)) {
			return 0;
		}
	}
	
	if (strcmp("*", lName) == 0) {
		if (!get_old_name(record, lName, "%*s %s %*d", fp)) {
			return 0;
		}
	}
	
	if (ast == '*') {
		if (fseek(fp, (record - 1) * 44, SEEK_SET) == 0) {
			if (fscanf(fp, "%*s %*s %d", &score) != 1) {
				return 0;
			}
		}
	}
	
	if (validate_add(fName, lName, score)) {
		to_lower_case(fName);
		to_lower_case(lName);
			
		if (fseek(fp, (record - 1) * 44, SEEK_SET) == 0) {
			fprintf(fp, "%-20s%-20s%-4d", fName, lName, score);
			
			return 1;
		}
	}
	
	return 0;
}

/* Gets the old (current) value of a name in the records. Returns 1 if the old
   name is successfully retrieved, and 0 if it has failed. */
int get_old_name(int record, char name[], char pattern[], FILE *fp) {
	if (fseek(fp, (record - 1) * 44, SEEK_SET) == 0) {
		if (fscanf(fp, pattern, name) == 1) {
			return 1;
		}
	}
	
	return 0;
}