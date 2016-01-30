#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#define NAMESIZE 20
#include "record.h"

/* sort.c */
   
/* Provides the methods used for sorting record_lists using a variety
   of different sorting techniques, and displays the output to stdout */

void display(record_list *list);
void sort(const char line[], record_list *list);

int cmp1(const void *p, const void *q);
int cmp2(const void *p, const void *q);
int cmp3(const void *p, const void *q);
int cmp4(const void *p, const void *q);
int cmp5(const void *p, const void *q);
int cmp6(const void *p, const void *q);
int cmp7(const void *p, const void *q);
int cmp8(const void *p, const void *q);
int cmp9(const void *p, const void *q);
int cmp10(const void *p, const void *q);
int cmp11(const void *p, const void *q);
int cmp12(const void *p, const void *q);

/* Sorts the record_list and displays it. Uses the switches +n, -n,
   +s, and -s for different sorting methods */
void sort(const char line[], record_list *list) {
	char s1[2];
	char s2[2];
	
	/* if there are 2 switches */
	if (sscanf(line, "%*s %s %s", s1, s2) == 2) {
		if (strcmp(s1, "+n") == 0 && strcmp(s2, "+s") == 0) {
			qsort(list->data, list->nused, sizeof(record), cmp5);
			display(list);
		} else if (strcmp(s1, "+n") == 0 && strcmp(s2, "-s") == 0) {
			qsort(list->data, list->nused, sizeof(record), cmp6);
			display(list);
		} else if (strcmp(s1, "-n") == 0 && strcmp(s2, "+s") == 0) {
			qsort(list->data, list->nused, sizeof(record), cmp7);
			display(list);
		} else if (strcmp(s1, "-n") == 0 && strcmp(s2, "-s") == 0) {
			qsort(list->data, list->nused, sizeof(record), cmp8);
			display(list);
		} else if (strcmp(s1, "+s") == 0 && strcmp(s2, "+n") == 0) {
			qsort(list->data, list->nused, sizeof(record), cmp9);
			display(list);
		} else if (strcmp(s1, "+s") == 0 && strcmp(s2, "-n") == 0) {
			qsort(list->data, list->nused, sizeof(record), cmp10);
			display(list);
		} else if (strcmp(s1, "-s") == 0 && strcmp(s2, "+n") == 0) {
			qsort(list->data, list->nused, sizeof(record), cmp11);
			display(list);
		} else if (strcmp(s1, "-s") == 0 && strcmp(s2, "-n") == 0) {
			qsort(list->data, list->nused, sizeof(record), cmp12);
			display(list);
		} else if (strcmp(s1, "+n") == 0) {
			qsort(list->data, list->nused, sizeof(record), cmp1);
			display(list);
		} else if (strcmp(s1, "-n") == 0) {
			qsort(list->data, list->nused, sizeof(record), cmp2);
			display(list);
		} else if (strcmp(s1, "+s") == 0) {
			qsort(list->data, list->nused, sizeof(record), cmp3);
			display(list);
		} else if (strcmp(s1, "-s") == 0) {
			qsort(list->data, list->nused, sizeof(record), cmp4);
			display(list);
		} else {
			display(list);
		}
	} else if (sscanf(line, "%*s %s", s1) == 1) {
		/* if there is 1 switch */  
		if (strcmp(s1, "+n") == 0) {
			qsort(list->data, list->nused, sizeof(record), cmp1);
			display(list);
		} else if (strcmp(s1, "-n") == 0) {
			qsort(list->data, list->nused, sizeof(record), cmp2);
			display(list);
		} else if (strcmp(s1, "+s") == 0) {
			qsort(list->data, list->nused, sizeof(record), cmp3);
			display(list);
		} else if (strcmp(s1, "-s") == 0) {
			qsort(list->data, list->nused, sizeof(record), cmp4);
			display(list);
		} else {
			display(list);
		}
	} else {
		/* if there are no switches */
		display(list);
	}
}

/* Displays the records in the given record_list in the format
   lastname firstname score*/
void display(record_list *list) {
	size_t i;
	
	for (i = 0; i < list->nused; i++) {
		printf("%s %s %d\n", list->data[i].name.last, list->data[i].name.first, list->data[i].score);
	}
	
	printf("%s", "OK\n");
}

/* Compare function used for +n switch */
int cmp1(const void *rec1, const void *rec2) {
	const record *r1 = rec1;
	const record *r2 = rec2;
	
	if (strcmp(r1->name.last, r2->name.last) == 0) {
		return strcmp(r1->name.first, r2->name.first);
	}
	
	return strcmp(r1->name.last, r2->name.last);
}

