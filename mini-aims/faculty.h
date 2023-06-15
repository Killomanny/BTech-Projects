#include <string>
#include <vector>
#include "student.h"

using namespace std;
#ifndef FAC_DEF
#define FAC_DEF
class Faculty
{
 public:
 string name;

Faculty(string);
 void lookStudents(string cour);
 void give_grade(string stud,string cour);
 void give_grade(string cour);
};
#endif