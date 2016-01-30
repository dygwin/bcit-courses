#include <stdio.h>

/* tr.c */
   
/* This program is used to replace or delete characters from input with
   a second given set of characters. There are three different modes to
   this program. The default mode uses the argument format of tr [set1] [set2]
   and will replace all of the characters in set1 with the character at the
   same index in set2. If set1 has more characters than set2, then the last
   character is set2 is repeated for the extra characters in set1. The second
   mode of this program is truncate, using the arguments tr -t [set1] [set2].
   This mode is the same as the default mode, but set2 is no longer extended if
   it is shorter than set1. Instead, set1 is truncated to match the size of set2.
   The final mode is delete, using the argument format of tr -d [set]. This will
   simply delete the characters in the set from the input string. */
   
void hyphen_replace(char letters[]);
void escape_replace(char letters[]);
void duplicate_char_remove(char letters[]);
void duplicate_char_remove_pair(char letters[], char replace[]);
void char_remove(char letters[], size_t pos);
void truncate(char oldc[], char newc[]);

void replace(const char oldc[], const char newc[]);
void delete(const char oldc[]);

/* The main access point where a method will be chosen based on the
   number and type of arguments passed into the command line.
   Valid arguments are [set1] [set2], -t [set1] [set2], and -d[set1] */
int main(int argc, char *argv[]) {
	/* If we have 2 arguments (./a abc def OR ./a -d abc),
	   then replace or delete */
	if (argc == 3) {
		/* If the first argument is -d then delete. Otherwise, replace */
		if (argv[1][0] == '-' && argv[1][1] == 'd') {
			/* Replace escape characters with their escape sequences */
			escape_replace(argv[2]);
			/* Replace hyphens with their character ranges */
			hyphen_replace(argv[2]);
			/* Remove duplicate characters */
			duplicate_char_remove(argv[2]);
			
			delete(argv[2]);
		} else if (argv[1][0] == '-' && argv[1][1] != 'd') {
			/* Show usage instructions when they execute the program incorrectly */
			fprintf(stderr, "usage: %s [set1] [set2]\n", argv[0]);
			fprintf(stderr, "usage: %s -t [set1] [set2]\n", argv[0]);
			fprintf(stderr, "usage: %s -d [set1]\n", argv[0]);
		} else {
			/* Replace escape characters with their escape sequences */
			escape_replace(argv[1]);
			escape_replace(argv[2]);
			/* Replace hyphens with their character ranges */
			hyphen_replace(argv[1]);
			hyphen_replace(argv[2]);
			/* Remove duplicate characters */
			duplicate_char_remove_pair(argv[1], argv[2]);
			duplicate_char_remove(argv[2]);
			
			replace(argv[1], argv[2]);
		}
	}
	
	/* If we have 3 arguments (./a -t abcdef xyz), execute replace_truncated */
	if (argc == 4) {
		if (argv[1][0] == '-' && argv[1][1] == 't') {
			/* Replace escape characters with their escape sequences */
			escape_replace(argv[2]);
			escape_replace(argv[3]);
			/* Replace hyphens with their character ranges */
			hyphen_replace(argv[2]);
			hyphen_replace(argv[3]);
			/* Truncate the first set */
			truncate(argv[2], argv[3]);
			/* Remove duplicate characters */
			duplicate_char_remove_pair(argv[2], argv[3]);
			duplicate_char_remove(argv[3]);
			
			replace(argv[2], argv[3]);
		} else {
			/* Show usage instructions when they execute the program incorrectly */
			fprintf(stderr, "usage: %s [set1] [set2]\n", argv[0]);
			fprintf(stderr, "usage: %s -t [set1] [set2]\n", argv[0]);
			fprintf(stderr, "usage: %s -d [set1]\n", argv[0]);
		}
	}
	/* Show usage instructions when they execute the program incorrectly */
	if (argc < 3) {
		fprintf(stderr, "usage: %s [set1] [set2]\n", argv[0]);
		fprintf(stderr, "usage: %s -t [set1] [set2]\n", argv[0]);
		fprintf(stderr, "usage: %s -d [set1]\n", argv[0]);
	}
	
	return 0;
}

/* Replaces the hyphens in a string with the character range */
void hyphen_replace(char letters[]) {
	size_t i;
	
	for (i = 0; letters[i] != '\0'; i++) {
		/* If the hyphen is not the first nor the last character */
		if (letters[i] == '-' && i != 0 && letters[i + 1] != '\0') {
			int    min_range = letters[i - 1];
			int    max_range = letters[i + 1];
			int    range     = max_range - min_range;
			size_t j;
			size_t array_size;
			/* Number of trailing characters */
			size_t trailing_chars;
			
			/* Get the array size */
			for (array_size = 0; letters[array_size] != '\0'; array_size++) {}
			
			trailing_chars = array_size - i;
			
			/* If the hyphen has a range of 0 (like a-a) */
			if (range == 0) {
				for (j = i; j < array_size; j++) {
					letters[j] = letters[j + 2];
				}
			} else {
				/* Pad the string with enough characters, end it with a null 
				   character */
				for (j = array_size; j < range + (array_size - i); j++) {
					letters[j] = ' ';
					
					if (j == range + (array_size - i) - 1) {
						letters[j] = '\0';
					}
				}
				
				/* Get the arrays new size */
				for (array_size = 0; letters[array_size] != '\0'; array_size++) {}
				
				/* Copy the letters after the hyphen to the end */
				for (j = i + trailing_chars - 1; j > i; j--) {
					letters[array_size - 1] = letters[j];
					array_size--;
				}

				/* Input the letters from the hyphen */
				for (j = i; min_range < max_range; min_range++) {
					letters[j] = min_range + 1;
					j++;
				}
				
				/* Increase the position to pass the end of the hyphen */
				i += range;
			}
		}
	}
}

