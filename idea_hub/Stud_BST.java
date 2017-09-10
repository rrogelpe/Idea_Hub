import java.io.Serializable;
public class Stud_BST implements Serializable
{
private Student root;

public Stud_BST()
{
root = null;
}

public void insert(Student x)
{
if (root == null)
{
root = x;//Insert first node
}
else
{
insert2(root,x);
}
}

public void insert2(Student t,Student x)
{
if (x.getStudentNum() < t.getStudentNum())//If node is less than root it will continue on the left side and find and find a null space
{
if (t.getLeft() == null)
{
t.setLeft(x);
//x.setParent(t);
}
else
{
insert2(t.getLeft(),x);
}
}
else
{
if (t.getRight() == null)//If node is greater than root it will take the right side and find a null space
{
t.setRight(x);
}
else
{
insert2(t.getRight(),x);
}
}
}

public Student search(int student_num)
{
if (root == null)
{
return null;
}
else
{
return search2(root,student_num);
}
}

public Student search2(Student t,int student_num)//Goes through BST by comparing the value of the node we're trying to find with the others
{
if (t.getStudentNum() == student_num)
{
return t;
}
else if (student_num < t.getStudentNum())
{
return search2(t.getLeft(),student_num);
}
else
{
return search2(t.getRight(),student_num);//Recursion+
}
}

public boolean isEmptyTree()
{
return root == null;
}

public void traverse()//Prints out contents of tree
{
if (root != null)
{
traverse2(root);
System.out.println();
}
else
{
System.out.println();
}
}

