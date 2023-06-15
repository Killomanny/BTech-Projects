#include <iostream>
#include <fstream>
#include "admin.h"
#include "faculty.h"
#include "student.h"
#include "course.h"

using namespace std;

void input_handler(Admin ad)
{
    int n;
    cout<<"Below is a menu to perform various actions as a admin"<<endl;
    
    
    bool cont=true;
    while(cont)
    {
         cout<<"Press 1 to add another Admin\nPress 2 to delete the admin\nPress 3 to add a student to IITH\nPress 4 to add a faculty to IITH\nPress 5 to float a course\nPress 6 to remove a course\nPress 7 to remove a student from IITH\nPress 8 to remove a faculty from IITH\nPress 9 to remove a stundent from a floated course"<<endl;
        cin>>n;
    switch(n)
    {
        case 1:
                ad.addAdmin();
                break;
        case 2:
                {
                    cout<<"Are you sure you want to delete your id from IITH (Y/N)";
                    char c;
                    cin>>c;
                    if(c=='Y'||c=='y')
                    {
                        ad.delAdmin();
                        break;
                    }
                    else
                    cont=false;
                    break;
                }
        case 4:
                {
                    cout<<"Enter the name of the faculty to be added"<<endl;
                    string fac_name;
                    getline(cin,fac_name);
                    getline(cin,fac_name);
                    if(ad.isFaculty(fac_name))
                    {
                        cout<<fac_name+" is already a faculty in IITH"<<endl;
                        break;
                    }
                    Faculty fc(fac_name);
                    fstream f("DATA/Faculty/faculty.txt",ios::app);
                    ad.addFaculty(f,fc);
                    break;
                }
        case 3:
                {
                string s_roll;
                string s_name;
                string s_branch;
                string s_program;
                string temp;
                bool counter= false;
                cout<<"Enter the roll number of the student to be added to IITH"<<endl;
                getline(cin,s_roll);
                getline(cin,s_roll);
                fstream f("DATA/Students/students.txt",ios::in);
                while(getline(f,temp))
                {
                    if(temp==s_roll)
                    {
                        cout<<s_roll+" already is enrolled in IITH"<<endl;
                        counter=true;
                    }
                }
                f.close();
                if(counter==true)
                break;
                cout<<"Enter the name of the student to be added"<<endl;
                getline(cin,s_name);
                cout<<"Enter the branch of the student"<<endl;
                getline(cin,s_branch);
                cout<<"Enter the program in which the student is enrolled"<<endl;
                getline(cin,s_program);
                Student stud(s_name,s_roll,s_branch,s_program);
                f.open("DATA/Students/students.txt",ios::app);
                ad.addStud(f,stud);
                break;
                }
                
        case 5:
                {
                    string temp;
                    getline(cin,temp);
                    ad.float_course();
                    break;
                }
        case 6:
                {
                string temp;
                getline(cin,temp);
                ad.delCourse();
                break;
                }
        case 7:
                {
                cout<<"Enter the roll number of the student you want to remove from IITH"<<endl;
                string stud_roll;
                getline(cin,stud_roll);
                getline(cin,stud_roll);
                cout<<"Are you sure you want to delete "+stud_roll+" from IITH (Y/N)"<<endl;
                char c;
                cin>>c;
                if(c=='Y'||c=='y')
                {
                    
                    fstream f("DATA/Students/students.txt",ios::in);
                    string temp;
                    string name;
                    string branch;
                    string program;
                    bool counter=false;
                    while(getline(f,temp))
                    {
                        if(temp==stud_roll)
                        {
                            counter =true;
                            getline(f,temp);
                            getline(f,name);
                            getline(f,branch);
                            getline(f,program);
                        }
                        else
                        {
                            for(int i =1; i<=4;++i)
                            {
                                getline(f,temp);
                            }
                        }
                    }
                    if(counter==false)
                    {
                        cout<<"Cannot find "+stud_roll+" in IITH"<<endl;
                        break;
                    }
                    Student stud(name,stud_roll,branch,program);
                    f.close();
                    f.open("DATA/Students/students.txt",ios::in);
                    ad.delStud(f,stud);
                    f.close();
                }
                else
                cout<<"Aborted the process of removing the student from IITH"<<endl;
                break;
                }
        case 8:
                {
                cout<<"Enter the name of the faculty you want to remove from IITH"<<endl;
                string name;
                getline(cin,name);
                getline(cin,name);
                cout<<"Are you sure you want to remove "+name+" from IITH (Y/N)"<<endl;
                char c;
                cin>>c;
                if(c=='Y'||c=='y')
                {
                    fstream f("DATA/Faculty/faculty.txt",ios::in);
                    Faculty fc(name);
                    ad.delFaculty(f,fc);
                    f.close();
                }
                break;
                }
        case 9:
                {
                    cout<<"Enter the roll of the student"<<endl;
                string roll;
                getline(cin,roll);
                getline(cin,roll);
                cout<<"Enter the course you want the student to be removed from"<<endl;
                string course;
                getline(cin,course);
                ad.delStud(course,roll);
                break;
                }


    }
    char ch;
    string trash;
    cout<<"Press X to exit Admin choice menu"<<endl;
    cin>>ch;
    getline(cin,trash);
    if(ch=='X')
    {
        cont=false;
    }
    }
}

