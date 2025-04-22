#include <iostream>
using namespace std;

const int N = 10;
const int M = 10;

int way(int x, int y, int N, int M) {
	if (x == N && y == M) {
		return 1;
	}

	int summa = 0;
	// идеём вправо пока можем
	if (x != N) {
		summa += way(x + 1, y, N, M);
	}

	// идеём вниз пока можем
	if (y != M) {
		summa += way(x, y + 1, N, M);
	}
	return summa;
}

void main() {
	cout << way(1, 1, N, M); // 1 задание сложность по времени и по памяти: M = O(1) T = O(2**N+M)
}