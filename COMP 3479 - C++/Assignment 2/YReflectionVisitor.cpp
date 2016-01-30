#include "YReflectionVisitor.h"

void YReflectionVisitor::visitCircle(Circle *s) {
	// get vertexes
	Point p = s->getCentre();
	
	// negate the X coordinate of each vertex
	p.setX(p.getX() * -1);
	
	// set the vertexes to the negates points
	s->setCentre(p);
}

void YReflectionVisitor::visitRectangle(Rectangle *s) {
	// get vertexes
	Point p0 = s->getVertex(0);
	Point p1 = s->getVertex(1);
	
	// negate the X coordinate of each vertex
	p0.setX(p0.getX() * -1);
	p1.setX(p1.getX() * -1);
	
	// set the vertexes to the negates points
	s->setVertex(0, p0);
	s->setVertex(1, p1);
}

void YReflectionVisitor::visitTriangle(Triangle *s) {
	// get vertexes
	Point p0 = s->getVertex(0);
	Point p1 = s->getVertex(1);
	Point p2 = s->getVertex(2);
	
	// negate the X coordinate of each vertex
	p0.setX(p0.getX() * -1);
	p1.setX(p1.getX() * -1);
	p2.setX(p2.getX() * -1);
	
	// set the vertexes to the negates points
	s->setVertex(0, p0);
	s->setVertex(1, p1);
	s->setVertex(2, p2);
}
