#include <bits/stdc++.h>

using namespace std;

#define X first
#define Y second

const int N = 100020;
typedef pair<int,int>ii;
ii a[N];
int c[N];
int n,p=1000000007;

void R(int x,int y)
{
	for(x++;x<=n+1;x+=x&-x)
		c[x]=(c[x]+y)%p;
}
int G(int x)
{
	int re=0;
	for(x++;x;x-=x&-x)
		re=(re+c[x])%p;
	return re;
}
int main()
{

    cin>>n;
    for (int i = 0; i < n; i++) {
        cin>>a[i].X;
        a[i].Y=i+1;
    }
	sort(a,a+n);
	R(0,1);
	for(int i=0,j;i<n;i=j)
	{
		int l=0;
		for(j=i;a[j].X==a[i].X;j++)
		{
			R(a[j].Y,(long long)(G(a[j].Y)-G(l-1))*(a[j].X)%p);
			l=a[j].Y;
		}
	}
	printf("%d\n",(G(n)+p-1)%p);
	return 0;
}