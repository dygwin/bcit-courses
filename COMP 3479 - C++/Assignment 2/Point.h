#ifndef POINT_H
#define POINT_H
#include <iostream>

class Point {
public:
  explicit Point(int x = 0, int y = 0): x_(x), y_(y) {}

  // note that Point(std::istream&) is not really needed although you
  // may implement one if you want

  int  getX() const { return x_; }
  int  getY() const { return y_; }
  void setX(int x) { x_ = x; }
  void setY(int y) { y_ = y; }
 
  void save(std::ostream& os = std::cout) const {
    os << '(' << x_ << ',' << y_ << ')' << std::endl;
  }

  // called by operator+ (see below)
  Point& operator+=(const Point& rhs) {
   	x_ += rhs.x_;
	y_ += rhs.y_;
	return *this;
  }

  friend Point operator*(int n, const Point& p);
  friend std::istream& operator>>(std::istream&, Point&); 
  friend std::ostream& operator<<(std::ostream&, const Point&); 
private:
  int x_, y_;
};

// addition of Points, e.g. (1,2) + (3,4) should give (4,6)
inline Point  
operator+(const Point& lhs, const Point& rhs) {
  Point tmp(lhs);
  return tmp += rhs;
}

// multiply a point by an int, e.g. 3 * (1,2) should give (3,6)
inline Point
operator*(int n, const Point& p) {
  return Point(n * p.x_, n * p.y_);
}

// should read back what's written by operator<< as well as by the save method
// some error-checking should be performed (see README)
inline std::istream&
operator>>(std::istream& is, Point& pt) {
  char c1, c2, c3;
  int p1, p2;
  
  if (is >> c1 >> p1 >> c2 >> p2 >> c3
	   && c1 == '(' && c2 == ',' && c3 == ')') {
	pt.x_ = p1;
	pt.y_ = p2;
  } else {
	is.setstate(std::ios_base::failbit);
  }
  
  return is;
}

inline std::ostream& 
operator<<(std::ostream& os, const Point& pt) {
  os << '(' << pt.x_ << ',' << pt.y_ << ')';
  return os;
} 
#endif
