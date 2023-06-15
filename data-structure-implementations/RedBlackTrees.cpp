#include <iostream>

using namespace std;

struct treenode
{
    int Age;
    int colour; // if 0 then red else black
    struct treenode *parent;
    struct treenode *left;
    struct treenode *right;
};

typedef struct treenode *Node;

Node left_rotate(Node *add, Node x)
{

    if (x->right != NULL)
    {
        Node a;
        Node b;

        if (*add == x)
        {
            *add = x->right;
            a = x;
            b = x->right;

            x->right = b->left;

            if (x->right != NULL)
            {
                b->left->parent = x;
            }

            b->left = a;

            a->parent = b;
            b->parent = NULL;
        }
        else
        {

            if (x == x->parent->right)
            {
                a = x;
                b = x->right;

                x->right = b->left;

                if (x->right != NULL)
                {
                    b->left->parent = x;
                }

                b->left = a;

                b->parent = x->parent;

                x->parent->right = b;
                x->parent = b;
            }
            else
            {

                a = x;
                b = x->right;

                x->right = x->right->left;

                if (x->right != NULL)
                {
                    b->left->parent = x;
                }

                b->left = x;

                b->parent = x->parent;

                x->parent->left = b;
                x->parent = b;
            }
        }

        return a;
    }
    return x;
}

Node right_rotate(Node *add, Node x)
{

    if (x->left != NULL)
    {
        Node a;
        Node b;

        if (*add == x)
        {
            *add = x->left;
            a = x;
            b = x->left;

            x->left = b->right;
            if (x->left != NULL)
            {
                b->right->parent = x;
            }
            b->right = a;

            a->parent = b;
            b->parent = NULL;
        }
        else
        {

            if (x == x->parent->left)
            {
                a = x;
                b = x->left;

                x->left = b->right;
                if (x->left != NULL)
                {
                    b->right->parent = x;
                }
                b->right = a;

                b->parent = x->parent;

                x->parent->left = b;
                x->parent = b;
            }
            else
            {

                a = x;
                b = x->left;

                x->left = b->right;

                if (x->left != NULL)
                {
                    b->right->parent = x;
                }
                b->right = a;

                b->parent = x->parent;

                x->parent->right = b;
                x->parent = b;
            }
        }

        return a;
    }
    return x;
}