/* Replaces all valid escape characters with their actual escape sequences */
void escape_replace(char letters[]) {
	size_t i;
	
	for (i = 0; letters[i] != '\0'; i++) {
		if (letters[i] == '\\') {
			size_t array_size;
			
			for (array_size = 0; letters[array_size] != '\0'; array_size++) {}
			
			/* Replace the \ character and shift the other characters left */
			if (letters[i + 1] == '\\') {
				letters[i] = '\\';
				char_remove(letters, i + 1);
			} else if (letters[i + 1] == 'a') {
				letters[i] = '\a';
				char_remove(letters, i + 1);
			} else if (letters[i + 1] == 'b') {
				letters[i] = '\b';
				char_remove(letters, i + 1);
			} else if (letters[i + 1] == 'f') {
				letters[i] = '\f';
				char_remove(letters, i + 1);
			} else if (letters[i + 1] == 'n') {
				letters[i] = '\n';
				char_remove(letters, i + 1);
			} else if (letters[i + 1] == 'r') {
				letters[i] = '\r';
				char_remove(letters, i + 1);
			} else if (letters[i + 1] == 't') {
				letters[i] = '\t';
				char_remove(letters, i + 1);
			} else if (letters[i + 1] == 'v') {
				letters[i] = '\v';
				char_remove(letters, i + 1);
			} else if (letters[i + 1] == '\'') {
				letters[i] = '\'';
				char_remove(letters, i + 1);
			} else if (letters[i + 1] == '\"') {
				letters[i] = '\"';
				char_remove(letters, i + 1);
			}
		}
	}
}

/* Checks the character array for duplicate letters and uses char_remove
   to remove them */
void duplicate_char_remove(char letters[]) {
	size_t i;
	
	for (i = 0; letters[i] != '\0'; i++) {
		size_t j;
		
		for (j = i + 1; letters[j] != '\0'; j++) {
			if (letters[i] == letters[j]) {
				char_remove(letters, i);
				i = 0;
				j = 0;
			}
		}
	}
}

/* Checks the character array for duplicate letters and uses char_remove
   to remove them. It removes the positions from both arrays at the same time */
void duplicate_char_remove_pair(char letters[], char replace[]) {
	size_t i;
	size_t letters_size;
	size_t replace_size;
	
	/* Find the sizes of both arrays */
	for (letters_size = 0; letters[letters_size] != '\0'; letters_size++) {};
	for (replace_size = 0; replace[replace_size] != '\0'; replace_size++) {};
	
	/* Extend the size of replace if it's shorter than letters */
	if (replace_size < letters_size) {
		for (i = replace_size - 1; i < letters_size; i++) {
			replace[i] = replace[replace_size - 1];
		}
		
		replace[i] = '\0';
	}
	
	for (i = 0; letters[i] != '\0'; i++) {
		size_t j;
		
		for (j = i + 1; letters[j] != '\0'; j++) {
			if (letters[i] == letters[j]) {
				char_remove(letters, i);
				char_remove(replace, i);
				i = 0;
				j = 0;
			}
		}
	}
}

/* Removes the first duplicate character then shifts the characters left */
void char_remove(char letters[], size_t pos) {
	size_t i;
	size_t array_size;
	
	/* Find the size of the array */
	for (array_size = 0; letters[array_size] != '\0'; array_size++) {}
	
	/* Remove the char and shift the other chars left */
	for (i = pos; i < array_size; i++) {
		letters[i] = letters[i + 1];
	}	
}

/* Replaces the characters in oldc[] with the corresponding characters in
   newc[] that are at the same index in the arrays */
void replace(const char oldc[], const char newc[]) {
	/* The current character to check and possibly replace */
	int c;
	/* Contains the size of newc[] */
	size_t newc_size;
	
	/* Counts the size of the newc[] array and stores it in newc_size */
	for(newc_size = 0; newc[newc_size + 1] != '\0'; newc_size++) {}
	
	while((c = getchar()) != EOF) {
		size_t i;
		/* Whether we have replaced a character */
		int replaced = 0;
		
		/* Iterate through each character in oldc[] and check for a match */
		for(i = 0; oldc[i] != '\0'; i++) {
			if (c == oldc[i]) {
				/* Use newc[]'s last character if we exceed its size */
				putchar(newc[i > newc_size ? newc_size : i]);
				/* Change replaced to true */
				replaced = 1;
			}
		}
		
		/* If the character wasn't replaced, put the original character */
		if (!replaced)
			putchar(c);
	}
}

/* Truncates the size of the oldc[] array to the size of the newc[] array */
void truncate(char oldc[], char newc[]) {
	/* Contains the size of newc[] */
	size_t newc_size;
	
	/* Counts the size of the newc[] array and stores it in newc_size */
	for(newc_size = 0; newc[newc_size] != '\0'; newc_size++) {}
	
	/* Truncate */
	oldc[newc_size] = '\0';
}

/* Deletes the characters from the input that are in the oldc[] array */
void delete(const char oldc[]) {
	/* The current character to check and possibly replace */
	int c;
	
	while((c = getchar()) != EOF) {
		size_t i;
		/* Whether we have deleted the current char */
		int deleted = 0;
		
		/* Iterate through each character in oldc[] and check for a match */
		for(i = 0; oldc[i] != '\0'; i++) {
			if (c == oldc[i]) {
				/* Set deleted to true */
				deleted = 1;
			}
		}
		
		/* If the character wasn't deleted, put it */
		if(!deleted)
		  putchar(c);
	}
}