void input_handler(Faculty fc)
{
    int n;
    cout<<"Below is a list of operations you can do as a faculty"<<endl;
    bool cont=true;
    while(cont)
    {
        cout<<"Press 1 to look at the list of students in a particular course\nPress 2 to give grade to a particular student enrolled in a particular course\nPress 3 to give grades to all students in a particular course"<<endl;
    cin>>n;
    switch(n)
    {
        case 1:
        {
            cout<<"Enter the course whose student list you wish to look at"<<endl;
            string course;
            getline(cin,course);
            getline(cin,course);
            fc.lookStudents(course);
            break;
        }
        case 2:
        {
            cout<<"Enter the course which you want to grade"<<endl;
            string course;
            getline(cin,course);
            getline(cin,course);
            cout<<"Enter the student's roll whom you wish to grade"<<endl;
            string roll;
            getline(cin,roll);
            string name;
            string branch;
            string program;
            fstream f("DATA/Students/students.txt",ios::in);
            string temp;
            while(getline(f,temp))
            {
                if(temp==roll)
                {
                    getline(f,temp);
                    getline(f,name);
                    getline(f,branch);
                    getline(f,program);
                }
            }
            f.close();
            string file_name="DATA/Courses/"+course+".txt";
            Student st(name,roll,branch,program);
            fc.give_grade(st.roll,course);
            break;
        }
        case 3:
        {
            cout<<"Enter the course you want to grade"<<endl;
            string course;
            getline(cin,course);
            getline(cin,course);
            string file_name="DATA/Courses/"+course+".txt";
            fc.give_grade(course);
        }
    }
    cout<<"Press X to exit Faculty choice window"<<endl;
    char ch;
    string trash;
    cin>>ch;
    getline(cin,trash);
    if(ch=='X')
    {
        cont=false;
    }
    }
}

void input_handler(Student st)
{
    int n;
    cout<<"Below is the menu for the operations that a student can do"<<endl;
    bool cont=true;
    while(cont)
    {
    cout<<"Press 1 to register for a course\nPress 2 to de-register from a course\nPress 3 to look at your grades in an enrolled course"<<endl;
    cin>>n;
    
    
    switch(n)
    {
        
        case 1:
                {
                    string t;
                    getline(cin,t);
                    string file_name="DATA/Students/"+st.roll+".txt";
                    fstream f(file_name.c_str(),ios::app);
                    st.registerCourse(f);
                    break;
                }
        case 2:
                {
                    string file_name="DATA/Students/"+st.roll+".txt";
                    fstream f(file_name.c_str(),ios::in);
                    st.deregisterCourse(f);
                    break;
                }
        case 3:
                {
                    string course;
                    getline(cin,course);
                    cout<<"Enter the course whose grades you want to look at"<<endl;
                    getline(cin,course);
                    cout<<Course::get_grade(st.roll,course)<<endl;
                }
    }
    cout<<"Press X to exit Student choice window"<<endl;
    char ch;
    string trash;
    cin>>ch;
    getline(cin,trash);
    if(ch=='X')
    {
        cont=false;
    }
    }
}

