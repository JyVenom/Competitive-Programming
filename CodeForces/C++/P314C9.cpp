#include <bits/stdc++.h>

using namespace std;

inline namespace FastIO {
	const int BSZ = 1<<16; ////// INPUT
	char ibuf[BSZ]; int ipos, ilen;
	char nc() { // next char
		if (ipos == ilen) {
			ipos = 0; ilen = fread(ibuf,1,BSZ,stdin);
			if (!ilen) return EOF;
		}
		return ibuf[ipos++];
	}
	template<class T> void ri(T& x) { // read int or ll
		char ch; int sgn = 1;
		while (!isdigit(ch = nc())) if (ch == '-') sgn *= -1;
		x = ch-'0'; while (isdigit(ch = nc())) x = x*10+(ch-'0');
		x *= sgn;
	}
}

#pragma GCC optimize("Ofast")
#pragma GCC target("avx,avx2,fma")

#define MAXN 100100
using LL = long long;
 
int N;
#define MOD 1000000007LL
LL ff[MAXN];
pair <int,int> a[MAXN];
LL res[MAXN];
 
LL upd(int x);
void query(int x, LL val);


int main(){
    cin.tie(0)->sync_with_stdio(0);

	ri(N);
	for(int i=0;i<N;i++){
		ri(a[i].first);
		a[i].second=i;
	}

	sort(a,a+N);
	LL sum=0;
	LL last=-1;
	for(int i=N-1;i>=0;i--){
		LL val=a[i].first;
		int it=a[i].second;
		res[it]=((upd(it)+1ll)*val)%MOD;
		if(last==val){
			res[it]=(res[it]+MOD-sum)%MOD;
			sum=(sum+res[it])%MOD;
		}
		else {
			last=val;
			sum=res[it];
		}
		query(it,res[it]);
	}

	LL ans=0;
	for(int i=0;i<N;i++)ans=(res[i]+ans)%MOD;

	cout << ans;
	return(0);
}

LL upd(int x){
	x=N-1-x;
	LL res=0;
	for(;x>=0;x=(x&(x+1))-1)res+=ff[x];
	return res%MOD;
}

void query(int x, LL val){
	x=N-1-x;
	for(;x<N;x|=x+1)ff[x]=(ff[x]+val)%MOD;
}
