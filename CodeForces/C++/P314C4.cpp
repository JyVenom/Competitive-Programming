#pragma GCC optimize("Ofast")
#pragma GCC target("avx,avx2,fma")

#include <bits/stdc++.h>

#define MAXN 100100
#define INF 1<< 30

typedef unsigned long long ULL;
typedef long long LL;

using namespace std;

int N;
LL mod=1e9+7;
LL ff[MAXN];

LL ask(int x){
	x=N-1-x;
	LL res=0;
	for(;x>=0;x=(x&(x+1))-1)res+=ff[x];
	return res%mod;
}

void summa(int x, LL val){
	x=N-1-x;
	for(;x<N;x|=x+1)ff[x]=(ff[x]+val)%mod;
}

pair <int,int> a[MAXN];
LL res[MAXN];

int main(){
	ios_base::sync_with_stdio(false);
	cin >> N;

	for(int i=0;i<N;i++){
		cin >> a[i].first;
		a[i].second=i;
	}
	sort(a,a+N);

	LL sum=0;
	LL last=-1;
	for(int j=N-1;j>=0;j--){
		LL val=a[j].first;
		int it=a[j].second;
		res[it]=((ask(it)+1ll)*val)%mod;
		if(last==val){
			res[it]=(res[it]+mod-sum)%mod;
			sum=(sum+res[it])%mod;
		}
		else {
			last=val;
			sum=res[it];
		}
		summa(it,res[it]);
	}
	LL ros=0;
	for(int i=0;i<N;i++)ros=(res[i]+ros)%mod;
	cout << ros << "\n";
}