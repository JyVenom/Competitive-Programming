#include <bits/stdc++.h>

using namespace std;

#pragma GCC optimize("Ofast")
#pragma GCC target("avx,avx2,fma")

using ll = long long;
#define MOD 1000000007
int m=0;
vector<ll> bit;

void upd(ll amt, ll ind);
ll query(ll ind);

int main() {
    cin.tie(0)->sync_with_stdio(0);

    int n; cin>>n;
    int nums[n];
    for (int i=0; i<n; i++) {
        cin>>nums[i];
        m = max(m, nums[i]);
    }

    bit = vector <ll> (m+1, 0);
    ll p[m+1];
    memset(p, 0, sizeof p);
    for (int i=0; i<n; i++) {
        ll val = (query(nums[i])+1)%MOD;
        val = (val*nums[i])%MOD;
        upd(MOD - p[nums[i]] + val, nums[i]);
        p[nums[i]] = val;
    }

    cout<<query(m);
}

void upd(ll amt, ll ind) {
    while (ind <= m) {
        bit[ind] = (bit[ind]+amt)%MOD;
        ind+= ind & (-ind);
    }
}

ll query(ll ind) {
    ll ans =0;
    while (ind > 0) {
        ans = (ans+bit[ind])%MOD;
        ind-= ind & (-ind);
    }
    return ans;
}
