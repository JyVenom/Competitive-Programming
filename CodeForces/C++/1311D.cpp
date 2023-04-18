#include <bits/stdc++.h>

using namespace std;

#pragma GCC optimize("Ofast")
#pragma GCC target("avx,avx2,fma")

int main()
{
    ios_base::sync_with_stdio(false);
    cin.tie(0);

    int t; cin>>t;
    while(t-- > 0)
    {
        int a,b,c;
        cin>>a>>b>>c;

        int min = 30001;
        int ans[3];
        int a2 = a*2;
        int b2 = b*2;
        for (int A = 1; A < a; A++) {
            for (int B = A; B < b2; B += A) {
                int C = (c / B) * B;
                int C2 = C + B;
                if (B <= C) {
                    int tmp = a - A + abs(b - B) + c - C;
                    if (tmp < min) {
                        min = tmp;
                        ans[0] = A;
                        ans[1] = B;
                        ans[2] = C;
                    }
                }
                if (B <= C2) {
                    int tmp = a - A + abs(b - B) + C2 - c;
                    if (tmp < min) {
                        min = tmp;
                        ans[0] = A;
                        ans[1] = B;
                        ans[2] = C2;
                    }
                }
            }
        }
        for (int A = a; A < a2; A++) {
            for (int B = A; B < b2; B += A) {
                int C = (c / B) * B;
                int C2 = C + B;
                if (B <= C) {
                    int tmp = A - a + abs(b - B) + c - C;
                    if (tmp < min) {
                        min = tmp;
                        ans[0] = A;
                        ans[1] = B;
                        ans[2] = C;
                    }
                }
                if (B <= C2) {
                    int tmp = A - a + abs(b - B) + C2 - c;
                    if (tmp < min) {
                        min = tmp;
                        ans[0] = A;
                        ans[1] = B;
                        ans[2] = C2;
                    }
                }
            }
        }

        cout<<min<<"\n"<<ans[0]<<" "<<ans[1]<<" "<<ans[2]<<"\n";
    }
    return 0;
}