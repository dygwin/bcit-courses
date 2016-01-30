#include "Rectangle.h"
using namespace std;

  // read data for Rectangle from 'is'; used by factory
  // should read in data members saved by 'save' (except for the type name)
  // should perform some error-checking (see README)
Rectangle::Rectangle(std::istream& is) {
	char c1, c2, c3, c4, c5, c6;
	int p1, p2, p3, p4;
	
	if (is >> c1 >> p1 >> c2 >> p2 >> c3 >> c4 >> p3 >> c5 >> p4 >> c6
		&& c1 == '(' && c2 == ',' && c3 == ')'
		&& c4 == '(' && c5 == ',' && c6 == ')') {
		
		vertex0_ = Point(p1, p2);
		vertex1_ = Point(p3, p4);
	} else {
		throw "Rectangle::Rectangle(istream&): Invalid input";
	}
}  

  // write to standard output
  // example output:  [R: (2,-3), (4,-5)]  
void Rectangle::draw() const {
	cout << "[R: " << vertex0_ << ", " << vertex1_ << "]" << endl;
}

  /* example output:
       rectangle
       (2,-3) (4,-5)
  */
void Rectangle::save(std::ostream& os) const {
	os << "rectangle" << endl << vertex0_ << ' ' << vertex1_ << endl;
}
  
  // accept a visitor
void Rectangle::accept(ShapeVisitor& visitor) {
	visitor.visitRectangle(this);
}
