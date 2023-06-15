#include <iostream>
#include <fstream>
#include "admin.h"

Admin::Admin(string &s):name(s) {}

fstream& Admin::addStud(fstream &f,Student &s)
{
    f<<s.roll<<"\n";
    cout<<"Enter the password for "+s.roll<<endl;
    string pass;
    getline(cin,pass);
    f<<pass+"\n";
    f<<s.name+"\n";
    f<<s.branch+"\n";
    f<<s.program+"\n";
    fstream f1("DATA/Students/"+s.roll+".txt",ios::out);
    f1.close();
    return f;
}

fstream& Admin::addFaculty(fstream &f,Faculty &fac)
{
    f<<fac.name<<"\n";
    fstream f1("DATA/Faculty/"+fac.name+".txt",ios::out);
    cout<<"Enter the password for "+fac.name<<endl;
    string pass;
    getline(cin,pass);
    f<<pass+"\n";
    f1.close();
    return f;
}

void Admin::delStud(fstream &f,Student st)
{
    fstream f_temp("DATA/Students/temp.txt",ios::out);
    string roll;
    bool counter=false;
    while(getline(f,roll))
    {
        if(roll!=st.roll)
            {
                f_temp<<roll<<endl;
                for(int i=1;i<=4;++i)
                {
                    getline(f,roll);
                    f_temp<<roll+"\n";
                }
            }
        else
            {
                counter=true;
                for(int i =0;i<4;++i)
                getline(f,roll);
            }
    }
    if(!counter)
    cout<<"Could not find "<<st.roll<<" in IITH"<<endl;
    else
    cout<<"Successfully removed "<<st.roll<<" from IITH"<<endl;
    f.close();
    f_temp.close();
    remove("DATA/Students/students.txt");
    rename("DATA/Students/temp.txt","DATA/Students/students.txt");
    if(counter)
    {
        string file_name="DATA/Students/"+st.roll+".txt";
        remove(file_name.c_str());
    }
}

void Admin::delFaculty(fstream &f,Faculty fc)
{
    fstream f1("DATA/Courses/floated_courses.txt",ios::in);
    string temp;
    string course_name;
    bool counter=false;
    vector<string> course_unfinished;
    while(getline(f1,course_name))
    {
       //cout<<course_name<<endl;
        string file_name="DATA/Courses/"+course_name+".txt";
        //cout<<file_name<<endl;
        fstream f2(file_name.c_str(),ios::in);
        
        getline(f2,temp);
        //cout<<temp<<endl;
        if(temp==fc.name)
        {
            int stud_count=0;
            while(getline(f2,temp))
            {
                ++stud_count;
                getline(f2,temp);
                if(temp=="Not_Yet_Graded"&&stud_count!=0)
                {
                    counter=true;
                    course_unfinished.push_back(course_name);
                }
                //cout<<temp<<endl;
            }
        }
        f2.close();
        
    }
    f1.close();
    if(course_unfinished.empty()==false)
    {
        cout<<"Cannot remove faculty yet as they have unfinished courses shown below"<<endl;
        auto ptr1=course_unfinished.begin();
        auto ptr2=course_unfinished.end();
        while(ptr1!=ptr2)
        {
            cout<<*ptr1<<endl;
            ptr1+=1;
        }
        return;
    }
    else

    {
     fstream f_temp("DATA/Faculty/temp.txt",ios::out);
     string name;
     bool counter=false;
     while(getline(f,name))
     {
        if(name!=fc.name)
            {
                //cout<<name<<endl;
                f_temp<<name<<endl;
                getline(f,name);
                f_temp<<name<<endl;
            }
        else
            {
                //cout<<name<<endl;
                counter=true;
                getline(f,name);
            }
     }
     if(!counter)
     cout<<"Could not find "<<fc.name<<" in IITH"<<endl;
     else
     cout<<"Successfully removed "<<fc.name<<" from IITH"<<endl;
     f.close();
     f_temp.close();
     remove("DATA/Faculty/faculty.txt");
     rename("DATA/Faculty/temp.txt","DATA/Faculty/faculty.txt");
    }
    string file_name="DATA/Faculty/"+fc.name+".txt";
    remove(file_name.c_str());
}

void Admin::delStud(string course,string stud)
{
    fstream f_temp("DATA/Courses/temp.txt",ios::out);
    fstream f("DATA/Courses/"+course+".txt",ios::in);
    string grade=Course::get_grade(stud,course);
    if(grade!="Not_Yet_Graded")
    {
        cout<<"This course has already been graded"<<endl;
        return;
    }
    string temp;
    bool counter=false;
    while(getline(f,temp))
    {
        if(temp==stud)
        {
            getline(f,temp);
            counter=true;
        }
        else
        {
            f_temp<<temp;
            getline(f,temp);
            f_temp<<temp;
        }
    }
    f_temp.close();
    f.close();

    string file_name="DATA/Courses/"+course+".txt";
    remove(file_name.c_str());
    rename("DATA/Courses/temp.txt",file_name.c_str());
}

