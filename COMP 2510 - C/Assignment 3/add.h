/* add.h */
#ifndef ADD_H
#define ADD_H
void to_lower_case(char line[]);
void add(const char line[], record_list *list);
void list_init(record_list *list);
void list_destroy(record_list *list);

int validate_add(const char firstName[], const char lastName[], int score);
int hyphen_check(const char name[]);
#endif
