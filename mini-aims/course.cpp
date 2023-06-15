#include <iostream>
#include <fstream>
#include "course.h"

Course::Course(string course_id,string course_name,vector<string> course_instructors):course_id(course_id),course_name(course_name),course_instructor(course_instructor) {}

string Course::get_grade(string stud,string course)
{
    fstream f("DATA/Courses/"+course+".txt",ios::in);
    bool counter =false;
    string temp;
    while(getline(f,temp))
    {
        if(temp==stud)
        counter=true;
        if(counter==true)
        {   
            getline(f,temp);
            f.close();
            return temp;
        }
    }
    f.close();
    cout<<stud<<" has not enrolled for the course "+course<<endl;
    return "Not_Enrolled";

}

void Course::floated_courses()
{
    fstream floated("DATA/Courses/floated_courses.txt",ios::in);
    string course;
    cout<<"Course code\tCourse Name\tCourse Instructor"<<endl;
    while(getline(floated,course))
    {
        string course_name;
        string course_instructor;
        getline(floated,course_name);
        getline(floated,course_instructor);
        cout<<course<<"\t\t"<<course_name<<"\t\t"<<course_instructor+'\n'<<endl;
    }
    floated.close();
}