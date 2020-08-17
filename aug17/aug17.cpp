#include<iostream>
#include<unordered_map>

using namespace std;

void freqMap(string s){
    unordered_map<char,int> map;

    for(char ch: s){
        map[ch]++;
    }
}

void main(){

}