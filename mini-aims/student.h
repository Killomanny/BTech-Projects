#include <vector>
#include <string>

using namespace std;

#ifndef STU_DEF
#define STU_DEF
class Student
{
    public:
    string name;
    string roll;
    string branch;
    string program;
    
    Student(string,string,string,string);
    void registerCourse(fstream &stud);
    void deregisterCourse(fstream &stud);
};
#endif