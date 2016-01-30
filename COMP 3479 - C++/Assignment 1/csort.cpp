// csort.cpp
/* Trevor Broderick*/

#include <iostream>
#include <sstream>
#include <string>
#include <cmath>
#include <vector>
#include <algorithm>

using namespace std;

/* Use a struct for comparison in the stable_sort so that I can pass the
	command line arguments into it */
struct Cmp {
	// vector for storing the command line arguments for referencing later
    vector<pair<int, bool>> args;
	
    Cmp(vector<pair<int, bool>> arguments) : args(arguments) {}
	
	// overload the () operator for comparison in the stable_sort
    bool operator () (vector<string> v1, vector<string> v2) {
		// loop through all the arguments (as needed when they are equivalent)
		for (vector<pair<int, bool>>::size_type i = 0; i < args.size(); i++) {
			istringstream iss(v1[abs(args[i].first) - 1]);
			// if the argument is  specified to be double comparison
			if (!(args[i].second)) {
				double d1, d2;
				bool first_failed = false;
				
				// if we cant get the first double, set first_failed
				if (!(iss >> d1)) {
					first_failed = true;
				}
				
				iss.clear();
				iss.str(v2[abs(args[i].first) - 1]);
				// if we cant get the second double, return false
				if (!(iss >> d2)) {
					// if they both failed, continue (equiv)
					if (first_failed) {
						continue;
					}
					
					// if only the second one failed, return true
					return true;
				}
				
				// if the first one failed and the second didn't
				if (first_failed) {
					return false;
				}
				
				// compare and return true/false for descending or ascending
				if (d1 < d2) {
					return args[i].first > 0 ? true : false;
				} else if (d1 > d2) {
					return args[i].first > 0 ? false : true;
				} else {
					// if equivalent, continue the loop on the next argument
					continue;
				}
			} // if the argument is specified to be string comparison 
			  else {
				string s1, s2;
				bool first_failed = false;
				// if we can't read the first string, return false
				if (!(iss >> s1)) {
					first_failed = true;
				}
				iss.clear();
				iss.str(v2[abs(args[i].first) - 1]);
				// if we can't read the second string, return true
				if (!(iss >> s2)) {
					// if they both failed, continue (equiv)
					if (first_failed) {
						continue;
					}
					
					// if only the second one failed, return true
					return args[i].first > 0 ? false : true;
				}
				
				// if the first one failed and the second didn't
				if (first_failed) {
					return args[i].first > 0 ? true : false;
				}
				
				// compare and return true/false for descending or ascending
				if (s1 < s2) {
					return args[i].first > 0 ? true : false;
				} else if (s1 > s2) {
					return args[i].first > 0 ? false : true;
				} else {
					// if equivalent, continue the loop on the next argument
					continue;
				}
			}
		}
		// loop has ended and values have always been equivalent, return false
		return false;
	}
};

vector<string> split(const string& s, const string& delim);
void print_rows(const vector<vector<string>>& rows);

/* Validates the command line arguments, then gets the rows to sort from user
	input. The rows are then sorted based on the command line arguments,
	and printed back out to the user. */
int main(int argc, char* argv[]) {
	// the program must be called with only 1 argument
	if (argc != 2) {
			cerr << "Invalid Arguments. Correct Usage: 'csort {column numbers seperated by commas}'" << endl << "e.g. csort 1,2s,3";
		return 5;
	}
	
	// stores the arguments in a <column number, is string?> format
	vector<pair<int, bool>> args;
	// splits up the initial argument for validation
	vector<string> split_args = split(argv[1], ",");
	// max argument size (all input must be >= than this)
	size_t max_column = 0;
	
	/* Validate all the arguments and add them to the unordered_map.
		Exit the program with an error if any of the arguments are invalid. */
	for (auto arg : split_args) {
		istringstream iss(arg);
		int column;
		string str;
		
		if (iss >> column) {
			// if an argument was 0, exit
			if (abs(column) == 0) {
				cerr << "Invalid Arguments. Correct Usage: 'csort {column numbers seperated by commas}'" << endl << "e.g. csort 1,2s,3";
				return 1;
			}
			
			// if the argument is a duplicate, exit
			for (auto arg : args) {
				if (abs(arg.first) == abs(column)) {
				cerr << "Invalid Arguments. Correct Usage: 'csort {column numbers seperated by commas}'" << endl << "e.g. csort 1,2s,3";
					return 2;
				}
			}
			
			if (abs(column) > (int)max_column) {
				max_column = abs(column);
			}
			
			// if the argument contains an 's', add with the string value true
			if (iss >> str) {
				if (str == "s") {
					args.push_back(pair<int, bool>(column, true));
				} else {
				cerr << "Invalid Arguments. Correct Usage: 'csort {column numbers seperated by commas}'" << endl << "e.g. csort 1,2s,3";
					return 3;
				}
			} else {
				iss.clear();
				args.push_back(pair<int, bool>(column, false));
			}
		} else {
				cerr << "Invalid Arguments. Correct Usage: 'csort {column numbers seperated by commas}'" << endl << "e.g. csort 1,2s,3";
			return 4;
		}
	}
	
	// string used to store each line of input temporarily
	string line;
	// vector of vectors to store the user input
	vector<vector<string>> rows;
	
	// get all the input from the user and add it to the rows vector
	while (getline(cin, line)) {
		// split the row for easier comparison later, and store it in rows
		vector<string> row = split(line, ",");
		
		// ensures the input is >= the max column argument
		if (row.size() >= max_column) {
			rows.push_back(row);
		}
	}
	
	// run the stable_sort on the vector of user input
	stable_sort(rows.begin(), rows.end(), Cmp(args));
	
	// print the sorted rows back to the user
	print_rows(rows);
}

/* Splits up a string by the specified delimiter and returns the tokens. */
vector<string> split(const string& s, const string& delim) {
	vector<string> v;
	
	string::size_type start = 0;
	string::size_type pos = 0;
	
	while (1) {
		start = s.find_first_of(delim, pos);
		
		if (start == string::npos) {
			v.push_back(s.substr(pos));
			break;
		} else {
			v.push_back(s.substr(pos, start - pos));
		}
		
		pos = start + 1;
	}
	
	return v;
}

/* Prints each row (the vector of vectors) back to the user. */
void print_rows(const vector<vector<string>>& rows) {
	for (auto row : rows) {
		for (string::size_type i = 0; i < row.size(); i++) {
			if (i == row.size() - 1) {
				cout << row[i];
			} else {
				cout << row[i] << ",";
			}
		}
		cout << endl;
	}
}
