#include <bits/stdc++.h>

using namespace std;

namespace FastIO {
	const int BSZ = 1<<15; ////// INPUT
	char ibuf[BSZ]; int ipos, ilen;
	char nc() { // next char
		if (ipos == ilen) {
			ipos = 0; ilen = fread(ibuf,1,BSZ,stdin);
			if (!ilen) return EOF;
		}
		return ibuf[ipos++];
	}
	void rs(str& x) { // read str
		char ch; while (isspace(ch = nc()));
		do { x += ch; } while (!isspace(ch = nc()) && ch != EOF);
	}
	template<class T> void ri(T& x) { // read int or ll
		char ch; int sgn = 1;
		while (!isdigit(ch = nc())) if (ch == '-') sgn *= -1;
		x = ch-'0'; while (isdigit(ch = nc())) x = x*10+(ch-'0');
		x *= sgn;
	}
	template<class T, class... Ts> void ri(T& t, Ts&... ts) {
		ri(t); ri(ts...); } // read ints
	////// OUTPUT (call initO() at start)
	char obuf[BSZ], numBuf[100]; int opos;
	void flushOut() { fwrite(obuf,1,opos,stdout); opos = 0; }
	void wc(char c) { // write char
		if (opos == BSZ) flushOut();
		obuf[opos++] = c; }
	void ws(str s) { each(c,s) wc(c); } // write str
	template<class T> void wi(T x, char after = '\0') { /// write int
		if (x < 0) wc('-'), x *= -1;
		int len = 0; for (;x>=10;x/=10) numBuf[len++] = '0'+(x%10);
		wc('0'+x); R0F(i,len) wc(numBuf[i]);
		if (after) wc(after);
	}
	void initO() { assert(atexit(flushOut) == 0); } /// auto-flush output
}
/// initO(); int a,b; ri(a,b); wi(b,'\n'); wi(a,'\n');

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
using namespace FastIO;
int main()
{
    initO();
    cin.tie(0)->sync_with_stdio(0);
    int n; ri(n);
    for (int i = 0; i < n; i++) {
        ri(a[i].X);
        a[i].Y = i+1;
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
	wi((G(n)+p-1)%p, '\n');
	return 0;
}