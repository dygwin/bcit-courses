/* record.h */
#ifndef RECORD_H
#define RECORD_H
typedef struct { 
	char last[NAMESIZE];
	char first[NAMESIZE];
}	name;
typedef struct { 
	name name; 
	int score; 
}   record;
typedef struct { 
	record *data;
	size_t nalloc;
	size_t nused;
}   record_list;
#endif
