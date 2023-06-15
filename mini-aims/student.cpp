#include <iostream>
#include <fstream>
#include "student.h"


Student::Student(string name,string roll, string branch,string program):name(name),roll(roll),branch(branch),program(program) {}

void Student::registerCourse(fstream &stud)
{
    Course::floated_courses();
    cout<<"Choose the course from the above floated courses"<<endl;//case sensitive
    string course_code;
    getline(cin,course_code);
    fstream fl("DATA/Courses/floated_courses.txt",ios::in);
    bool counter=false;
    string temp;
    while(getline(fl,temp))
    {
        if(temp==course_code)
            counter=true;
        
    }
    if(!counter)
    {
        cout<<"This course does not exist"<<endl;
        return;
    }
    stud<<course_code<<'\n';
    fl.close();
    fl.open("DATA/Courses/"+course_code+".txt",ios::app);
    fl<<this->roll<<'\n';
    fl<<"Not_Yet_Graded"<<'\n';
    fl.close();
}

void Student::deregisterCourse(fstream &f)
{
    string course_code;
    cout<<"Enter the course code which you want to deregister"<<endl;
    getline(cin,course_code);
    getline(cin,course_code);
    bool counter = false;
    string temp;
    while(getline(f,temp))
    {
        if(temp==course_code)
            counter=true;
    }

    if(!counter)
    {
        cout<<"You have not enrolled for this course"<<endl;
        return;
    }

    fstream course("DATA/Courses/"+course_code+".txt",ios::in);
    while(getline(course,temp))
    {
        if(temp==this->roll)
        {
            getline(course,temp);
            if(temp!="Not_Yet_Graded")
            {
                cout<<"You have already been graded for this course and hence cannot deregister from this course"<<endl;
                return;
            }
        }
    }
    course.close();
    fstream f_temp("DATA/Students/temp.txt",ios::out);
    fstream fl("DATA/Students/"+this->roll+".txt",ios::in);
    while(getline(fl,temp))
    {
        if(temp!=course_code)
            {
                f_temp<<temp<<endl;
            }
    }
    cout<<"Successfully de-registered from "<<course_code<<endl;
    fl.close();
    f_temp.close();
    string file_name="DATA/Students/"+this->roll+".txt";
    f.close();

    remove(file_name.c_str());
    rename("DATA/Students/temp.txt",file_name.c_str());
    file_name="DATA/Courses/"+course_code+".txt";
    fl.open("DATA/Courses/"+course_code+".txt",ios::in);
    f_temp.open("DATA/Courses/temp.txt",ios::out);
    while(getline(fl,temp))
    {
        if(temp!=this->name)
            {
                f_temp<<temp<<endl;
            }
    }
    fl.close();
    f_temp.close();
    remove(file_name.c_str());
    rename("DATA/Courses/temp.txt",file_name.c_str());
    f_temp.open("DATA/Courses/temp.txt",ios::out);
    fl.open("DATA/Courses/"+course_code+".txt",ios::in);
    getline(fl,temp);
    f_temp<<temp+"\n";
    while(getline(fl,temp))
    {
        if(temp!=this->roll)
        {
            f_temp<<temp+"\n";
            getline(fl,temp);
            f_temp<<temp+"\n";
        }
        else
        {
            getline(fl,temp);
        }
    }
    fl.close();
    f_temp.close();
    file_name="DATA/Courses/"+course_code+".txt";
    remove(file_name.c_str());
    rename("DATA/Courses/temp.txt",file_name.c_str());
}

