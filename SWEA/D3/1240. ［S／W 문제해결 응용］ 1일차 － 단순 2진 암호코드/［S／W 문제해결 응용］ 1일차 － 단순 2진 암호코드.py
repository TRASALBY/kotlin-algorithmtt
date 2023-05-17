T = int(input())
# 여러개의 테스트 케이스가 주어지므로, 각각을 처리합니다.

passDict = {"0001101": 0, "0011001": 1, "0010011": 2, "0111101": 3, "0100011": 4, "0110001": 5, "0101111": 6,
            "0111011": 7, "0110111": 8, "0001011": 9}

for test_case in range(1, T + 1):
    answer = 0
    n, m = map(int, input().strip().split())

    passList = []
    for _ in range(n):
        password = str(input().strip())
        if "1" in password:
            for i in range(m-1, -1, -1):
                if password[i] == "1":
                    passList.append(password[i - 55:i + 1])
                    break

    password = passList[0]
    numList = []
    for i in range(8):
        s = i * 7
        e = s + 7
        subPass = password[s:e]
        numList.append(passDict[subPass])

    oddNum = 0
    evenNum = 0
    totalSum = 0
    for i in range(8):
        now = int(numList[i])
        if (i + 1) % 2 == 1:
            oddNum += now
        else:
            evenNum += now
        totalSum += now
    if (oddNum * 3 + evenNum) % 10 == 0:
        answer = totalSum
    else:
        answer = 0
    print(f"#{test_case} {answer}")