int AddToTree(Node *add, int ab)
{

    Node parent = NULL;
    Node present = *add;
    Node root = *add;

    int found = 0;

    while (present != NULL)
    {

        if (ab == present->Age)
        {

            found = 1;
            break;
        }

        parent = present;

        if (ab < present->Age)
        {
            if (present->left != NULL)
            {
                present = present->left;
            }
            else
            {
                break;
            }
        }
        else
        {
            if (present->right != NULL)
            {
                present = present->right;
            }
            else
            {
                break;
            }
        }
    }

    int truth = 0;

    Node see;
    see = *add;

    if (found == 0)
    {

        Node x = new struct treenode;

        x->Age = ab;
        x->colour = 0;
        x->parent = parent;
        x->left = NULL;
        x->right = NULL;

        while (truth != 1)
        {

            if (*add == NULL)
            {

                *add = x;
                truth = 1;
                break;
            }
            else
            {

                if (x->Age < see->Age)
                {

                    if (see->left == NULL)
                    {

                        see->left = x;
                        truth = 1;
                        break;
                    }
                    else
                    {

                        see = see->left;
                    }
                }
                if (x->Age > see->Age)
                {

                    if (see->right == NULL)
                    {

                        see->right = x;
                        truth = 1;
                        break;
                    }
                    else
                    {

                        see = see->right;
                    }
                }
            }
        }

        Node y;

        while ((x->parent != NULL) && (x->parent->colour == 0))
        {

            if (x->parent == x->parent->parent->left)
            {

                /* If x's parent is a left, y is x's right 'uncle' */

                if (x->parent->parent->right == NULL) // if no uncle case 
                {
                    if (x == x->parent->right)  
                    {

                        x = x->parent; // page 1 remaining both cases

                        x = left_rotate(add, x);
                    }

                    x->parent->colour = 1;
                    x->parent->parent->colour = 0;
                    x = x->parent->parent;

                    x = right_rotate(add, x);
                }
                else
                {
                    y = x->parent->parent->right;
                    if (y->colour == 0) // check in notability for this case2
                    {

                        x->parent->colour = 1;
                        y->colour = 1;
                        x->parent->parent->colour = 0;
                        x = x->parent->parent;
                    }

                    else
                    {

                        if (x == x->parent->right)
                        {

                            x = x->parent; // page 1 remaining both cases

                            x = left_rotate(add, x);
                        }
                        else
                        {

                            x->parent->colour = 1;

                            x->parent->parent->colour = 0;

                            right_rotate(add, x->parent->parent);
                        }
                    }
                }
            }

            else
            {
                if (x->parent == x->parent->parent->right)
                {
                    /* If x's parent is a right, y is x's left 'uncle' */

                    if (x->parent->parent->left == NULL) // no uncle case
                    {
                        if (x == x->parent->left)
                        {

                            x = x->parent; // page 1 remaining both cases

                            x = right_rotate(add, x);
                        }
                        x->parent->colour = 1;
                        x->parent->parent->colour = 0;
                        x = x->parent->parent;

                        x = left_rotate(add, x);
                    }
                    else
                    {
                        y = x->parent->parent->left;
                        if (y->colour == 0) // check in notability for this case2
                        {

                            x->parent->colour = 1;
                            y->colour = 1;
                            x->parent->parent->colour = 0;
                            x = x->parent->parent;
                        }
                        else
                        {

                            if (x == x->parent->left)
                            {

                                x = x->parent; // page 1 remaining both cases

                                x = right_rotate(add, x);
                            }
                            else
                            {

                                x->parent->colour = 1;

                                x->parent->parent->colour = 0;

                                left_rotate(add, x->parent->parent);
                            }
                        }
                    }
                }
            }
        }
        if ((x->parent == NULL))
        {
            x->colour = 1;
        }

        return 0;
    }
    return 0;
}

int find(Node *add, int search)
{

    Node n = *add;

    while (n != NULL)
    {

        if (n->Age == search)
        {
            cout << "hurray we found the teacher Age  :" << search << "\n";
            return 1;
        }
        if (n->Age < search && n->right != NULL)
        {
            n = n->right;
        }
        if (n->Age < search && n->right == NULL)
        {
            return 0;
        }
        if (n->Age > search && n->left != NULL)
        {
            n = n->left;
        }
        if (n->Age > search && n->left == NULL)
        {
            return 0;
        }
    }

    return 0;
}

void freetree(Node node)
{
    if (node != NULL)
    {
        freetree(node->left);
        freetree(node->right);

        delete node;
    }
}

void inorder(Node ptr)
{
    string a ;

    if (ptr)
    {
        
        inorder(ptr->left);
        if(ptr->colour == 0 )
        {
        a = "RED" ;
        }
        else
        {
        a = "BLACK" ;
        }
        cout << ptr->Age << "  (" << a << ")  ";

        inorder(ptr->right);
    }
}

int main()
{

    treenode inithead = { 10, 1, NULL, NULL, NULL};

    Node head = new struct treenode;
    *head = inithead;

    Node *add = &head;

    AddToTree(add, 18);
    AddToTree(add, 7);
    AddToTree(add, 15);
    AddToTree(add, 16);
    AddToTree(add, 30);
    AddToTree(add, 25);
    AddToTree(add, 40);
    AddToTree(add, 60);
    AddToTree(add, 2);
    AddToTree(add, 1);
    AddToTree(add, 70);

    find(add, 70);
    find(add, 45);

    cout << "\n";
    inorder(*add);
    cout << "\n\n";

    cout << "The head is the element numbering : " << (*add)->Age << "\n";

    freetree(*add);

    return 0;
}