int main()
{
    cout<<"Welcome to mini_aims"<<endl;
    bool cont=true;
    while(cont)
    {
    cout<<"What role do you want to assume?"<<endl;
    cout<<"Press 1 to login as an admin\nPress 2 to login as a faculty\nPress 3 to login as a student"<<endl;
    int c;
    string temp;
    cin>>c;
    getline(cin,temp);
    
    switch(c)
    {
        case 1:
                {
                    cout<<"Enter your name"<<endl;
                    string name;
                    
                    getline(cin,name);
                    fstream f("DATA/Admins/Admins.txt",ios::in);
                    string test;
                    string pass;
                    cout<<"Enter the password"<<endl;
                    getline(cin,pass);
                    bool counter=false;
                    while(getline(f,test))
                    {
                        if(test==name)
                        {
                            counter=true;
                            getline(f,test);
                            if(test==pass)
                            {
                                f.close();
                                Admin ad(name);
                                input_handler(ad);
                            }
                            else
                            {
                                cout<<"Wrong password entered"<<endl;
                            }
                        }
                   

                    }
                     if(counter==false)
                    {
                        cout<<"No such admin is registered in IITH"<<endl;
                    }
                    break;
                }
        case 2:
        {
             cout<<"Enter your name"<<endl;
                    string name;
                    //cin>>c;
                    //getline(cin,name);
                    getline(cin,name);
                    fstream f("DATA/Faculty/faculty.txt",ios::in);
                    string test;
                    string pass;
                    cout<<"Enter the password"<<endl;
                    getline(cin,pass);
                    bool counter=false;
                    while(getline(f,test))
                    {
                        if(test==name)
                        {
                            counter=true;
                            getline(f,test);
                            if(test==pass)
                            {   
                                f.close();
                                Faculty fac(name);
                                input_handler(fac);
                            }
                            else
                            {
                                cout<<"Wrong password entered"<<endl;
                            }
                        }
                   

                    }
                     if(counter==false)
                    {
                        cout<<"No such faculty is registered in IITH"<<endl;
                    }
                    break;
        }
        case 3:
        {
            cout<<"Enter your roll number"<<endl;
                    string roll;
                    //cin>>c;
                    getline(cin,roll);
                    fstream f("DATA/Students/students.txt",ios::in);
                    string test;
                    string name;
                    string branch;
                    string program;
                    string pass;
                    cout<<"Enter the password"<<endl;
                    //cout<<roll<<endl;
                    getline(cin,pass);
                    bool counter=false;
                    while(getline(f,test))
                    {
                        //cout<<test<<endl;
                        if(test==roll)
                        {
                            counter=true;
                            getline(f,test);
                            if(test==pass)
                            {
                                
                                getline(f,name);
                                getline(f,branch);
                                getline(f,program);
                                f.close();
                                Student st(name,roll,branch,program);
                                input_handler(st);
                            }
                            else
                            {
                                cout<<"Wrong password entered"<<endl;
                            }
                        }
                        getline(f,test);
                        getline(f,test);
                        getline(f,test);
                        getline(f,test);
                    }
                     if(counter==false)
                    {
                        cout<<"No such student is registered in IITH"<<endl;
                    }
                    break;
        }
    }
    cout<<"Press X to exit position selection window"<<endl;
    char ch;
    cin>>ch;
    string trash;
    getline(cin,trash);
    if(ch=='X')
        cont=false;
    }

    return 0;
}