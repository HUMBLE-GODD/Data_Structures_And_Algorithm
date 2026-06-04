#include <bits/stdc++.h>
using namespace std;

class Node {
public:
    int data;
    Node* address;

    Node(int data1) {
        data = data1;
        address = nullptr;
    }

       Node(int data1, Node* address1) {
        data = data1;
        address = address1;
    }
};

int main() {

    vector<int> arr = {1, 2, 3, 4, 5};

    // Creating objects
    Node obj1(arr[0]);
    Node obj2(arr[1]);
    Node obj3(arr[2]);
    Node obj4(arr[3]);
    Node obj5(arr[4]);

    // Linking using . operator
    obj1.address = &obj2;
    obj2.address = &obj3;
    obj3.address = &obj4;
    obj4.address = &obj5;
    obj5.address = nullptr;

    // Traversal
    Node* temp = &obj1;

    while (temp != nullptr) {
        cout << temp->data << " ";
        temp = temp->address;
    }

    return 0;
}