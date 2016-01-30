#include "ShapeFactory.h"

using namespace std;

Shape* ShapeFactory::create() {
	string type;
	
	if (!(*pis_ >> type ))
		return 0;
	
	if (type == "circle")
		return new Circle(*pis_);
	
	if (type == "triangle")
		return new Triangle(*pis_);
	
	if (type == "rectangle")
		return new Rectangle(*pis_);
		
	return 0;
}
