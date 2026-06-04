#include <bits/stdc++.h>
using namespace std;

class Node {
public:
    int data;
    Node* address;

    // Constructor with data and next node
    Node(int data1, Node* address1) {
        data = data1;
        address = address1;
    }

    // Constructor with only data
    Node(int data1) {
        data = data1;
        address = nullptr;
    }

     Node* Insert(int data1, Node*head) {
     Node* newnode = new Node(data1,head);
     return newnode;
    }

     };

int main() {

    vector<int> arr = {1, 2, 3, 4, 5,6};

    Node* y1 = new Node(arr[0]);
    Node* y2 = new Node(arr[1]);
    Node* y3 = new Node(arr[2]);
    Node* y4 = new Node(arr[3]);
    Node* y5 = new Node(arr[4]);

    // Linking nodes
    y1->address = y2;
    y2->address = y3;
    y3->address = y4;
    y4->address = y5;
    y5->address = nullptr;

    Node obj1(0);

    y1 = obj1.Insert(arr[5],y1);
    


    // Traversing linked list
    Node* temp = y1;

    while (temp != nullptr) {
        cout << temp->data << " ";
        temp = temp->address;
    }

    return 0;
}