bool Admin::isFaculty(string look)
{
    fstream f("DATA/Faculty/faculty.txt",ios::in);
    string name;
    bool counter=false;
    while(getline(f,name))
    {
        if(name==look)
        return true;
    }
    return false;
}

void Admin::float_course()
{
    cout<<"Enter the course ID, course name and course instructor in seperate lines"<<endl;
    string course_ID;
    string course_name;
    string course_instructor;

    getline(cin,course_ID);
    fstream fl("DATA/Courses/floated_courses.txt",ios::in);
    bool counter=false;
    string temp;
    while(getline(fl,temp))
    {
        if(temp==course_ID)
             counter=true;
        
    }
    if(counter)
    {
        cout<<"This course already exists"<<endl;
        return;
    }
    getline(cin,course_name);
    getline(cin,course_instructor);

    if(!isFaculty(course_instructor))
    {
        cout<<course_instructor<<" is not in IITH, therfore cannot create such a course"<<endl;
        return;
    }

    fstream floated("DATA/Courses/floated_courses.txt",ios::app);
    floated<<course_ID<<'\n'<<course_name<<'\n'<<course_instructor<<'\n';
    floated.close();
    fstream fac("DATA/Faculty/"+course_instructor+".txt",ios::app);
    fac<<course_ID+'\n';
    fac.close();
    fstream cour("DATA/Courses/"+course_ID+".txt",ios::out);
    cour<<course_instructor+'\n';
    cour.close();

}

void Admin::addAdmin()
{
    fstream ad("DATA/Admins/Admins.txt",ios::app);
    string admin_name;
    cout<<"Enter the name of the admin to be added"<<endl;
    getline(cin,admin_name);
    getline(cin,admin_name);
    cout<<"stored name"<<endl;
    ad<<admin_name+"\n";
    cout<<"Enter the password for "+admin_name<<endl;
    getline(cin,admin_name);
    ad<<admin_name+"\n";
    ad.close();
}

void Admin::delAdmin()
{
    fstream ad("DATA/Admins/Admins.txt",ios::in);
    fstream f_temp("DATA/Admins/temp.txt",ios::out);
    string name;
    while(getline(ad,name))
    {
        if(name==this->name)
        {
            getline(ad,name);
        }
        else
        {
            f_temp<<name+"\n";
            getline(ad,name);
            f_temp<<name+"\n";
        }
    }
    f_temp.close();
    ad.close();
    remove("DATA/Admins/Admins.txt");
    rename("DATA/Admins/temp.txt","DATA/Admins/Admins.txt");
}

void Admin::delCourse()
{
    cout<<"Enter the code of the course you want to delete"<<endl;
    string course_code;
    getline(cin,course_code);
    fstream f("DATA/Courses/floated_courses.txt",ios::in);
    string temp;
    bool counter =false;
    while(getline(f,temp))
    {
        if(temp==course_code)
        {
            counter=true;
        }
    }
    if(counter==false)
    {
        cout<<course_code+" is not on the list of floated courses"<<endl;
        f.close();
        return;
    }
    f.close();
    cout<<"Are you sure you want to delete "+course_code+" (Y/N)"<<endl;
    char c;
    cin>>c;
    if(c=='Y'||c=='y')
    {
        counter = false;
         int num_stud=0;
        int num_grade=0;
        fstream f("DATA/Courses/"+course_code+".txt",ios::in);
        getline(f,temp);
        while(getline(f,temp))
        {   
            //cout<<temp<<endl;
            ++num_stud;
            getline(f,temp);
            if(temp!="Not_Yet_Graded")
            {
                ++num_grade;
                counter=true;
            }
        }
        f.close();
        if(!counter||(counter&&num_stud==num_grade))
        {
            //cout<<"entered"<<endl;
        f.open("DATA/Courses/floated_courses.txt",ios::in);
       
        fstream f_temp("DATA/Courses/temp.txt",ios::out);
        while(getline(f,temp))
        {
            if(temp==course_code)
            {
                getline(f,temp);
                getlin(f,temp);
            }
            else
            {
                f_temp<<temp+"\n";
            }
        }
        f.close();
        f_temp.close();
        remove("DATA/Courses/floated_courses.txt");
        rename("DATA/Courses/temp.txt","DATA/Courses/floated_courses.txt");
        }
        else if(num_stud!=num_grade)
        {
            cout<<"The course has already been partially graded hence cannot remove it at the moment"<<endl;
            return;
        }
    }
}

