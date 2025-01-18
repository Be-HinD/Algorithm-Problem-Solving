#include <string>
#include <vector>
#include <iostream>
#include <queue>

using namespace std;

struct Pos
{
    int AX;
    int AY;
    int BX;
    int BY;

    int Count;
    bool Shape; // false = ㅡ, true = ㅣ

    Pos(int ax, int ay, int bx, int by, int count, bool shape) : AX(ax), AY(ay), BX(bx), BY(by), Count(count), Shape(shape) {}
};

int solution(vector<vector<int>> board) {
    int answer = 1e9;
    int DirX[4] = {1, -1, 0, 0};
    int DirY[4] = {0, 0, 1, -1};
    int Size = board.size();
    vector<vector<int>> NewBoard(Size + 2, vector<int>(Size + 2, 1));

    for(int i = 0; i < Size; ++i)
    {
        for(int j = 0; j < Size; ++j)
        {
            if(board[i][j] == 0)
            {
                NewBoard[i + 1][j + 1] = 0;
            }
        }
    }

    vector<vector<bool>>IsVisited1(Size + 2, vector<bool>(Size + 2, false));
    vector<vector<bool>>IsVisited2(Size + 2, vector<bool>(Size + 2, false));

    queue<Pos> Q;

    /*for(auto x : NewBoard)
    {
        for(auto y : x)
        {
            cout << y;
        }
        cout << '\n';
    }
    */

    Q.emplace(Pos(2, 1, 1, 1, 0, false));

    while(!Q.empty())
    {
        Pos CurrentPos = Q.front();
        Q.pop();

        if(CurrentPos.Count >= answer)
        {
            continue;
        }

        if(NewBoard[CurrentPos.AY][CurrentPos.AX] == 1 || NewBoard[CurrentPos.BY][CurrentPos.BX] == 1)
        {
            continue;
        }

        if((CurrentPos.AY == Size && CurrentPos.AX == Size) || (CurrentPos.BY == Size && CurrentPos.BX == Size))
        {
            //cout << "End, Count : " << CurrentPos.Count << '\n';
            answer = min(answer, CurrentPos.Count);
            continue;
        }


        if(!CurrentPos.Shape && IsVisited1[CurrentPos.AY][CurrentPos.AX] && IsVisited1[CurrentPos.BY][CurrentPos.BX])
        {
            continue;
        }

        if(CurrentPos.Shape && IsVisited2[CurrentPos.AY][CurrentPos.AX] && IsVisited2[CurrentPos.BY][CurrentPos.BX])
        {
            continue;
        }

        if(!CurrentPos.Shape)
        {
            IsVisited1[CurrentPos.AY][CurrentPos.AX] = true;
            IsVisited1[CurrentPos.BY][CurrentPos.BX] = true;
        }
        else
        {
            IsVisited2[CurrentPos.AY][CurrentPos.AX] = true;
            IsVisited2[CurrentPos.BY][CurrentPos.BX] = true;
        }

        /*cout << "Current Pos : A -> " << CurrentPos.AY << ", " << CurrentPos.AX << ", B -> " << CurrentPos.BY << ", " << CurrentPos.BX << ", Count : " << CurrentPos.Count << '\n';

        if(!CurrentPos.Shape)
        {
            cout << "Shape : ㅡ" << '\n';
        }
        else
        {
            cout << "Shape : ㅣ" << "\n";
        }
        */

        for(int i = 0; i < 4; ++i)
        {
            if(DirX[i] != 0 && CurrentPos.Shape)
            {
                if(NewBoard[CurrentPos.AY][CurrentPos.AX + DirX[i]] == 0 && NewBoard[CurrentPos.BY][CurrentPos.BX + DirX[i]] == 0)
                {
                    //cout << "Change, DirX : " << DirX[i] << '\n'; 
                    Q.emplace(CurrentPos.BX + DirX[i], CurrentPos.BY, CurrentPos.BX, CurrentPos.BY, CurrentPos.Count + 1, !CurrentPos.Shape);
                    Q.emplace(CurrentPos.AX + DirX[i], CurrentPos.AY, CurrentPos.AX, CurrentPos.AY, CurrentPos.Count + 1, !CurrentPos.Shape);
                }
            }
            else if(DirY[i] != 0 && !CurrentPos.Shape)
            {
                if(NewBoard[CurrentPos.AY + DirY[i]][CurrentPos.AX] == 0 && NewBoard[CurrentPos.BY + DirY[i]][CurrentPos.BX] == 0)
                {
                    //cout << "Change, DirY : " << DirY[i] << '\n'; 
                    //cout << "Change  Pos : A -> " << CurrentPos.BY + DirY[i] << ", " << CurrentPos.BX << ", B -> " << CurrentPos.BY << ", " << CurrentPos.BX << '\n';
                    //cout << "Change  Pos : A -> " << CurrentPos.AY + DirY[i] << ", " << CurrentPos.AX << ", B -> " << CurrentPos.AY << ", " << CurrentPos.AX << '\n';
                    Q.emplace(CurrentPos.BX, CurrentPos.BY + DirY[i], CurrentPos.BX, CurrentPos.BY, CurrentPos.Count + 1, !CurrentPos.Shape);
                    Q.emplace(CurrentPos.AX, CurrentPos.AY + DirY[i], CurrentPos.AX, CurrentPos.AY, CurrentPos.Count + 1, !CurrentPos.Shape);
                }
            }
            Q.emplace(CurrentPos.AX + DirX[i], CurrentPos.AY + DirY[i], CurrentPos.BX + DirX[i], CurrentPos.BY + DirY[i], CurrentPos.Count + 1, CurrentPos.Shape);
        }

    }


    return answer;
}