    public void traverse2(Student x) {
        x.updateAverage();
        if (x.getLeft() != null) {
            traverse2(x.getLeft());
        }

        System.out.println("Student number: " +  x.getStudentNum());
        System.out.println("Last Name: " + x.getLastName());
        System.out.println("Email Login: " + x.getLogin());
        System.out.println("SSN: " + x.getSSN());
        System.out.println("Average rating: "+ x.getAverage());
        System.out.println(" ");
        if (x.getRight() != null) {
            traverse2(x.getRight());
        }
    }

public void delete(Student x)
{
if (x.getStudentNum() == root.getStudentNum())
{
if (root.getLeft() == null)
{
Student temp = root;
root = root.getRight();
temp.setRight(null);
}
else if (root.getRight() == null)
{
Student temp = root;//If right side is empty. root.getLeft() becomes the new root
root = root.getLeft();
temp.setLeft(null);
}
else
{
Student temp = successor(root);
delete3(root,temp);
}
}
else if (x.getLeft() != null || x.getRight() != null)
{
delete1(root,x);
}
else if(x.getLeft() == null && x.getRight() == null)
{
delete2(root,x);
}
else
{
System.out.println("Node not found");
}
}

private Student successor(Student t)//Finds the Successor node if the node removed is the root
{
Student successorNode = t.getRight();
if (successorNode != null)
{
while (successorNode.getLeft() != null)
{
successorNode = successorNode.getLeft();
}
}
return successorNode;
}

public void delete3(Student x,Student t)
{
if (t.getStudentNum() < x.getStudentNum() )
{
if (x.getLeft().getStudentNum() == t.getStudentNum())
{
t.setLeft(root.getLeft());
x.setLeft(t.getRight());
t.setRight(root.getRight());
root = t;
}
else
{
delete3(x.getLeft(),t);
}
}
else
{
if (x.getRight().getStudentNum() == t.getStudentNum())
{
root = root.getRight();
root.setLeft(x.getLeft());
x.setRight(null);
x.setLeft(null);
}
else
{
delete3(x.getRight(),t);
}
}
}

public void delete1(Student t,Student x)
{
if (x.getStudentNum() < t.getStudentNum())
{
if (t.getLeft().getStudentNum() == x.getStudentNum())
{
if (x.getRight() != null)
{
Student succesor = x.getRight();
t.setLeft(succesor);
while (succesor.getLeft() != null)
{
succesor = succesor.getLeft();
}
succesor.setLeft(x.getLeft());
}
else
{
Student succesor = x.getLeft();
t.setLeft(succesor);
}
}
else
{
delete1(t.getLeft(),x);
}
}
else
{
if (t.getRight().getStudentNum() == x.getStudentNum())
{
if (x.getRight() != null)
{
Student succesor = x.getRight();
t.setRight(succesor);
while (succesor.getRight() != null)
{
succesor = succesor.getRight();
}
succesor.setRight(x.getRight());
}
else
{
Student succesor = x.getLeft();
t.setRight(succesor);
}

}
else
{
delete1(t.getRight(),x);
}
}
}

public void delete2(Student t, Student x)
{//Goes through the BST tree until it finds the leaf. Then it simply sets it to null
if (x.getStudentNum() < t.getStudentNum())
{
if (x.getStudentNum() == t.getLeft().getStudentNum())
{
t.setLeft(null);
}
else
{
delete2(t.getLeft(),x);
}
}
else
{
if (x.getStudentNum() == t.getRight().getStudentNum())
{
t.setRight(null);
}
else
{
delete2(t.getRight(),x);
}
}
}

public void printTree()
{
printTree2(root);
System.out.println();
}

private void printTree2(Student tree)
{
if (tree != null)
{
    System.out.print(tree.getStudentNum() + " ");
        if (tree.getLeft() != null)
        {
        System.out.print("Left: " + tree.getLeft().getStudentNum() + " ");
        }
        else
        {
        System.out.print("Left: null ");
        }
        if (tree.getRight() != null)
        {
        System.out.println("Right: " + tree.getRight().getStudentNum() + " ");
        }
        else
        {
        System.out.println("Right: null ");
        }
        printTree2(tree.getLeft());
    printTree2(tree.getRight());
}
}
/*
public static void main(String[] args){
Stud_BST a = new Stud_BST();
System.out.println("isEmptyTree = " + a.isEmptyTree());
Student xNode = new Student("Rogel-Perez","rrogelpe", 123456789, 499);
Student yNode = new Student("Beltran","odiperez", 934567890, 322);
Student zNode = new Student("Zotelo", "rrogelzo", 223452234, 100);
Student kNode = new Student("Khan", "khmoha", 934567856, 789);
Student aNode = new Student("Abe", "amorty4", 123456788, 267);
Student bNode = new Student("Beth", "kmakeinch6", 934567898, 667);
Student cNode = new Student("Chuck", "nochuck3", 223452238, 134);
Student dNode = new Student("Dot", "jodot1", 934567858, 987);
Student mNode = new Student("Mike", "mijordan", 723452237, 578);
Student nNode = new Student("Nick", "marnick", 734567857, 784);
Student oNode = new Student("Otis", "daortiz", 734562222, 225);
Student hNode = new Student("Kratos", "kgow", 356783455, 350);
Student pNode = new Student("Boss", "cabos", 356767890, 340);

a.insert(kNode);
a.insert(xNode);
a.insert(yNode);
a.insert(zNode);
a.traverse();
a.printTree();
System.out.println("delete322");
a.delete(yNode);
a.traverse();
a.printTree();
System.out.println("insert578&&784");
a.insert(mNode);
a.insert(nNode);
a.traverse();
a.printTree();
System.out.println("insert667&&134");
a.insert(bNode);
a.insert(cNode);
a.traverse();
a.printTree();
System.out.println("delete578");
a.delete(mNode);
a.traverse();
a.printTree();
System.out.println("delete784");
a.delete(nNode);
a.traverse();
a.printTree();
System.out.println("insert267");
a.insert(aNode);
a.traverse();
a.printTree();
System.out.println("insert350&&340");
a.insert(hNode);
a.insert(pNode);
a.traverse();
a.printTree();
System.out.println("delete322");
a.delete(yNode);
a.traverse();
a.printTree();
System.out.println("delete499");
a.delete(xNode);
a.traverse();
a.printTree();
System.out.println("insert987");
a.insert(dNode);
a.traverse();
a.printTree();
System.out.println("insert225");
a.insert(oNode);
a.traverse();
a.printTree();
System.out.println("delete667");
a.delete(bNode);
a.traverse();
a.printTree();
}*/
}
