#include<iostream>
#include<vector>
using namespace std;

void targetSum(vector<int>& arr, int idx, int tar, string ans){
    if(tar == 0|| idx == arr.size()){
        if(tar==0)
        cout<<ans<<endl;
        return;
    }
    
    
    
    if(tar-arr[idx]>=0){
        targetSum(arr,idx+1,tar-arr[idx],ans+to_string(arr[idx])+", ");
    }
    targetSum(arr,idx+1,tar,ans);
    
}

int main(){
    
    int n;
    cin>>n;
    
    vector<int> arr(n,0);
    
    for(int i =0;i<n;i++){
        cin>>arr[i];
    }
    
    int data;
    cin>>data;
    
    targetSum(arr,0,data,"");
    cout<<endl;
    
    return 0;
}