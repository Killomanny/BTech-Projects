#include <string>
#include <vector>

using namespace std;

#ifndef COU_DEF
#define COU_DEF
class Course
{
    public:
    string course_id;
    string course_name;
    vector<string> course_instructor;
    static void floated_courses();
    static string get_grade(string stud,string course);
    Course(string,string,vector<string>);
};
#endif