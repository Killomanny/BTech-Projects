#include "student.h"
#include "faculty.h"

using namespace std;
#ifndef AD_DEF
#define AD_DEF
class Admin
{
    public:
    string name;
    fstream& addStud(fstream &f, Student &stud);
    bool isFaculty(string look); 
    void float_course();
    void delStud(fstream &f,Student stud);
    void delStud(string course,string stud);
    fstream& addFaculty(fstream &f, Faculty &fac);
    void addAdmin();
    void delAdmin();
    void delFaculty(fstream &f,Faculty fac);
    Admin(string &s);
    void delCourse();
};
#endif