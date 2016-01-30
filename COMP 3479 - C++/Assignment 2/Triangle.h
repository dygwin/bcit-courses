#ifndef TRIANGLE_H
#define TRIANGLE_H
// headers, ...
#include "Point.h"
#include "Shape.h"

class Triangle: public Shape {
public:
  // read data for Triangle from 'is'; used by factory
  // should read in data members saved by 'save' (except for the type name)
  // should perform some error-checking (see README)
  explicit Triangle(std::istream& is); 

  // a Triangle consists of 3 vertices
  // 'i' should be 0, 1, or 2; if not, throw an exception
  Point getVertex(int i) const;

  // 'i' should be 0, 1, or 2; if not, throw an exception
  void  setVertex(int i, const Point& p); 

  // write to standard output
  // example output:  [T: (5,-6), (7,-8), (2,1)]
  virtual void draw() const; 

  /* example output:
       triangle
       (5,-6) (7,-8) (2,1)
  */
  virtual void save(std::ostream& os = std::cout) const; 

  // accept a visitor
  virtual void accept(ShapeVisitor& v);

private:
  // provide suitable data structure
  Point vertex0_;
  Point vertex1_;
  Point vertex2_;
};

// provide inline implementation of getVertex & setVertex here
// implement the other functions in the CPP file
inline Point Triangle::getVertex(int i) const {
	if (i != 0 && i != 1 && i != 2)
		throw "Point Triangle::getVertex(int) const:Invalid vertex";
	if (i == 0)
		return vertex0_;
	else if (i == 1)
		return vertex1_;
	else
		return vertex2_;
}

inline void Triangle::setVertex(int i, const Point& p) {
	if (i != 0 && i != 1 && i != 2)
		throw "void Triangle::setVertex(int, const Point&):Invalid vertex";
	if (i == 0)
		vertex0_ = p;
	else if (i == 1)
		vertex1_ = p;
	else
		vertex2_ = p;
}
#endif
