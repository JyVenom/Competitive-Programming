//.h file code:

#include <string>
#include <vector>
#include <iostream>

class SwapitySwapitySwap4
{
	static void main(std::vector<std::wstring> &args);

private:
	static bool inOrder(std::vector<int> &arr);
};

//.cpp file code:

#include "snippet.h"

void SwapitySwapitySwap4::main(std::vector<std::wstring> &args)
{
	long long sta = System::currentTimeMillis();
	FileReader tempVar(L"swap.in");
	BufferedReader *br = new BufferedReader(&tempVar);
	BufferedWriter tempVar2(new FileWriter(L"swap.out"));
	PrintWriter *pw = new PrintWriter(&tempVar2);
	StringTokenizer *st = new StringTokenizer(br->readLine());

	int n = static_cast<Integer>(st->nextToken());
	int m = static_cast<Integer>(st->nextToken());
	int k = static_cast<Integer>(st->nextToken());

	std::vector<std::vector<int>> swaps;
	for (int i = 0; i < n; i++)
	{
		swaps.push_back(std::vector<int>(2));
	}
	for (int i = 0; i < m;i++)
	{
		st = new StringTokenizer(br->readLine());
		int l = static_cast<Integer>(st->nextToken()) - 1;
		int r = static_cast<Integer>(st->nextToken()) - 1;
		swaps[i][0] = l;
		swaps[i][1] = r;
	}
	br->close();

	std::vector<int> order;
	for (int i = 1; i <= n; i++)
	{
		order.push_back(i);
	}
	for (int i = 0; i < m; i++)
	{
		int start = swaps[i][0];
		int end = swaps[i][1];
		bool good = true;
		for (int j = 0; j < m; j++)
		{
			if (j == i)
			{
				continue;
			}
			if (start >= swaps[j][0] && start <= swaps[j][1])
			{
				good = false;
				break;
			}
			if (end >= swaps[j][0] && end <= swaps[j][1])
			{
				good = false;
				break;
			}
		}
		if (good)
		{
			int swi = k % 2;
			if (swi == 1)
			{
				std::vector<int> temp;
				for (int l = swaps[i][0]; l <= swaps[i][1]; l++)
				{
					temp.push_back(order[swaps[i][0]]);
					order.erase(order.begin() + swaps[i][0]);
				}
				int size = temp.size();
				for (int l = 0; l < size; l++)
				{
					order.push_back(swaps[i][0],temp[0]);
					temp.erase(temp.begin());
				}
			}
			swaps.erase(swaps.begin() + i);
			i--;
		}
	}

	std::vector<std::vector<int>> all;
	all.push_back(order);
	int num = -1;
	std::vector<int> temp;
	for (int j = 0; j < m; j++)
	{
		for (int l = swaps[j][0]; l <= swaps[j][1]; l++)
		{
			temp.push_back(order[swaps[j][0]]);
			order.erase(order.begin() + swaps[j][0]);
		}
		int size = temp.size();
		for (int l = 0; l < size; l++)
		{
			order.push_back(swaps[j][0],temp[0]);
			temp.erase(temp.begin());
		}
	}
	std::vector<int> prev(order);
	all.push_back(prev);
	std::vector<std::vector<int>> switches;
	for (int i = 0; i < n; i++)
	{
		if (order[i] != i + 1)
		{
			std::vector<int> s;
			s.push_back(order[i] - 1);
			s.push_back(i);
			switches.push_back(s);
		}
	}
	for (int i = 0; i < k - 1; i++)
	{
		std::vector<int> after(order);
		for (auto aSwitch : switches)
		{
			after[aSwitch[1]] = order[aSwitch[0]];
		}
		if (inOrder(after))
		{
			num = i + 2;
			break;
		}
		order = std::vector<int>(after);
		prev = std::vector<int>(after);
		all.push_back(prev);
	}

	if (num != -1)
	{
		int one = k % num;
		for (int i = 0; i < n; i++)
		{
			pw->println(all[one][i]);
		}
	}
	else
	{
		for (int i = 0; i < n; i++)
		{
			pw->println(all[all.size() - 1][i]);
		}
	}
	pw->close();
	long long end = System::currentTimeMillis();
	std::wcout << end - sta << std::endl;

	delete st;
	delete pw;
	delete br;
}

bool SwapitySwapitySwap4::inOrder(std::vector<int> &arr)
{
	for (int i = 0; i < arr.size(); i++)
	{
		if (arr[i] != i + 1)
		{
			return false;
		}
	}
	return true;
}