/* Compare function used for -n switch */
int cmp2(const void *rec1, const void *rec2) {
	const record *r1 = rec1;
	const record *r2 = rec2;
	
	if (strcmp(r1->name.last, r2->name.last) == 0) {
		return -strcmp(r1->name.first, r2->name.first);
	}
	
	return -strcmp(r1->name.last, r2->name.last);
}

/* Compare function used for +s switch */
int cmp3(const void *rec1, const void *rec2) {
	const record *r1 = rec1;
	const record *r2 = rec2;
	
	return r1->score - r2->score;
}

/* Compare function used for -s switch */
int cmp4(const void *rec1, const void *rec2) {
	const record *r1 = rec1;
	const record *r2 = rec2;
	
	return r2->score - r1->score;
}

/* Compare function used for +n +s switch */
int cmp5(const void *rec1, const void *rec2) {
	const record *r1 = rec1;
	const record *r2 = rec2;
	
	if (strcmp(r1->name.last, r2->name.last) == 0) {
		if (strcmp(r1->name.first, r2->name.first) == 0) {
			return r1->score - r2->score;
		}
		
		return strcmp(r1->name.first, r2->name.first);
	}
	
	return strcmp(r1->name.last, r2->name.last);
}

/* Compare function used for +n -s switch */
int cmp6(const void *rec1, const void *rec2) {
	const record *r1 = rec1;
	const record *r2 = rec2;
	
	if (strcmp(r1->name.last, r2->name.last) == 0) {
		if (strcmp(r1->name.first, r2->name.first) == 0) {
			return r2->score - r1->score;
		}
		
		return strcmp(r1->name.first, r2->name.first);
	}
	
	return strcmp(r1->name.last, r2->name.last);
}

/* Compare function used for -n +s switch */
int cmp7(const void *rec1, const void *rec2) {
	const record *r1 = rec1;
	const record *r2 = rec2;
	
	if (strcmp(r1->name.last, r2->name.last) == 0) {
		if (strcmp(r1->name.first, r2->name.first) == 0) {
			return r1->score - r2->score;
		}
		
		return -strcmp(r1->name.first, r2->name.first);
	}
	
	return -strcmp(r1->name.last, r2->name.last);
}

/* Compare function used for -n -s switch */
int cmp8(const void *rec1, const void *rec2) {
	const record *r1 = rec1;
	const record *r2 = rec2;
	
	if (strcmp(r1->name.last, r2->name.last) == 0) {
		if (strcmp(r1->name.first, r2->name.first) == 0) {
			return r2->score - r1->score;
		}
		
		return -strcmp(r1->name.first, r2->name.first);
	}
	
	return -strcmp(r1->name.last, r2->name.last);
}

/* Compare function used for +s +n switch */
int cmp9(const void *rec1, const void *rec2) {
	const record *r1 = rec1;
	const record *r2 = rec2;
	
	if (r1->score - r2->score == 0) {
		if (strcmp(r1->name.last, r2->name.last) == 0) {
			return strcmp(r1->name.first, r2->name.first);
		}
		
		return strcmp(r1->name.last, r2->name.last);
	}
	
	return r1->score - r2->score;
}

/* Compare function used for +s -n switch */
int cmp10(const void *rec1, const void *rec2) {
	const record *r1 = rec1;
	const record *r2 = rec2;
	
	if (r1->score - r2->score == 0) {
		if (strcmp(r1->name.last, r2->name.last) == 0) {
			return -strcmp(r1->name.first, r2->name.first);
		}
		
		return -strcmp(r1->name.last, r2->name.last);
	}
	
	return r1->score - r2->score;
}

/* Compare function used for -s +n switch */
int cmp11(const void *rec1, const void *rec2) {
	const record *r1 = rec1;
	const record *r2 = rec2;
	
	if (r2->score - r1->score == 0) {
		if (strcmp(r1->name.last, r2->name.last) == 0) {
			return strcmp(r1->name.first, r2->name.first);
		}
		
		return strcmp(r1->name.last, r2->name.last);
	}
	
	return r2->score - r1->score;
}

/* Compare function used for -s -n switch */
int cmp12(const void *rec1, const void *rec2) {
	const record *r1 = rec1;
	const record *r2 = rec2;
	
	if (r2->score - r1->score == 0) {
		if (strcmp(r1->name.last, r2->name.last) == 0) {
			return -strcmp(r1->name.first, r2->name.first);
		}
		
		return -strcmp(r1->name.last, r2->name.last);
	}
	
	return r2->score - r1->score;
}