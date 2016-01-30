#ifndef SHAPEFACTORY_H
#define SHAPEFACTORY_H
#include <string>
#include "Shape.h"
#include "Circle.h"
#include "Rectangle.h"
#include "Triangle.h"

class ShapeFactory {
public:
  explicit ShapeFactory(std::istream& is): pis_(&is) {}

  // refer to lecture; see writeup for brief description
  Shape* create();

private:
  std::istream  *pis_;
};
#endif
