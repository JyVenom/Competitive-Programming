#include <bits/stdc++.h>
using namespace std;
#define ll long long
#define MOD 1000000007
ll n, m=0;
vector <ll> numbers;
vector <ll> bit;
vector <ll> p;

void update_bit(ll amount, ll index) {
    while (index <= m) {
        bit[index] = (bit[index]+amount)%MOD;
        index+= index & (-index);
    }
}

ll query(ll index) {
    ll ans =0;
    while (index > 0) {
        ans = (ans+bit[index])%MOD;
        index-= index & (-index);
    }
    return ans;
}

int main() {
    scanf("%lld", &n);
    numbers = vector <ll> (n);
    for (ll i=0; i<n; i++) {
        scanf("%lld", &numbers[i]);
        m = max(m, numbers[i]);
    }
    bit = vector <ll> (m+1, 0);
    p = vector <ll> (m+1, 0);
    for (ll i=0; i<n; i++) {
        ll val = (query(numbers[i])+1)%MOD;
        val = (val*numbers[i])%MOD;
        update_bit(MOD - p[numbers[i]] + val, numbers[i]);
        p[numbers[i]] = val;
    }
    printf("%lld", query(m));
}