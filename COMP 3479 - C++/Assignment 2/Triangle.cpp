#include "Triangle.h"
using namespace std;

  // read data for Triangle from 'is'; used by factory
  // should read in data members saved by 'save' (except for the type name)
  // should perform some error-checking (see README)
Triangle::Triangle(std::istream& is) {
	char c1, c2, c3, c4, c5, c6, c7 ,c8 ,c9;
	int p1, p2, p3, p4, p5, p6;
	
	if (is >> c1 >> p1 >> c2 >> p2 >> c3 >> c4 >> p3 >> c5 >> p4 >> c6
	       >> c7 >> p5 >> c8 >> p6 >> c9
		&& c1 == '(' && c2 == ',' && c3 == ')'
		&& c4 == '(' && c5 == ',' && c6 == ')'
		&& c7 == '(' && c8 == ',' && c9 == ')') {
		
		vertex0_ = Point(p1, p2);
		vertex1_ = Point(p3, p4);
		vertex2_ = Point(p5, p6);
	} else {
		throw "Triangle::Triangle(std::istream&): Invalid input";
	}
}

  // write to standard output
  // example output:  [T: (5,-6), (7,-8), (2,1)]
void Triangle::draw() const {
	cout << "[T: " << vertex0_ << ", " << vertex1_
		 << ", " << vertex2_ << "]" << endl;
}

  /* example output:
       triangle
       (5,-6) (7,-8) (2,1)
  */
void Triangle::save(std::ostream& os) const {
	os << "triangle" << endl << vertex0_ << ' '
	   << vertex1_ << ' ' << vertex2_ << endl;
} 

  // accept a visitor
void Triangle::accept(ShapeVisitor& v) {
	v.visitTriangle(this);
}
