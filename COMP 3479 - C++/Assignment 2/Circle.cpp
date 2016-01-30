#include "Circle.h"
using namespace std;

  // read data for circle from 'is'; used by ShapeFactory objects
  // should read in data members saved by 'save' (except for the type name)
  // should perform some error-checking (see README)
Circle::Circle(istream& is) {
	char c1, c2, c3;
	int p1, p2, radius;
	
	if (is >> c1 >> p1 >> c2 >> p2 >> c3 >> radius
		&& c1 == '(' && c2 == ',' && c3 == ')') {
		
		centre_.setX(p1);
		centre_.setY(p2);
		
		radius_ = radius;
	} else {
		throw "Circle::Circle(istream&): Invalid input";
	}
}

  // write to standard output
  // example output:  [C: (1,-2), 3]
void Circle::draw() const {
	cout << "[C: " << centre_ << ", " << radius_ << "]" << endl;
}

  /* example output:
      circle
      (1,-2) 3
  */
void Circle::save(ostream& os) const {
	os << "circle" << endl << centre_ << " " << radius_ << endl;
}

  // accept a visitor
void  Circle::accept(ShapeVisitor& v) {
	v.visitCircle(this);
}
