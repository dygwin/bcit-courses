#include <iostream>
#include <string>
#include <fstream>
#include <vector>
#include <limits>
#include "ShapeFactory.h"
#include "Circle.h"
#include "Rectangle.h"
#include "Triangle.h"
#include "TranslationVisitor.h"
#include "XReflectionVisitor.h"
#include "YReflectionVisitor.h"

using namespace std;

void read_shapes(char* filename, vector<Shape*>& v);
void get_input(vector<Shape*>& v);
void write_shapes(char* filename, vector<Shape*>& v);

int main(int argv, char* argc[]) {
	// ensure the program was called correctly
	if (argv != 2) {
		cerr << "Proper usage: " << argc[0] << " [filename]";
		return 1;
	}
	
	// vector to store the shapes read in from the file
	vector<Shape*> v;
	
	// attempt to read the shapes in the from file
	read_shapes(argc[1], v);
	
	// read input from cin and carry out the commands
	get_input(v);
	
	// write the shapes to the file, overwriting previous data
	write_shapes(argc[1], v);
	
}

// read shapes from an ifstream, adding them to the vector, then draw them
void read_shapes(char* filename, vector<Shape*>& v) {
	// create a ifstream on the filename
	ifstream is(filename);
	
	// if we can open the file, read the shapes it contains
	if (is) {
		// create a ShapeFactory to handle the input of shapes
		ShapeFactory f(is);
			
		// as long as we can read shapes from the file, add them to the vector
		try {
			Shape* s;
			while ((s = f.create()) != '\0') {
				v.push_back(s);
			}
		} catch (const char* s) {
			cerr << s << endl;
		}
			
		// draws all the shapes contained in the opened file
		for (auto s : v)
			s->draw();
	}
}

// reads commands from cin and executes the commands (c, d, x, y, or t)
void get_input(vector<Shape*>& v) {
	// create a ShapeFactory to handle the input of shapes
	ShapeFactory sf(cin);
	
	// prompt for user input
	cerr << '>';
	
	string command;
	// as long as we can read a command, carry out the command
	while (cin >> command) {
		if (command == "c") {
			// if the command is c, create a shape using the ShapeFactory
			Shape* s;
			try {
				if ((s = sf.create()) != '\0')
					v.push_back(s);
			} catch (const char* s) {}
			
			for (auto s : v)
				s->draw();
				
			// ignore the rest of the line
			cin.ignore( numeric_limits<streamsize>::max() , '\n');
		} else if (command == "d") {
			// if the command is d, draw all the shapes in the vector
			
			for (auto s : v)
				s->draw();
			
			// ignore the rest of the line
			cin.ignore( numeric_limits<streamsize>::max() , '\n');
		} else if (command == "x") {
			// if the command is x, use XReflectionVisitor on all the shapes
			XReflectionVisitor x;
			
			for (auto s : v) {
				s->accept(x);
				s->draw();
			}
				
			// ignore the rest of the line
			cin.ignore( numeric_limits<streamsize>::max() , '\n');
		} else if (command == "y") {
			// if the command is y, use YReflectionVisitor on all the shapes
			YReflectionVisitor y;
			
			for (auto s : v) {
				s->accept(y);
				s->draw();
			}
				
			// ignore the rest of the line
			cin.ignore( numeric_limits<streamsize>::max() , '\n');
		} else if (command == "t") {
			// if the command is t, use TranslationVisitor on all the shapes
			Point p;
			
			if (cin >> p) {
				TranslationVisitor t(p);
				for (auto s : v) {
					s->accept(t);
					s->draw();
				}
			}
			
			// ignore the rest of the line
			cin.ignore( numeric_limits<streamsize>::max() , '\n');
		}
		// prompt for more input
		cerr << '>';
	}
}

void write_shapes(char* filename, vector<Shape*>& v) {
	ofstream os(filename, ios_base::trunc);
	
	for (auto s : v)
		s->save(os);
}