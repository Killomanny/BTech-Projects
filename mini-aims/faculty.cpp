#include <iostream>
#include <fstream>
#include "faculty.h"

using namespace std;

Faculty::Faculty(string s):name(s) {}

void Faculty::lookStudents(string course)
{
    
    fstream f("DATA/Courses/"+course+".txt",ios::in);
    string temp;
    getline(f,temp);
    if(temp!=this->name)
    {
        cout<<"You are not an instructor to this course therefore you cannot view the list of students"<<endl;
        return;
    }
    while(getline(f,temp))
    {
        cout<<temp<<endl;
        getline(f,temp);
    }
}

void Faculty::give_grade(string stud,string cour)
{
    bool is_student=false;
    fstream f("DATA/Courses/"+cour+".txt",ios::in);
    fstream f_temp("DATA/Courses/temp.txt",ios::out);
    string temp;
    string grade;
    bool change=false;
    getline(f,temp);
    f_temp<<temp+'\n';
    while(getline(f,temp))
    {
        f_temp<<temp<<'\n';
        if(temp==stud)
        {
            is_student=true;
        }
        if(is_student==true&&change==false)
        {
            cout<<"What grade do you want to give"<<endl;
            change=true;
            cin>>grade;
            getline(f,temp);
            f_temp<<grade<<'\n';
        }
    }
    if(is_student==false)
        cout<<stud+" has not enrolled for the course "+cour<<endl;
    f_temp.close();
    f.close();
    string file_name="DATA/Courses/"+cour+".txt";
    //cout<<file_name<<endl;
    remove(file_name.c_str());
    rename("DATA/Courses/temp.txt",file_name.c_str());

}

void Faculty::give_grade(string cour)
{   
    fstream floated("DATA/Courses/floated_courses.txt",ios::in);
    bool counter = false;
    string temp;
    while(getline(floated,temp))
    {
        if(temp==cour)
        {
            counter=true;
            break;
        }
    }
    if(counter==false)
    {
        cout<<cour<<" is not an active course"<<endl;
        return;
    }
    floated.close();
    fstream f("DATA/Courses/"+cour+".txt",ios::in);
    fstream f_temp("DATA/Courses/temp.txt",ios::out);
    
    getline(f,temp);
    if(temp!=this->name)
    {
        cout<<this->name<<" is not a faculty to "<<cour<<endl;
        return;
    }
    f_temp<<temp+'\n';
    while(getline(f,temp))
    {
        f_temp<<temp+'\n';
        cout<<"What grade do you want to give to "+temp<<endl;
        string grade;
        cin>>grade;
        
        f_temp<<grade+'\n';
        getline(f,temp);
    }
    f_temp.close();
    f.close();
    string file_name="DATA/Courses/"+cour+".txt";
    remove(file_name.c_str());
    rename("DATA/Courses/temp.txt",file_name.c_str());
}

