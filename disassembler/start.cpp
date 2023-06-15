#include <bits/stdc++.h>
#include <string>
#include <iostream>
#include <fstream>
using namespace std;

string cha_to_bin(char k)
{
    string n;
    if (k == '0')
        n = "0000";
    if (k == '1')
        n = "0001";
    if (k == '2')
        n = "0010";
    if (k == '3')
        n = "0011";
    if (k == '4')
        n = "0100";
    if (k == '5')
        n = "0101";
    if (k == '6')
        n = "0110";
    if (k == '7')
        n = "0111";
    if (k == '8')
        n = "1000";
    if (k == '9')
        n = "1001";
    if (k == 'a' || k == 'A')
        n = "1010";
    if (k == 'b' || k == 'B')
        n = "1011";
    if (k == 'c' || k == 'C')
        n = "1100";
    if (k == 'd' || k == 'D')
        n = "1101";
    if (k == 'e' || k == 'E')
        n = "1110";
    if (k == 'f' || k == 'F')
        n = "1111";

    return n;
}

string hex_to_bin(char k[])
{
    string a;
    int i = 0;
    while (i < 8)
    {
        string c = cha_to_bin(k[i]);
        a = a + c;
        i = i + 1;
    }
    return a;
}

int main()
{

    int count = 0;
    string line;

    ifstream file("lines.txt");
    while (getline(file, line))
    {
        count++;
    }
    file.close();

    int branches[count + 1];
    for (int yy = 0; yy <= (count + 1); yy++)
    {
        branches[yy] = 0;
    }
    string all[count + 1];

    string lines;
    ifstream Filee("lines.txt");
    int i = 0;
    int j = 0;

    while (getline(Filee, lines))
    {
        i = i + 1;
        char k[8];
        strcpy(k, lines.c_str());
        string b;
        b = hex_to_bin(k);

        string kty = b.substr(25, 7);
        string a;
        string c;
        string final;

        // add
        if (kty == "0110011")
        {
            int k;

            a = b.substr(17, 3);
            c = b.substr(0, 7);

            if (a == "000" && c == "0000000")
                final = final + "add ";
            if (a == "000" && c == "0100000")
                final = final + "sub ";
            if (a == "111" && c == "0000000")
                final = final + "and ";
            if (a == "110" && c == "0000000")
                final = final + "or ";
            if (a == "100" && c == "0000000")
                final = final + "xor ";
            if (a == "001" && c == "0000000")
                final = final + "sll ";
            if (a == "101" && c == "0000000")
                final = final + "srl ";
            if (a == "101" && c == "0100000")
                final = final + "sra ";

            a = b.substr(20, 5);
            k = stoi(a, 0, 2);
            c = "x";
            c = c + to_string(k);
            final = final + c;
            final = final + ",";

            a = b.substr(12, 5);
            k = stoi(a, 0, 2);
            c = "x";
            c = c + to_string(k);
            final = final + c;
            final = final + ",";

            a = b.substr(7, 5);
            k = stoi(a, 0, 2);
            c = "x";
            c = c + to_string(k);
            final = final + c;

            all[i] = final;
        }
        // addi
        if (kty == "0010011")
        {
            int k;

            a = b.substr(17, 3);

            if (a == "000")
                final = final + "addi ";
            if (a == "111")
                final = final + "andi ";
            if (a == "110")
                final = final + "ori ";
            if (a == "100")
                final = final + "xori ";

            if (a == "000" || a == "111" || a == "110" || a == "100")
            {
                a = b.substr(20, 5);
                k = stoi(a, 0, 2);
                c = "x";
                c = c + to_string(k);
                final = final + c;
                final = final + ",";

                a = b.substr(12, 5);
                k = stoi(a, 0, 2);
                c = "x";
                c = c + to_string(k);
                final = final + c;
                final = final + ",";

                a = b.substr(0, 12);
                k = stoi(a, 0, 2);

                if (a[0] == '1')
                {
                    k = k - 4096;
                }

                final = final + to_string(k);

                all[i] = final;
            }
            else
            {

                c = b.substr(0, 6);

                if (a == "001" && c == "000000")
                    final = final + "slli ";
                if (a == "101" && c == "000000")
                    final = final + "srli ";
                if (a == "101" && c == "010000")
                    final = final + "srai ";

                a = b.substr(20, 5);
                k = stoi(a, 0, 2);
                c = "x";
                c = c + to_string(k);
                final = final + c;
                final = final + ",";

                a = b.substr(12, 5);
                k = stoi(a, 0, 2);
                c = "x";
                c = c + to_string(k);
                final = final + c;
                final = final + ",";

                a = b.substr(6, 6);
                k = stoi(a, 0, 2);

                final = final + to_string(k);

                all[i] = final;
            }
        }
        //  ld
        if (kty == "0000011")
        {
            int k;

            a = b.substr(17, 3);

            if (a == "000")
                final = final + "lb ";
            if (a == "001")
                final = final + "lh ";
            if (a == "010")
                final = final + "lw ";
            if (a == "011")
                final = final + "ld ";
            if (a == "100")
                final = final + "lbu ";
            if (a == "101")
                final = final + "lhu ";
            if (a == "110")
                final = final + "lwu ";

            a = b.substr(20, 5);
            k = stoi(a, 0, 2);
            c = "x";
            c = c + to_string(k);
            final = final + c;
            final = final + ",";

            a = b.substr(0, 12);
            k = stoi(a, 0, 2);

            if (a[0] == '1')
            {
                k = k - 4096;
            }

            final = final + to_string(k);
            final = final + "(";

            a = b.substr(12, 5);
            k = stoi(a, 0, 2);
            c = "x";
            c = c + to_string(k);
            final = final + c;
            final = final + ")";

            all[i] = final;
        }
        //  sw
        if (kty == "0100011")
        {
            int k;

            a = b.substr(17, 3);

            if (a == "000")
                final = final + "sb ";
            if (a == "001")
                final = final + "sh ";
            if (a == "010")
                final = final + "sw ";
            if (a == "011")
                final = final + "sd ";

            a = b.substr(7, 5);
            k = stoi(a, 0, 2);
            c = "x";
            c = c + to_string(k);
            final = final + c;
            final = final + ",";

            a = b.substr(0, 7);
            a = a + b.substr(20, 5);
            k = stoi(a, 0, 2);

            if (a[0] == '1')
            {
                k = k - 4096;
            }

            final = final + to_string(k);
            final = final + "(";

            a = b.substr(12, 5);
            k = stoi(a, 0, 2);
            c = "x";
            c = c + to_string(k);
            final = final + c;

            final = final + ")";

            all[i] = final;
        }
        //  branch offset
        if (kty == "1100011")
        {
            int k;

            a = b.substr(17, 3);
            c = b.substr(0, 7);

            if (a == "000")
                final = final + "beq ";
            if (a == "001")
                final = final + "bne ";
            if (a == "100")
                final = final + "blt ";
            if (a == "101")
                final = final + "bge ";
            if (a == "110")
                final = final + "bltu ";
            if (a == "111")
                final = final + "bgeu ";

            a = b.substr(12, 5);
            k = stoi(a, 0, 2);
            c = "x";
            c = c + to_string(k);
            final = final + c;
            final = final + ",";

            a = b.substr(7, 5);
            k = stoi(a, 0, 2);
            c = "x";
            c = c + to_string(k);
            final = final + c;
            final = final + ",";

            a = b.substr(0, 1);
            a = a + b.substr(24, 1);
            a = a + b.substr(1, 6);
            a = a + b.substr(20, 4);
            k = stoi(a, 0, 2);

            if (a[0] == '1')
            {
                k = k - 4096;
            }

            k = k * 2;

            if ((i + k / 4) >= 0)
            {
                if (branches[i + (k / 4)] == 0)
                {
                    branches[i + (k / 4)] = i + k / 4;
                }
                final = final + "L";
                int p;
                p = (i + (k / 4));
                final = final + to_string(p);
                all[i] = final;
            }
            else
            {
                final = final + to_string(k);
                all[i] = final;
            }
        }
        //  jal offset
        if (kty == "1101111")
        {
            int long long k;

            a = b.substr(17, 3);
            c = b.substr(0, 7);

            final = final + "jal ";

            a = b.substr(20, 5);
            k = stoi(a, 0, 2);
            c = "x";
            c = c + to_string(k);
            final = final + c;
            final = final + ",";

            a = b.substr(0, 1);
            a = a + b.substr(12, 8);
            a = a + b.substr(11, 1);
            a = a + b.substr(1, 10);
            k = stoi(a, 0, 2);

            if (a[0] == '1')
            {
                k = k - 1048576; // 2 power 20
            }

            k = k * 2;

            if ((i + k / 4) >= 0)
            {
                if (branches[i + (k / 4)] == 0)
                {
                    branches[i + (k / 4)] = i + k / 4;
                }
                final = final + "L";
                int p;
                p = (i + (k / 4));
                final = final + to_string(p);
                all[i] = final;
            }
            else
            {
                final = final + to_string(k);
                all[i] = final;
            }
        }
        //  jalr
        if (kty == "1100111")
        {
            int k;

            a = b.substr(17, 3);

            if (a == "000")
                final = final + "jalr ";

            a = b.substr(20, 5);
            k = stoi(a, 0, 2);
            c = "x";
            c = c + to_string(k);
            final = final + c;
            final = final + ",";

            a = b.substr(12, 5);
            k = stoi(a, 0, 2);
            c = "x";
            c = c + to_string(k);
            final = final + c;
            final = final + ",";

            a = b.substr(0, 12);
            k = stoi(a, 0, 2);

            if (a[0] == '1')
            {
                k = k - 4096;
            }

            final = final + to_string(k);

            all[i] = final;
        }
        //  lui
        if (kty == "0110111")
        {
            int k;

            final = final + "lui ";

            a = b.substr(20, 5);
            k = stoi(a, 0, 2);
            c = "x";
            c = c + to_string(k);
            final = final + c;
            final = final + ",";
            char hex_string[20];

            a = b.substr(0, 20);
            k = stoi(a, 0, 2);

            sprintf(hex_string, "%X", k);

            final = final + "0X";
            final = final + hex_string;

            all[i] = final;
        }
    }
    Filee.close();

    string kuu;

    for (i = 1; i < (count + 1); i++)
    {
        if (branches[i] == i)
        {
              cout << "L" << i << ": ";
        }
        if (kuu != all[i])
        {
            cout << all[i] << "\n";
        }
    }
    return 0;
}