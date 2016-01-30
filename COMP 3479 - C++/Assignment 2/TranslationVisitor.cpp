#include "TranslationVisitor.h"

void TranslationVisitor::visitCircle(Circle *s) {
	s->setCentre(s->getCentre() + d_);
}

void TranslationVisitor::visitRectangle(Rectangle *s) {
	s->setVertex(0, s->getVertex(0) + d_);
	s->setVertex(1, s->getVertex(1) + d_);
}

void TranslationVisitor::visitTriangle(Triangle *s) {
	s->setVertex(0, s->getVertex(0) + d_);
	s->setVertex(1, s->getVertex(1) + d_);
	s->setVertex(2, s->getVertex(2) + d_);
}
