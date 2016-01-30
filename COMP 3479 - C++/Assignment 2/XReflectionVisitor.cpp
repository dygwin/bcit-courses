#include "XReflectionVisitor.h"

void XReflectionVisitor::visitCircle(Circle *s) {
	// get vertexes
	Point p = s->getCentre();
	
	// negate the Y coordinate of each vertex
	p.setY(p.getY() * -1);
	
	// set the vertexes to the negates points
	s->setCentre(p);
}

void XReflectionVisitor::visitRectangle(Rectangle *s) {
	// get vertexes
	Point p0 = s->getVertex(0);
	Point p1 = s->getVertex(1);
	
	// negate the Y coordinate of each vertex
	p0.setY(p0.getY() * -1);
	p1.setY(p1.getY() * -1);
	
	// set the vertexes to the negates points
	s->setVertex(0, p0);
	s->setVertex(1, p1);
}

void XReflectionVisitor::visitTriangle(Triangle *s) {
	// get vertexes
	Point p0 = s->getVertex(0);
	Point p1 = s->getVertex(1);
	Point p2 = s->getVertex(2);
	
	// negate the Y coordinate of each vertex
	p0.setY(p0.getY() * -1);
	p1.setY(p1.getY() * -1);
	p2.setY(p2.getY() * -1);
	
	// set the vertexes to the negates points
	s->setVertex(0, p0);
	s->setVertex(1, p1);
	s->setVertex(2, p2);
}
